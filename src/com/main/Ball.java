package com.main;

public class Ball {
	private final int index;
	private int weight;
	private String originalState;
	private boolean stateChanged;

	public Ball(int index, int weight) {
		this.index = index;
		this.weight = weight;
	}

	public int getIndex() {
		return index;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String getOriginalState() {
		return originalState;
	}

	public void setOriginalState(String originalState) {
		this.originalState = originalState;
	}
	
	public boolean isStateChanged() {
		return stateChanged;
	}

	public void setStateChanged(boolean stateChanged) {
		this.stateChanged = stateChanged;
	}
	
}
