/**
 * 
 */
package com.alphasystem.persistence.mongo.repository;

import com.alphasystem.persistence.model.AbstractDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.*;

/**
 * @author sali
 * 
 */
public abstract class DocumentEventListener<T extends AbstractDocument> extends
		AbstractMongoEventListener<T> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAfterConvert(AfterConvertEvent<T> event) {
        super.onAfterConvert(event);
    }


    @Override
    public void onAfterDelete(AfterDeleteEvent<T> event) {
        super.onAfterDelete(event);
    }

    @Override
    public void onAfterLoad(AfterLoadEvent<T> event) {
        super.onAfterLoad(event);
    }

    @Override
    public void onAfterSave(AfterSaveEvent<T> event) {
        super.onAfterSave(event);
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<T> event) {
        super.onBeforeConvert(event);
        T source = event.getSource();
        source.initDisplayName();
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<T> event) {
        super.onBeforeDelete(event);
    }

    @Override
    public void onBeforeSave(BeforeSaveEvent<T> event) {
        super.onBeforeSave(event);
    }

}
