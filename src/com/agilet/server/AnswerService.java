package com.agilet.server;

import javax.jdo.PersistenceManager;

import com.agilet.model.AnswerEntity;
import com.agilet.util.PMF;

public class AnswerService {
	private static PersistenceManager persistenceManager = PMF.getInstance()
			.getPersistenceManager();

	public void add(AnswerEntity answer) {
		persistenceManager.makePersistent(answer);
	}

	public void delete(AnswerEntity answer) {
		persistenceManager.deletePersistent(answer);
	}

	public void update(AnswerEntity answer) {
		persistenceManager.refresh(answer);
	}

	public AnswerEntity getAnswerByKey(String key) {
		return persistenceManager.getObjectById(AnswerEntity.class, key);
	}
}
