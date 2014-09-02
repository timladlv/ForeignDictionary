package com.tim.dictionaryservice;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.tim.dictionaryservice.NullInference;

public class NullInferenceTest {
	
	private NullInference uut;
	
	@Before
	public void setup() {
		uut = new NullInference();
	}
	
	@Test
	public void inferenceMadeIsFalse() {
		assertFalse(uut.inferenceMade());
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void getComesBeforeThrowsException() {
		uut.comesBefore();
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void getComesAfterThrowsException() {
		uut.comesAfter();
	}
	
	
	

}
