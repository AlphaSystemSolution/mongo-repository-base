/**
 * 
 */
package com.alphasystem.persistence.mongo.repository;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;

import com.alphasystem.persistence.mongo.model.AbstractDocument;
import com.mongodb.DBObject;

/**
 * @author sali
 * 
 */
public abstract class DocumentEventListener<T extends AbstractDocument> extends
		AbstractMongoEventListener<T> {


	@Override
	public void onAfterConvert(DBObject dbo, T source) {
		super.onAfterConvert(dbo, source);
	}

	@Override
	public void onAfterDelete(DBObject dbo) {
		super.onAfterDelete(dbo);
	}

	@Override
	public void onAfterLoad(DBObject dbo) {
		super.onAfterLoad(dbo);
	}

	@Override
	public void onAfterSave(T source, DBObject dbo) {
		super.onAfterSave(source, dbo);
	}

	@Override
	public void onBeforeConvert(T source) {
		super.onBeforeConvert(source);
	}

	@Override
	public void onBeforeDelete(DBObject dbo) {
		super.onBeforeDelete(dbo);
	}

	@Override
	public void onBeforeSave(T source, DBObject dbo) {
		super.onBeforeSave(source, dbo);
	}

}
