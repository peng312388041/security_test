package com.agilet.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.agilet.util.DateTime;
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

		TestService testService = new TestService();
		if (request.getMethod().equals("GET")) {
			System.out.println("第一次访问，get方法！");
			response.sendRedirect("/admin/admin.htm");
		} else {
			String method = request.getParameter("action");

			// ----------------------如果是增加考试
			if (method.equals("add")) {
				String beginDate = request.getParameter("begintime");
				String beginDateString = beginDate.substring(0, 9) + " "
						+ "00:00:00";

				Date beginDateTime = DateTime.String2Time(beginDateString);

				String endDate = request.getParameter("endtime");
				String endDateString = endDate.substring(0, 9) + " "
						+ "24:00:00";
				Date endDateTime = DateTime.String2Time(endDateString);
				String name = request.getParameter("name");
				String totalTime = request.getParameter("totaltime");

				TestEntity testEntity = new TestEntity();
				testEntity.setActive(true);
				testEntity.setBeginDate(beginDateTime.getTime());
				testEntity.setEndDate(endDateTime.getTime());
				testEntity.setName(name);
				testEntity.setTotalTime(Integer.parseInt(totalTime));
				testService.add(testEntity);
				response.sendRedirect("/admin/test?action=list");
			}

			// ----------------------如果是列出考试清单
			else if (method.equals("list")) {
				List<TestEntity> testEntities = testService.getTestes();

				JSONArray jsonArray = new JSONArray();

				for (TestEntity testEntity : testEntities) {
					JSONObject jsonObject = new JSONObject();
					try {
						jsonObject.put("name", testEntity.getName());
						jsonObject.put("startdate", testEntity.getBeginDate());
						jsonObject.put("enddate", testEntity.getEndDate());
						jsonObject.put("totaltime", testEntity.getTotalTime());
						jsonArray.put(jsonObject);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				PrintWriter pw = response.getWriter();
				System.out.println(jsonArray.toString());
				pw.write(jsonArray.toString());

			}

			System.out.println("不是第一次访问，get方法！");
		}
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

}
