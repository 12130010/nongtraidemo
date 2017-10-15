package com.ndtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ndtt.domain.SingleUnit;
import com.ndtt.service.UnitService;

@Controller
@RequestMapping(value = "unit")
public class NongTraiController {
	@Autowired
	UnitService unitSetvice;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public @ResponseBody SingleUnit initUnit() {
		return new SingleUnit();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody SingleUnit createUnit(@RequestBody SingleUnit unit, @RequestParam(defaultValue = "") String unitParentId) {
		return unitSetvice.create(unit, unitParentId); 
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody SingleUnit getUnit(@PathVariable String id) {
		return unitSetvice.get(id);
	}
}
