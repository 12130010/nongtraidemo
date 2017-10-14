package com.ndtt.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.ndtt.cascade.CascadeSave;

import lombok.Data;

@Data
public abstract class Unit {
	@Id
	protected String id;
	protected String name;
	protected String address;
	protected int level;
	protected List<String> owners;
	protected Coordinates coordinate;
	@DBRef
	@CascadeSave
	private List<Unit> childUnits;
	
	public void addChild(Unit unit){
		if (childUnits == null)
			childUnits = new ArrayList<Unit>();
		childUnits.add(unit);
	}

	@Override
	public String toString() {
		return "Unit [name=" + name + ", address=" + address + ", level=" + level + ", owners=" + owners
				+ ", coordinate=" + coordinate + ", childUnits=" + childUnits + "]";
	}
	
	
}
