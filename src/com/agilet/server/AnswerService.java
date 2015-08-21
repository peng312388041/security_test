package com.agilet.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.agilet.model.AnswerEntity;
import com.agilet.util.PMF;

public class AnswerService {
	private static PersistenceManager persistenceManager = PMF.getInstance().getPersistenceManager();

	public void add(AnswerEntity answer) {
		persistenceManager.makePersistent(answer);
	}

	public void delete(AnswerEntity answer) {
		persistenceManager.deletePersistent(answer);
	}

	public void update(AnswerEntity answer) {
		persistenceManager.refresh(answer);
	}

	public List<AnswerEntity> getAnswers() {
		List<AnswerEntity> answerEntities = null;
		Query query = persistenceManager.newQuery(AnswerEntity.class);
		answerEntities = (List<AnswerEntity>) query.execute();
		return answerEntities;
	}

	public AnswerEntity getAnswerByKey(String key) {
		AnswerEntity answer = null;
		List<AnswerEntity> answerList = getAnswers();
		for (AnswerEntity entity : answerList) {
			if (entity.getKey().equals(key)) {
				answer = entity;
			}
		}
		return answer;
	}
}
