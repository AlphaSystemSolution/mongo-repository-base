/**
 * 
 */
package com.alphasystem.persistence.mongo.test.model;

import com.alphasystem.persistence.model.AbstractDocument;

import static java.lang.String.format;

/**
 * @author sali
 * 
 */
public class TestEntity extends AbstractDocument {

	private static final long serialVersionUID = -7641300010386837973L;

	private String value;

	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return format("%s:%s", super.toString(), type);
	}

}
