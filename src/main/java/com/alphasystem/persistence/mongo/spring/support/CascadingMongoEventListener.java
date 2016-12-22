package com.alphasystem.persistence.mongo.spring.support;

import com.alphasystem.persistence.model.AbstractDocument;
import com.alphasystem.persistence.model.AbstractSimpleDocument;
import com.alphasystem.persistence.model.CascadeSave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mapping.model.MappingException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;

import static com.alphasystem.util.AppUtil.isInstanceOf;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.util.ReflectionUtils.doWithFields;
import static org.springframework.util.ReflectionUtils.makeAccessible;

/**
 * @author sali
 */
@Component
public class CascadingMongoEventListener extends AbstractMongoEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CascadingMongoEventListener.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @SuppressWarnings({"unchecked"})
    @Override
    public void onBeforeConvert(BeforeConvertEvent event) {
        final Object source = event.getSource();
        doWithFields(source.getClass(), field -> {
            LOGGER.debug("Start saving field \"{}\"", field.getName());
            makeAccessible(field);

            if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
                final Object fieldValue = field.get(source);

                DbRefFieldCallback callback = new DbRefFieldCallback();

                LOGGER.debug("Current field value \"{}\"", fieldValue);
                if (fieldValue == null) {
                    LOGGER.debug("Skipping field \"{}\" because of null value", field.getName());
                    return;
                }
                Class<?> fieldClass = fieldValue.getClass();

                if (Collection.class.isAssignableFrom(fieldClass)) {
                    Collection collection = (Collection) fieldValue;
                    collection.stream().filter(o -> isInstanceOf(AbstractSimpleDocument.class, o)).forEach(o ->
                            save(field, o));
                } else {
                    doWithFields(fieldClass, callback);
                    if (!callback.isIdFound()) {
                        throw new MappingException("Cannot perform cascade save on child object without id set");
                    }
                    save(field, fieldValue);
                }

            }
        });
    }

    private void save(Field field, Object fieldValue) {
        String id = "none";
        if (isInstanceOf(AbstractSimpleDocument.class, fieldValue)) {
            id = ((AbstractSimpleDocument) fieldValue).getId();
        }
        if (isInstanceOf(AbstractDocument.class, fieldValue)) {
            AbstractDocument ad = (AbstractDocument) fieldValue;
            String displayName = ad.getDisplayName();
            Query query = new Query(where("displayName").is(displayName));
            AbstractDocument entity = mongoTemplate.findOne(query, ad.getClass());
            if (entity != null && !entity.getId().equals(id)) {
                LOGGER.error("Possible duplicate Target ID: {}, Database ID: {}, Display Name: {} for \"{}\"",
                        id, entity.getId(), displayName, fieldValue.getClass().getSimpleName());
                return;
            }
        }
        try {
            mongoTemplate.save(fieldValue);
        } catch (Exception e) {
            LOGGER.error("Error occurred while saving field \"{}\" with value \"{}\" and id \"{}\", error message is \"{}\"",
                    field.getName(), fieldValue, id, e.getMessage());
        }
    }

    private static class DbRefFieldCallback implements ReflectionUtils.FieldCallback {
        private boolean idFound;

        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
            makeAccessible(field);

            if (field.isAnnotationPresent(Id.class)) {
                idFound = true;
            }
        }

        boolean isIdFound() {
            return idFound;
        }
    }
}
