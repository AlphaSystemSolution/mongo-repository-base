/**
 * 
 */
package com.alphasystem.persistence.mongo.test.model;

import static java.lang.String.format;

import com.alphasystem.persistence.mongo.model.AbstractDocument;

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

	public String getValue() {
		return value;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return format("%s:%s", super.toString(), type);
	}

}
