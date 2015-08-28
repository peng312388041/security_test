package com.agilet.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agilet.model.AnswerEntity;
import com.agilet.model.ProblemEntity;
import com.agilet.model.TestEntity;
import com.agilet.server.AnswerService;
import com.agilet.server.ProblemService;
import com.agilet.server.TestService;
import com.agilet.util.PMF;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class AdminTestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unused")
		PersistenceManager persistenceManager = PMF.getInstance()
				.getPersistenceManager();
		TestService testService = new TestService();
		AnswerService answerService = new AnswerService();
		ProblemService problemService = new ProblemService();
		String method = request.getParameter("action");

		// ----------------------如果是增加考试
		if (method.equals("add")) {
			String beginDate = request.getParameter("begintime");
			String beginDateString = beginDate.substring(0, 10);

			String endDate = request.getParameter("endtime");
			String endDateString = endDate.substring(0, 10);

			String name = request.getParameter("name");
			String totalTime = request.getParameter("totaltime");

			TestEntity testEntity = new TestEntity();

			testEntity.setActive(true);
			testEntity.setBeginDate(beginDateString);
			testEntity.setEndDate(endDateString);
			testEntity.setName(name);
			testEntity.setTotalTime(Integer.parseInt(totalTime));
			testEntity.setUserTestes(null);
			testService.add(testEntity);
			request.getRequestDispatcher("/admin/test?action=list").forward(
					request, response);
		}

		// ----------------------如果是列出考试清单
		else if (method.equals("list")) {

			List<TestEntity> testEntities = new ArrayList<TestEntity>();
			testEntities = testService.getTestes();
			request.getSession().setAttribute("testEntities", testEntities);

			// response.sendRedirect("/admin/jsp/test.jsp");
			request.getRequestDispatcher("/admin/jsp/test.jsp").forward(
					request, response);

		}

		else if (method.equals("delete")) {

			String testIdList[] = request.getParameterValues("testid");
			for (int i = 0; i < testIdList.length; i++) {
				TestEntity testEntity = testService.getTestById(testIdList[i]);
				testService.delete(testEntity);
			}
			List<TestEntity> testEntities = new ArrayList<TestEntity>();
			testEntities = testService.getTestes();
			request.getSession().setAttribute("testEntities", testEntities);
			// response.sendRedirect("/admin/jsp/test.jsp");
			request.getRequestDispatcher("/admin/test?action=list").forward(
					request, response);

		} else if (method.equals("edit")) {

			String key = request.getParameter("testid");
			TestEntity testEntity = testService.getTestById(key);
			JSONArray jsonArray = new JSONArray();

			if (testEntity.getProblems() != null) {
				int jsonID = 1;
				for (String problem : testEntity.getProblems()) {
					JSONObject jsonObject = new JSONObject();
					ProblemEntity problemEntity = new ProblemEntity();
					problemEntity = problemService.getProblemByKey(problem);
					try {
						jsonObject.put("problemTitle",
								problemEntity.getContent());
						jsonObject.put("score", problemEntity.getScore());
						int i = 1;
						for (String answer : problemEntity.getAnswers()) {
							AnswerEntity answerEntity = new AnswerEntity();
							answerEntity = answerService.getAnswerByKey(answer);
							jsonObject.put("answer" + i,
									answerEntity.getContent());
							jsonObject.put("rightAnswer" + i,
									answerEntity.isResult());
							jsonObject.put("id", jsonID);
							i++;
						}
						jsonArray.put(jsonObject);
						jsonID++;
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			}
			request.getSession().setAttribute("test", testEntity);
			request.getSession().setAttribute("json", jsonArray.toString());
			request.getRequestDispatcher("/admin/jsp/edittest.jsp").forward(
					request, response);
		} else if (method.equals("update")) {
			// 转发给list
			// 从客户端取得所有考题信息，同步更新到保存到数据库
			JSONArray jsonArray = null;
			try {
				jsonArray = new JSONArray(request.getParameter("problems"));
			} catch (JSONException e) {
				e.printStackTrace();
			}

			String testID = request.getParameter("testid");
			TestEntity testEntity = testService.getTestById(testID);
			testEntity.setName(request.getParameter("name"));
			testEntity.setBeginDate(request.getParameter("begintime")
					.substring(0, 10));
			testEntity.setEndDate(request.getParameter("endtime").substring(0,
					10));
			testEntity.setTotalTime(Integer.parseInt(request
					.getParameter("totaltime")));

			List<String> problems = new ArrayList<String>();

			for (int i = 0; i < jsonArray.length(); i++) {
				ProblemEntity problemEntity = new ProblemEntity();
				JSONObject object = null;
				try {
					object = jsonArray.getJSONObject(i);
					List<String> answers = new ArrayList<String>();

					for (int j = 1; j <= 4; j++) {
						AnswerEntity answerEntity = new AnswerEntity();
						boolean answerResult;
						String answerContent = "";
						answerContent = object.getString("answer" + j);
						answerResult = object.getBoolean("rightAnswer" + j);
						answerEntity.setContent(answerContent);
						answerEntity.setResult(answerResult);
						answerService.add(answerEntity);
						answers.add(answerEntity.getKey());
					}
					problemEntity.setAnswers(answers);
					problemEntity.setActive(true);
					problemEntity.setContent(object.getString("problemTitle"));
					problemEntity.setScore(Long.parseLong(object
							.getString("score")));
					problemService.add(problemEntity);
					problems.add(problemEntity.getKey());

				} catch (JSONException e1) {
					e1.printStackTrace();
				}

			}
			testEntity.setProblems(problems);
			String data = "success";
			// 删除已经从考试中脱离的考题及选项
			response.getWriter().write(data);

		} else {
			System.out.println("没有匹配");
		}
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		super.service(arg0, arg1);
	}
}
