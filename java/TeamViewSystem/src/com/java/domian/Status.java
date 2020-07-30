package com.java.domian;

public class Status {
	private final String NAME;
	

	public String getNAME() {
		return NAME;
	}
	public static Status getFree() {
		return FREE;
	}
	public static Status getBusy() {
		return BUSY;
	}
	public static Status getVocation() {
		return VOCATION;
	}
	public Status(String name) {
		this.NAME = name;
	}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return NAME;
}
	public static final Status FREE = new Status("FREE");
	public static final Status BUSY = new Status("BUSY");
	public static final Status VOCATION = new Status("VOCATION");
}
