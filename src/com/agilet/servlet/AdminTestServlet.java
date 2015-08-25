package com.agilet.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agilet.model.TestEntity;
import com.agilet.server.TestService;
import com.agilet.util.DateTimeUtil;

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

		TestService testService = new TestService();

		String method = request.getParameter("action");

		// ----------------------如果是增加考试
		if (method.equals("add")) {
			String beginDate = request.getParameter("begintime");
			String beginDateString = beginDate.substring(0, 9) + " "
					+ "00:00:00";

			Date beginDateTime = DateTimeUtil.String2Time(beginDateString);

			String endDate = request.getParameter("endtime");
			String endDateString = endDate.substring(0, 9) + " " + "24:00:00";
			Date endDateTime = DateTimeUtil.String2Time(endDateString);
			String name = request.getParameter("name");
			String totalTime = request.getParameter("totaltime");

			TestEntity testEntity = new TestEntity();
			testEntity.setActive(true);
			testEntity.setBeginDate(beginDateTime.getTime());
			testEntity.setEndDate(endDateTime.getTime());
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
				Long id = Long.parseLong(testIdList[i]);
				testService.delete(id);
			}
			List<TestEntity> testEntities = new ArrayList<TestEntity>();
			testEntities = testService.getTestes();
			request.getSession().setAttribute("testEntities", testEntities);
			// response.sendRedirect("/admin/jsp/test.jsp");
			request.getRequestDispatcher("/admin/jsp/test.jsp").forward(
					request, response);

		} else if (method.equals("edit")) {

			Long id = Long.parseLong(request.getParameter("testid"));
			TestEntity testEntity = testService.getTestById(id);
			request.getSession().setAttribute("test", testEntity);
			request.getRequestDispatcher("/admin/jsp/edittest.jsp").forward(
					request, response);
		} else if (method.equals("udpate")) {
//转发给list
			
			
			request.getRequestDispatcher("/admin/jsp/test.jsp").forward(
					request, response);
		}
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}
}
