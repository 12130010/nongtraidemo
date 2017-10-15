package com.ndtt.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ndtt.domain.Action;
import com.ndtt.domain.Coordinates;
import com.ndtt.domain.SingleUnit;
import com.ndtt.repository.ActionRepository;

@Service
public class ActionService {
	@Autowired
	ActionRepository actionRepository;
	@Autowired
	FileService fileService;
	@Autowired
	UnitService unitService;

	public Action create(SingleUnit singleUnit, Action action) {
		action = actionRepository.save(action);
		singleUnit.addAction(action);
		unitService.update(singleUnit);
		return action;

	}

	public Action create(String unitId, String userId, String actionType, String note, String longtitude,
			String latitude, List<MultipartFile> files) throws URISyntaxException, IOException {
		Action action = new Action();
		SingleUnit singleUnit = unitService.get(unitId);
		if (singleUnit == null)
			return null;
		List<String> fileNames = fileService.save(files);
		Coordinates coordinates = new Coordinates(longtitude, latitude);
		action.setActionType(actionType);
		action.setActor(userId);
		action.setCoordinate(coordinates);
		action.setImageLinks(fileNames);
		action.setNote(note);
		return create(singleUnit, action);

	}

}
