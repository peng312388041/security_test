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
import com.agilet.model.TestEntity;
import com.agilet.server.AnswerService;
import com.agilet.server.ProblemService;
import com.agilet.server.TestService;
import com.agilet.util.DateTimeUtil;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ProblemServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TestService testService = new TestService();

		String action = request.getParameter("action");
		if (action.equals("add")) {
			TestEntity testEntity = new TestEntity();
			testEntity.setActive(true);
			testEntity.setName(request.getParameter("name"));
			testEntity.setBeginDate(DateTimeUtil.String2Time(
					request.getParameter("begintime")).getTime());
			testEntity.setEndDate(DateTimeUtil.String2Time(
					request.getParameter("endtime")).getTime());
			testEntity.setTotalTime(Integer.parseInt(request
					.getParameter("totaltime")));
			testEntity.setUserTestes(null);
			testService.add(testEntity);
		} else if (action.equals("list")) {
			TestService service = new TestService();
			List<TestEntity> testEntities = new ArrayList<TestEntity>();
			testEntities = service.getTestes();
			request.getSession().setAttribute("testEntities", testEntities);
			response.sendRedirect("/admin/jsp/test.jsp");
		}
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

}
