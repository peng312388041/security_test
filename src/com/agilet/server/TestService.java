package com.agilet.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.agilet.model.TestEntity;
import com.agilet.util.PMF;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class TestService {

	DatastoreService datastoreService = DatastoreServiceFactory
			.getDatastoreService();

	private static PersistenceManager persistenceManager = PMF.getInstance()
			.getPersistenceManager();

	public void add(TestEntity testEntity) {
		persistenceManager.makePersistent(testEntity);
	}

	public void delete(TestEntity testEntity) {

		persistenceManager.deletePersistent(testEntity);
	}

	public void update(TestEntity test) {
		persistenceManager.refresh(test);
	}

	public TestEntity getTestById(String key) {
		return persistenceManager.getObjectById(TestEntity.class, key);
	}

	public List<TestEntity> getTestes() {

		List<TestEntity> testEntities = new ArrayList<TestEntity>();
		 
		Query query = new Query(TestEntity.class.getSimpleName());
		PreparedQuery pQuery = datastoreService.prepare(query);
		for (Entity entity : pQuery.asIterable()) {
			TestEntity testEntity = new TestEntity();
			testEntity = (TestEntity) persistenceManager.getObjectById(
					TestEntity.class, entity.getKey());
//			persistenceManager.makePersistent(testEntity);
			testEntities.add(testEntity);
		}
		return testEntities;
	}

}
