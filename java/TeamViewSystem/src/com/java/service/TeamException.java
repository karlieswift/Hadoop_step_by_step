package com.java.service;

public class TeamException extends Exception {

	
	public TeamException(Exception e) {
		super(e);
	}
	public TeamException(String msg) {
		super(msg);
	}

}
