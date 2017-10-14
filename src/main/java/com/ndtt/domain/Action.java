package com.ndtt.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Action {
	@Id
	private String id;
	private Date date;
	private String actor;
	private String actionType;
	private List<String> imageLinks;
	private String note;
	private Coordinates coordinate;
}
