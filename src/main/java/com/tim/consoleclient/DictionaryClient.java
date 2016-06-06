package com.tim.consoleclient;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import com.tim.dictionaryservice.Dictionary;
import com.tim.dictionaryservice.DictionaryImpl;
import com.tim.dictionaryservice.InsufficientTermsException;
import com.tim.dictionaryservice.MissingWordsException;

public class DictionaryClient {

	public static void main(String[] args) {
		Console console = System.console();
		console.format("enter dictionary terms, followed by STOP when done" + System.lineSeparator());
		List<String> wordsInDictionary = new ArrayList<String>();
		while (true) {
			final String input = console.readLine();
			if ("STOP".equals(input)) {
				break;
			} else {
				wordsInDictionary.add(input);
			}
		}
		console.format("passing dictionary to infer alphabet ... " + System.lineSeparator());
		try {
			Dictionary dictionary = new DictionaryImpl(wordsInDictionary);
			final List<Character> alphabet = dictionary.getAlphabet();
			console.format(" alphabet: " + alphabet.toString() + System.lineSeparator());
		} catch (MissingWordsException e) {
			console.format(" no words in the dictionary");
		} catch (InsufficientTermsException e) {
			console.format(" alphabet can't be inferred form words given");
		}

	}

}
