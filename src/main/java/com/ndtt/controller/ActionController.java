package com.ndtt.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ndtt.domain.Action;
import com.ndtt.service.ActionService;

@Controller
@RequestMapping(value = "action")
public class ActionController {

	@Autowired
	ActionService actionService;

	@PostMapping(value = "/")
	public @ResponseBody ResponseEntity<?> create(MultipartHttpServletRequest request, Principal principal,
			Locale locale) {
		String unitId = request.getParameter("unitId");
		String userId = request.getParameter("userId");
		String actionType = request.getParameter("actionType");
		String note = request.getParameter("note");
		String longtitude = request.getParameter("longtitude");
		String latitude = request.getParameter("latitude");
		List<MultipartFile> files = request.getFiles("files");
		try {
			Action action = actionService.create(unitId, userId, actionType, note, longtitude, latitude, files);
			return ResponseEntity.ok(action);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
