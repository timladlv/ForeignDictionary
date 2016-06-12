package com.tim.dictionaryservice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DictionaryTest {
	
	private DictionaryImpl uut;
	
	@Test (expected=MissingWordsException.class)
	public void testNoWordsProvided() throws MissingWordsException {
		List<String> words = new ArrayList<String>();
		uut = new DictionaryImpl(words);
		fail();
	}
		
	@Test (expected=MissingWordsException.class)
	public void testNullWordsProvided() throws MissingWordsException {
		uut = new DictionaryImpl(null);
		fail();
	}
	
	@Test
	public void testSingleLetterAlphabet() throws MissingWordsException, InsufficientTermsException {
		uut = new DictionaryImpl(Arrays.asList("A"));
		List<Character> alphabet = uut.getAlphabet();
		assertEquals(1, alphabet.size());
		assertEquals(Arrays.asList('A'), alphabet);
	}
	
	@Test
	public void testTwoLetterAlphabet() throws MissingWordsException, InsufficientTermsException {
		uut = new DictionaryImpl(Arrays.asList("B", "A"));
		List<Character> alphabet = uut.getAlphabet();
		assertEquals(2, alphabet.size());
		assertEquals(Arrays.asList('B', 'A'), alphabet);
	}
	
	@Test
	public void testTwoLetterAlphabetWordsDifferentFirstLetter() throws MissingWordsException, InsufficientTermsException {
		uut = new DictionaryImpl(Arrays.asList("BA", "A"));
		List<Character> alphabet = uut.getAlphabet();
		assertEquals(2, alphabet.size());
		assertEquals(Arrays.asList('B', 'A'), alphabet);
	}
	
	@Test
	public void testTwoLetterAlphabetWordsDifferentSecondLetter() throws MissingWordsException, InsufficientTermsException {
		uut = new DictionaryImpl(Arrays.asList("BB", "BA"));
		List<Character> alphabet = uut.getAlphabet();
		assertEquals(2, alphabet.size());
		assertEquals(Arrays.asList('B', 'A'), alphabet);
	}
	
	@Test (expected = InsufficientTermsException.class)
	public void testSimpleInsufficientPairs() throws MissingWordsException, InsufficientTermsException {
		uut = new DictionaryImpl(Arrays.asList("CB", "CA", "AB"));
		uut.getAlphabet();
	}
	
	@Test
	public void testFourLetterWordsWithSixDifferentLetters() throws MissingWordsException, InsufficientTermsException {
		uut = new DictionaryImpl(Arrays.asList("ZZXP", "ZYXX", "ZHXY", "HXYY", "XTPP", "TZXT", "TZXP"));
		List<Character> alphabet = uut.getAlphabet();
		assertEquals(6, alphabet.size());
		assertEquals(Arrays.asList('Z', 'Y', 'H', 'X', 'T', 'P'), alphabet);		
	}
	
}
