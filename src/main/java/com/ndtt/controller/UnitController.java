package com.ndtt.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndtt.domain.SingleUnit;
import com.ndtt.service.UnitService;

@RestController
@RequestMapping(value = "/unit")
public class UnitController {

	@Autowired
	private UnitService unitService;

	@PostMapping(value = "/get")
	public ResponseEntity<?> getUnits(HttpServletRequest request, Principal principal, Locale locale) {
		String[] userIds = request.getParameterValues("userId");
		int level = Integer.parseInt(request.getParameter("level"));
		int page = UnitService.DEFAULT_PAGE;
		int size = UnitService.DEFAULT_SIZE;
		try {
			page = Integer.parseInt(request.getParameter("page"));
			size = Integer.parseInt(request.getParameter("size"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<SingleUnit> singleUnits = unitService.getByUserIdAndLevel(Arrays.asList(userIds), level, page, size);
		return ResponseEntity.ok(singleUnits);
	}
}
