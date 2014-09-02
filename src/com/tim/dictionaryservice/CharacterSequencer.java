package com.tim.dictionaryservice;

class CharacterSequencer implements InferenceCharacterSequencer {

	@Override
	public Inference inferSequenceFromWords(String first, String second) {
		Inference inference = null;
		if (first == null || second == null) {
			throw new IllegalArgumentException("parameters must not be null");
		}
		if (first.equals(second)) {
			inference = new NullInference();
		}
		if (second.startsWith(first)) {
			inference = new NullInference();
		}
		final char[] firstCharacters = first.toCharArray();
		final char[] secondCharacters = second.toCharArray();
		for (int i = 0; i < firstCharacters.length; i++) {
			if (firstCharacters[i] != secondCharacters[i]) {
				inference = new CharacterInference(firstCharacters[i], secondCharacters[i]);
				break;
			}
		}
		return inference;
	}

}
