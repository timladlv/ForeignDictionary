package com.tim.consoleclient;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tim.dictionaryservice.Dictionary;
import com.tim.dictionaryservice.DictionaryImpl;
import com.tim.dictionaryservice.InsufficientTermsException;
import com.tim.dictionaryservice.MissingWordsException;

public class DictionaryClient {

	public static void main(final String[] args) {
		final Scanner scanner = new Scanner(System.in);
		System.out.println("enter dictionary terms, followed by STOP when done");
		final List<String> wordsInDictionary = new ArrayList<String>();
		while (true) {
			final String input = scanner.nextLine();
			if ("STOP".equals(input)) {
				break;
			} else {
				wordsInDictionary.add(input);
			}
		}
		System.out.println("passing dictionary to infer alphabet ... ");
		try {
			final Dictionary dictionary = new DictionaryImpl(wordsInDictionary);
			final List<Character> alphabet = dictionary.getAlphabet();
			System.out.println(" alphabet: " + alphabet.toString());
		} catch (final MissingWordsException e) {
			System.out.println(" no words in the dictionary");
		} catch (final InsufficientTermsException e) {
			System.out.println(" alphabet can't be inferred from words given");
		}

	}

}
