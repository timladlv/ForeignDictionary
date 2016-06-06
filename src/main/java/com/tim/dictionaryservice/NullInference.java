package com.tim.dictionaryservice;

class NullInference implements Inference {

	@Override
	public boolean inferenceMade() {
		return false;
	}

	@Override
	public Character comesBefore() {
		throw new UnsupportedOperationException("comesBefore not available from NullInference");
	}

	@Override
	public Character comesAfter() {
		throw new UnsupportedOperationException("comesAfter not available from NullInference");
	}

}
