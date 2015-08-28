package com.agilet.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.agilet.model.AdminUserEntity;
import com.agilet.util.PMF;

public class AdminUserService {

	private static PersistenceManager persistenceManager = PMF.getInstance().getPersistenceManager();

	public void add(AdminUserEntity adminUserEntity) {
		persistenceManager.makePersistent(adminUserEntity);
	}

	@SuppressWarnings("unchecked")
	public List<AdminUserEntity> getAdminUserEntities() {

		Query query = persistenceManager.newQuery(AdminUserEntity.class);
		List<AdminUserEntity> entries = (List<AdminUserEntity>) query.execute();
		return entries;
	}
}
