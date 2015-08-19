package com.agilet.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agilet.model.AdminUserEntity;
import com.agilet.server.AdminUserService;
import com.google.appengine.api.search.query.ExpressionParser.index_return;

public class AdminServlet extends HttpServlet {

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
		// AdminUserEntity adminUserEntity = new AdminUserEntity();
		// AdminUserService service = new AdminUserService();
		// adminUserEntity.setActive(true);
		// adminUserEntity.setAuthority(0);
		// adminUserEntity.setUserName("pengchong");
		// adminUserEntity.setPassWord("peng000");
		// service.add(adminUserEntity);
		//
		// List<AdminUserEntity> entityList = service.getAdminUserEntities();
		// System.out.println(entityList.size());
		// for (AdminUserEntity entity : entityList) {
		// System.out.println(entity.getUserName());
		// }

		if (req.getMethod().equals("GET")) {
			System.out.println("第一次访问，get方法！");
			resp.sendRedirect("admin/index.html");
		} else {
			System.out.println("不是第一次访问，get方法！");
		}
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

}
