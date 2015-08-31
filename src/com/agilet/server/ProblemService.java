package com.agilet.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.agilet.model.AnswerEntity;
import com.agilet.model.ProblemEntity;
import com.agilet.model.TestEntity;
import com.agilet.util.PMF;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class ProblemService {
	private static TestService testService = new TestService();
	private static AnswerService answerService=new AnswerService();
	@SuppressWarnings("unused")
	private DatastoreService datastoreService = DatastoreServiceFactory
			.getDatastoreService();
	private static PersistenceManager persistenceManager = PMF.getInstance()
			.getPersistenceManager();

	public void add(ProblemEntity problem) {
		persistenceManager.makePersistent(problem);
	}

	public void delete(ProblemEntity problem) {
		for(String string :problem.getAnswers())
		{
			AnswerEntity answerEntity=answerService.getAnswerByKey(string);
			answerService.delete(answerEntity);
		}
		persistenceManager.deletePersistent(problem);
		 
	}
 

	public ProblemEntity getProblemByKey(String key) {
		return (ProblemEntity) persistenceManager.getObjectById(
				ProblemEntity.class, key);

	}

	public List<ProblemEntity> getProblems(String testEntityKey) {
		TestEntity testEntity = testService.getTestById(testEntityKey);
		List<ProblemEntity> problemEntities = new ArrayList<ProblemEntity>();

		List<String> problems = testEntity.getProblems();
		for (String key : problems) {
			ProblemEntity problem = new ProblemEntity();
			problem = (ProblemEntity) persistenceManager.getObjectById(
					ProblemEntity.class, key);
			problemEntities.add(problem);
		}
		return problemEntities;
	}

	public boolean inDataBase(ProblemEntity problemEntity) {
		List<TestEntity> testEntities = testService.getTestes();
		for (TestEntity testEntity : testEntities) {
			List<String> problemEntities = testEntity.getProblems();
			for (String problemKey : problemEntities) {
				if (problemEntity.getKey().equals(problemKey)) {
					return true;
				}
			}
		}
		return false;
	}

}
