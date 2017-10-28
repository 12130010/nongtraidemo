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
	private String id;
	private String name;
	private String address;
	private String type;
	private int level;
	private List<String> owners;
	private Coordinates coordinate;
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
		return "SingleUnit [id=" + id + ", name=" + name + ", address=" + address + ", type=" + type + ", level="
				+ level + ", owners=" + owners + ", coordinate=" + coordinate + ", childUnits=" + childUnits
				+ ", actions=" + actions + "]";
	}

	
	
}
