package com.agilet.util;

import java.util.ArrayList;
import java.util.List;

import com.agilet.model.TestEntity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class QueryUtil {
	private static DatastoreService datastoreService = DatastoreServiceFactory
			.getDatastoreService();

	@SuppressWarnings("unused")
	public static Entity getEntityById(String string, Long id) {
		Entity entity = null;
		List<TestEntity> testEntities = new ArrayList<TestEntity>();

		Query query = new Query(string);
		PreparedQuery preparedQuery = datastoreService.prepare(query);
		PreparedQuery pQuery = datastoreService.prepare(query);

		for (Entity object : pQuery.asIterable()) {
			if (object.getKey().getId() == id) {
				entity = object;
			}
		}
		return entity;
	}

	@SuppressWarnings("unused")
	public static Entity getEntityById(String string, String id) {
		Entity entity = null;
		List<TestEntity> testEntities = new ArrayList<TestEntity>();

		Query query = new Query(string);
		PreparedQuery preparedQuery = datastoreService.prepare(query);
		PreparedQuery pQuery = datastoreService.prepare(query);

		for (Entity object : pQuery.asIterable()) {
			if (object.getKey().getId() == Long.parseLong(id)) {
				entity = object;
			}
		}
		return entity;
	}
}
