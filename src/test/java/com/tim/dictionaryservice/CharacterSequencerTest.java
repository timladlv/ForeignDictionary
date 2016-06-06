package com.tim.dictionaryservice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.tim.dictionaryservice.CharacterSequencer;
import com.tim.dictionaryservice.Inference;

public class CharacterSequencerTest {
	
	private CharacterSequencer uut;
	
	@Before
	public void setup() {
		uut = new CharacterSequencer();
	}
	
	@Test
	public void testEqualWordsYieldNulInference() {
		String aWord = "jkdfhsjkdsfjskfh";
		final Inference inference = uut.inferSequenceFromWords(aWord, aWord);
		assertFalse(inference.inferenceMade());
	}
	
	@Test
	public void testExtendedWordsYieldNullInference() {
		String aWord = "jkdfhsjkdsfjskfh";
		String extendedWord = aWord + "dskjfh";
		final Inference inference = uut.inferSequenceFromWords(aWord, extendedWord);
		assertFalse(inference.inferenceMade());
	}
	
	@Test
	public void testWordsDifferByFirstCharacterYieldCharacterInference() {
		String first = "BBBB";
		String second = "ABBB";
		final Inference inference = uut.inferSequenceFromWords(first, second);
		assertTrue(inference.inferenceMade());
		assertEquals((char)first.charAt(0), (char)inference.comesBefore());
		assertEquals((char)second.charAt(0), (char)inference.comesAfter());
	}

}
