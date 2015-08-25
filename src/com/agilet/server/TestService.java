package com.agilet.server;

import java.util.ArrayList;
import java.util.List;

import com.agilet.model.TestEntity;
import com.agilet.util.QueryUtil;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class TestService {

	DatastoreService datastoreService = DatastoreServiceFactory
			.getDatastoreService();

	public void add(TestEntity testEntity) {
		Entity entity = new Entity(TestEntity.class.getSimpleName());
		entity.setProperty("active", testEntity.getActive());
		entity.setProperty("beginDate", testEntity.getBeginDate());
		entity.setProperty("endDate", testEntity.getEndDate());
		entity.setProperty("name", testEntity.getName());
		entity.setProperty("userTested", testEntity.getUserTestes());
		entity.setProperty("totalTime", testEntity.getTotalTime());
		datastoreService.put(entity);
	}

	public void delete(Long id) {
		Entity entity = QueryUtil.getEntityById(
				TestEntity.class.getSimpleName(), id);
		datastoreService.delete(entity.getKey());
	}

	public void update(TestEntity test) {
		// persistenceManager.refresh(test);
	}
	
	public TestEntity getTestById(Long id)
	{
		TestEntity test=null;
		List<TestEntity> testEntities=getTestes();
		for(TestEntity entity:testEntities)
		{
			if(entity.getId().equals(id))
			{
				test=entity;
				break;
			}
		}
		return test;
	}

	@SuppressWarnings("unchecked")
	public List<TestEntity> getTestes() {
		List<TestEntity> testEntities = new ArrayList<TestEntity>();

		Query query = new Query(TestEntity.class.getSimpleName());
		PreparedQuery preparedQuery = datastoreService.prepare(query);
		PreparedQuery pQuery = datastoreService.prepare(query);
		for (Entity entity : pQuery.asIterable()) {

			TestEntity testEntity = new TestEntity();
			Long id = entity.getKey().getId();
			Long endDate = Long.parseLong(entity.getProperty("endDate")
					.toString());

			Long beginDate = Long.parseLong(entity.getProperty("beginDate")
					.toString());

			String name = entity.getProperty("name").toString();
			Integer totalTime = Integer.parseInt(entity
					.getProperty("totalTime").toString());
			testEntity.setActive(Boolean.parseBoolean(entity.getProperty(
					"active").toString()));
			testEntity.setId(id);
			testEntity.setBeginDate(beginDate);
			testEntity.setEndDate(endDate);
			testEntity.setName(name);
			testEntity.setTotalTime(totalTime);
			List<String> userTestList = null;
			Object object = entity.getProperty("userTestes");
			if (object instanceof ArrayList<?>) {
				userTestList = (ArrayList<String>) object;
			}
			testEntity.setUserTestes(userTestList);
			testEntities.add(testEntity);
		}

		return testEntities;
	}

}
