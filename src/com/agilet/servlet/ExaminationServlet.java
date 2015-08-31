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
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class ExaminationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ProblemService problemService = new ProblemService();
	private static AnswerService answerService = new AnswerService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int totalProblem = Integer.parseInt(request
				.getParameter("totalProblem"));
		int totalScore = 0;
		List<Integer> wrongList = new ArrayList<Integer>();
		for (int i = 0; i < totalProblem; i++) {
			// 取当前考题
			String problemKey = request.getParameter("question" + (i + 1));
			ProblemEntity problem = problemService.getProblemByKey(problemKey);
			// 取当前考题所有答案
			String answers[] = request.getParameterValues("answer" + (i + 1));

			// 判断当前考题是否正确
			// 如果用户选项个数与标准答案相同，且用户选项均正确，则当前考题答案正确
			// 取标准答案正确选项个数
			int rightCount = 0;
			for (String string : problem.getAnswers()) {
				AnswerEntity answerEntity = answerService
						.getAnswerByKey(string);
				if (answerEntity.isResult()) {
					rightCount++;
				}

			}
			boolean result = true; // 保存当前考题是否正确
			if (answers!=null&&answers.length > 0) {
				for (String string : answers) {
					AnswerEntity answerEntity = answerService
							.getAnswerByKey(string);
					if (!answerEntity.isResult()) {
						result = false;
					}

				}
				if (rightCount != answers.length)

				{
					result = false;
				}
				if (result) {
					totalScore += problem.getScore();
				} else {
					wrongList.add(i + 1);
				}
			}
			//如果没有选，则错误 
			else{
				result=false;
				wrongList.add(i + 1);
			}
		}
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("totalScore", totalScore);
			jsonObject.put("wrongList", wrongList);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write(jsonObject.toString());
		// request.getRequestDispatcher("/front/index.jsp").forward(request,
		// response);
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

}
