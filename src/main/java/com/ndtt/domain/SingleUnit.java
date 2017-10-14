package com.ndtt.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ndtt.cascade.CascadeSave;

import lombok.Data;

@Data
@Document(collection = "unit")
public class SingleUnit extends Unit{
	@CascadeSave
	@DBRef
	private List<Action> actions;
	
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
