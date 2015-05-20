/**
 * 
 */
package com.alphasystem.persistence.mongo.test;

import static com.alphasystem.persistence.mongo.spring.support.config.MongoConfig.MONGO_DB_NAME_PROPERTY;
import static java.lang.String.format;
import static java.lang.System.out;
import static java.lang.System.setProperty;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alphasystem.persistence.mongo.test.model.TestEntity;
import com.alphasystem.persistence.mongo.test.model.Type;
import com.alphasystem.persistence.mongo.test.repository.TestRepository;
import com.alphasystem.persistence.mongo.test.spring.TestMongoConfig;

/**
 * @author sali
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestMongoConfig.class })
public class BaseTest {

	private static final String SEP = "*******************************************";

	private static final String DEFAULT_DISPLAY_NAME = "testDisplayName1";

	static {
		setProperty(MONGO_DB_NAME_PROPERTY, "test");
	}

	private TestRepository testRepository;

	private TestEntity testEntity;

	private TestEntity duplicateTestEntity;

	@Test
	public void create() throws Exception {
		save(testEntity);
	}

	@Test
	public void duplicateCreate() throws Exception {
		out.println(format("%s %s %s", SEP, "duplicateTestEntity", SEP));
		save(duplicateTestEntity);
		out.println(format("%s %s %s", SEP, "duplicateTestEntity", SEP));
	}

	@Test
	public void getByDisplayName() throws Exception {
		save(testEntity);
		TestEntity entity = testRepository
				.findByDisplayName(DEFAULT_DISPLAY_NAME);
		if (entity == null) {
			out.println(format("No Entiry found with displayNme {%s}",
					DEFAULT_DISPLAY_NAME));
		} else {
			out.println(format("Entity found {%s}", entity));
		}
	}

	public TestRepository getTestRepository() {
		return testRepository;
	}

	@Before
	public void purgeRepository() {
		testRepository.deleteAll();

		testEntity = new TestEntity();
		testEntity.setId("dummy");
		testEntity.setDisplayName(DEFAULT_DISPLAY_NAME);
		testEntity.setValue("value");
		testEntity.setType(Type.ONE);

		duplicateTestEntity = new TestEntity();
		duplicateTestEntity.setId("dummy");
		duplicateTestEntity.setDisplayName(DEFAULT_DISPLAY_NAME);
		duplicateTestEntity.setValue("value");
		duplicateTestEntity.setType(Type.TWO);
	}

	private void save(TestEntity entity) throws Exception {
		out.println(format("Before saving DisplayName: {%s}, ID: {%s}",
				entity.getDisplayName(), entity.getId()));
		testEntity = testRepository.save(entity);
		out.println(format("After saving DisplayName: {%s}, ID: {%s}",
				testEntity.getDisplayName(), testEntity.getId()));
	}

	@Autowired
	public void setTestRepository(TestRepository testRepository) {
		this.testRepository = testRepository;
	}
}
