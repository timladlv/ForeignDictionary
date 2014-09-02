package com.tim.dictionaryservice;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tim.dictionaryservice.CharacterInference;

public class CharacterInferenceTest {
	
	private CharacterInference uut;
	
	@Test
	public void testCharacterInference() {
		uut = new CharacterInference('A', 'B');
		assertTrue(uut.inferenceMade());
		assertEquals(new Character('A'), uut.comesBefore());
		assertEquals(new Character('B'), uut.comesAfter());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCharacterInferenceConstructedWithSameCharacters() {
		uut = new CharacterInference('A', 'A');
	}

}
