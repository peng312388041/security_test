package com.agilet.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.agilet.model.AnswerEntity;
import com.agilet.model.ProblemEntity;
import com.agilet.util.PMF;
import com.google.appengine.api.datastore.KeyFactory;

public class ProblemService {
	private static PersistenceManager persistenceManager = PMF.getInstance().getPersistenceManager();

	public void add(ProblemEntity problem) {
		persistenceManager.makePersistent(problem);
	}

	public void delete(ProblemEntity problem) {
		persistenceManager.deletePersistent(problem);
	}

	public void update(ProblemEntity problem) {
		persistenceManager.refresh(problem);
	}

	public List<ProblemEntity> getProblems() {
		List<ProblemEntity> problemEntities = null;
		Query query = persistenceManager.newQuery(ProblemEntity.class);
		problemEntities = (List<ProblemEntity>) query.execute();
		return problemEntities;
	}

	public ProblemEntity getProblemById(Long id) {
		ProblemEntity problem = null;
		List<ProblemEntity> problemEntities = getProblems();
		for (ProblemEntity entity : problemEntities) {
			if (entity.getId().equals(id)) {
				problem = entity;
			}
		}
		return problem;
	}
 
}
