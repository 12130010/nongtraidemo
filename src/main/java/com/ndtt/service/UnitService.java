package com.ndtt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndtt.domain.SingleUnit;
import com.ndtt.repository.UnitRepository;

@Service
public class UnitService {
	@Autowired
	private UnitRepository unitRepository;
	
	public SingleUnit create(SingleUnit unit) {
		return unitRepository.save(unit);
	}
	
	public SingleUnit get(String id) {
		return unitRepository.findOne(id);
	}
}
