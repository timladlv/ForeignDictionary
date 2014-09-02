package com.tim.dictionaryservice;

public class InsufficientTermsException extends Exception {
	
	private static final long serialVersionUID = 4001348247552041807L;

	public InsufficientTermsException(String message) {
		super(message);
	}

}
