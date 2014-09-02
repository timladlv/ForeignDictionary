package com.tim.dictionaryservice;

class CharacterInference implements Inference {
	
	private final Character comesBefore;
	private final Character comesAfter;

	public CharacterInference(Character comesBefore, Character comesAfter) {
		if (comesBefore.equals(comesAfter)) {
			throw new IllegalArgumentException("comesBefore and comesAfter must be different");
		}
		this.comesBefore = comesBefore;
		this.comesAfter = comesAfter;
	}

	@Override
	public boolean inferenceMade() {
		return true;
	}

	@Override
	public Character comesBefore() {
		return comesBefore;
	}

	@Override
	public Character comesAfter() {
		return comesAfter;
	}

}
