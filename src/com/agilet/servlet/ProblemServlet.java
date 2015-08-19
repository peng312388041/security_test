package com.agilet.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agilet.model.AnswerEntity;
import com.agilet.model.ProblemEntity;
import com.agilet.server.AnswerService;
import com.agilet.server.ProblemService;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ProblemServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		ProblemService problemService = new ProblemService();
		AnswerService answerService = new AnswerService();

		List<String> answers = new ArrayList<>();
		AnswerEntity answer = new AnswerEntity();

		answer.setContent("haha2");
		answer.setResult(true);
		Key key = KeyFactory.createKey(AnswerEntity.class.getSimpleName(), answer.getContent() + answer.isResult());
		String keyString = KeyFactory.keyToString(key);
		answer.setKey(keyString);
		answerService.add(answer);
		answers.add(keyString);

		AnswerEntity answer2 = new AnswerEntity();
		answer2.setContent("哈哈1");
		answer2.setResult(true);
		Key key2 = KeyFactory.createKey(AnswerEntity.class.getSimpleName(), answer2.getContent() + answer2.isResult());
		String keyString2 = KeyFactory.keyToString(key2);
		answer2.setKey(keyString2);
		answerService.add(answer2);
		answers.add(keyString2);
		// System.out.println("key: " + key);
		//
		// System.out.println("key-string: " + KeyFactory.keyToString(key));
		//
		// System.out.println("key-string-key: " +
		// KeyFactory.stringToKey(KeyFactory.keyToString(key)));

		ProblemEntity problemEntity = new ProblemEntity();
		problemEntity.setActive(true);
		problemEntity.setAnswers(answers);
		problemEntity.setContent("heihei");
		problemEntity.setScore(20l);
		problemService.add(problemEntity);

		List<ProblemEntity> problemList = problemService.getProblems();

		for (ProblemEntity entity : problemList) {
			System.out.println(entity.getContent());
			for (String string : entity.getAnswers()) {
				System.out.println(answerService.getAnswerByKey(string).getContent());
			}
		}
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

}
