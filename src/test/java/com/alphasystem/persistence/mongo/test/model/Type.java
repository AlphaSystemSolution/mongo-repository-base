/**
 * 
 */
package com.alphasystem.persistence.mongo.test.model;

/**
 * @author sali
 * 
 */
public enum Type {

	ONE(1), TWO(2);

	private final int val;

	private Type(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
