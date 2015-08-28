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

import com.agilet.model.ProblemEntity;
import com.agilet.model.TestEntity;
import com.agilet.server.ProblemService;
import com.agilet.server.TestService;

public class FrontTestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static TestService testservice=new TestService();
	private static ProblemService problemService=new ProblemService();
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 List<ProblemEntity> problems=new ArrayList<ProblemEntity>();
		 TestEntity testEntity=testservice.getTestes().get(0);
		 problems= problemService.getProblems(testservice.getTestes().get(0).getKey());
		 request.getSession().setAttribute("problems", problems);
		 
		 request.getRequestDispatcher("/front/index.jsp").forward(
					request, response);
		}
	 

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

}
