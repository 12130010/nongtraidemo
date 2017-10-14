package com.ndtt.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "unit")
public class ComplexUnit extends Unit{@Override
	public String toString() {
		return "ComplexUnit [toString()=" + super.toString() + ", getName()=" + getName() + ", getAddress()="
				+ getAddress() + ", getLevel()=" + getLevel() + ", getOwners()=" + getOwners() + ", getCoordinate()="
				+ getCoordinate() + ", getChildUnits()=" + getChildUnits() + ", getClass()=" + getClass() + "]";
	}
	
}
