package com.ndtt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ndtt.domain.SingleUnit;
import com.ndtt.repository.UnitRepository;

@Service
public class UnitService {
	@Autowired
	private UnitRepository unitRepository;
	public static final int DEFAULT_PAGE = 0;
	public static final int DEFAULT_SIZE = 20;

	public SingleUnit create(SingleUnit unit) {
		return unitRepository.save(unit);
	}
	public SingleUnit create(SingleUnit unit, String unitParentId) {
		SingleUnit parentUnit = unitRepository.findOne(unitParentId);
		unit = unitRepository.save(unit);
		
		parentUnit.addChild(unit);
		unitRepository.save(parentUnit);
		
		return unit;
	}
	
	public SingleUnit get(String id) {
		return unitRepository.findOne(id);
	}
	
	public SingleUnit update(SingleUnit singleUnit) {
		return unitRepository.save(singleUnit);
		
	}

	public List<SingleUnit> getByUserIdAndLevel(List<String> userIds, int level, int page, int size) {
		return unitRepository.findByOwnersContainsAndLevel(userIds, level, createPageable(page, size));
	}

	public List<SingleUnit> getByUserIdAndLevel(List<String> userIds, int level) {
		return unitRepository.findByOwnersContainsAndLevel(userIds, level, createPageable(DEFAULT_PAGE, DEFAULT_SIZE));
	}

	Pageable createPageable(int page, int size) {
		Pageable pageable = new PageRequest(page, size);
		return pageable;
	}

	Pageable createPageable(int page, int size, Sort sort) {
		Pageable pageable = new PageRequest(page, size, sort);
		return pageable;
	}

}
