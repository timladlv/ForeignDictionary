package com.tim.dictionaryservice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DictionaryImpl implements Dictionary {
	
	private final List<String> words;
	private final InferenceCharacterSequencer inferenceCharacterSequencer;

	public DictionaryImpl(final List<String> words) throws MissingWordsException {
		if (words == null || words.size() == 0) {
			throw new MissingWordsException("no words received");
		}
		this.words = words;
		this.inferenceCharacterSequencer = new CharacterSequencer();
	}

	@Override
	public List<Character> getAlphabet() throws InsufficientTermsException {
		final List<Character> dictionary = new ArrayList<Character>();
		if (words.size() == 1) {
			dealWithSingleWord(dictionary);
		} else {
			dealWithManyWords(dictionary);
			
		}
		return dictionary;
	}

	private void dealWithManyWords(final List<Character> dictionary) throws InsufficientTermsException {
		final List<Inference> inferences = new ArrayList<Inference>();
		for (int i = 1; i < words.size(); i++) {
			inferences.add(inferenceCharacterSequencer.inferSequenceFromWords(words.get(i - 1), words.get(i)));
		}
		removeNullInferences(inferences);
		final Set<Character> uniqueCharacters = findAllCharacters();
		while (uniqueCharacters.size() > 0) {
			final Character hasNoneComesAfter = findSingleCharacterWhichOnlyComesFirst(
					inferences, uniqueCharacters);
			dictionary.add(hasNoneComesAfter);
			uniqueCharacters.remove(hasNoneComesAfter);
			removeProcessedInferences(inferences, hasNoneComesAfter);			
		}
	}

	private void removeProcessedInferences(final List<Inference> inferences,
										   final Character hasNoneComesAfter) {
		final Iterator<Inference> inferenceIterator = inferences.iterator();
		while (inferenceIterator.hasNext()) {
			final Inference inference = inferenceIterator.next();
			if (inference.comesBefore().equals(hasNoneComesAfter)) {
				inferenceIterator.remove();
			}
		}
	}

	private Character findSingleCharacterWhichOnlyComesFirst(
			final List<Inference> inferences, final Set<Character> uniqueCharacters) throws InsufficientTermsException {
		Character hasNoneComesAfter = null;
		for (final Character character : uniqueCharacters) {
			boolean noneComesAfter = true;
			for (final Inference inference : inferences) {
				if (inference.comesAfter().equals(character)) {
					noneComesAfter = false;
					break;
				}
			}
			if (noneComesAfter && hasNoneComesAfter != null) {
				throw new InsufficientTermsException("more than 1 character found as next: " + character + " and " + hasNoneComesAfter);
			}
			if (noneComesAfter) {
				hasNoneComesAfter = character;
			}
		}
		return hasNoneComesAfter;
	}

	private Set<Character> findAllCharacters() {
		final Set<Character> uniqueCharacters = new HashSet<Character>();
		for (final String word: words) {
			final char[] primitiveCharacters = word.toCharArray();
			final List<Character> characters = new ArrayList<Character>();
			for (final char character : primitiveCharacters) {
				characters.add(character);
			}
			uniqueCharacters.addAll(characters);
		}
		return uniqueCharacters;
	}

	private void removeNullInferences(final List<Inference> inferences) {
		final Iterator<Inference> iterator = inferences.iterator();
		while (iterator.hasNext()) {
			final Inference next = iterator.next();
			if (!next.inferenceMade()) {
				iterator.remove();
			}
		}
	}

	private void dealWithSingleWord(final List<Character> dictionary)
			throws InsufficientTermsException {
		final String word = words.get(0);
		if (word.length() > 1) {
			throw new InsufficientTermsException("single word with more than 1 letter");
		} else {
			dictionary.add(new Character(word.charAt(0)));
		}
	}
}
