package com.agilet.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.agilet.model.TestEntity;
import com.agilet.util.PMF;

public class TestService {
	private static PersistenceManager persistenceManager = PMF.getInstance()
			.getPersistenceManager();

	public void add(TestEntity testEntity) {
		persistenceManager.makePersistent(testEntity);
	}

	public void delete(TestEntity test) {
		persistenceManager.deletePersistent(test);
	}

	public void update(TestEntity test) {
		persistenceManager.refresh(test);
	}

	public List<TestEntity> getTestes() {
		List<TestEntity> testEntities = null;
		Query query = persistenceManager.newQuery(TestEntity.class);
		testEntities = (List<TestEntity>) query.execute();
		return testEntities;
	}

	public TestEntity getTestById(Long id) {
		TestEntity test = null;
		List<TestEntity> testEntities = getTestes();
		for (TestEntity entity : testEntities) {
			if (entity.getId().equals(id)) {
				test = entity;
			}
		}
		return test;
	}

}
