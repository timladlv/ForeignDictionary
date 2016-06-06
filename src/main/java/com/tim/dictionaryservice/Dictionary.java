package com.tim.dictionaryservice;

import java.util.List;

public interface Dictionary {
	
	public List<Character> getAlphabet() throws InsufficientTermsException;

}
