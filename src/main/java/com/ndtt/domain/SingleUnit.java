package com.ndtt.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ndtt.cascade.CascadeSave;

import lombok.Data;

@Data
@Document(collection = "unit")
public class SingleUnit {
	@Id
	protected String id;
	protected String name;
	protected String address;
	protected int level;
	protected List<String> owners;
	protected Coordinates coordinate;
	@DBRef
	@CascadeSave
	private List<SingleUnit> childUnits;
	
	@CascadeSave
	@DBRef
	private List<Action> actions;
	

	public void addChild(SingleUnit unit){
		if (childUnits == null)
			childUnits = new ArrayList<SingleUnit>();
		childUnits.add(unit);
	}
	
	public void addAction(Action action) {
		if (actions == null) {
			actions = new ArrayList<Action>();
		}
		
		actions.add(action);
	}

	@Override
	public String toString() {
		return "SingleUnit [actions=" + actions + ", getName()=" + getName() + ", getAddress()=" + getAddress()
				+ ", getLevel()=" + getLevel() + ", getOwners()=" + getOwners() + ", getCoordinate()=" + getCoordinate()
				+ ", getChildUnits()=" + getChildUnits() + ", getClass()=" + getClass() + "]";
	}
	
}
