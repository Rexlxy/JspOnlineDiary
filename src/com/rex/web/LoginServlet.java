package com.rex.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.dao.UserDao;
import com.rex.model.User;
import com.rex.util.DbUtil;

public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		Connection con = null;
		try {
			con = dbUtil.getConnection();
			User user = new User(userName, password);
			//check if the user is existing in the database
			User currentUser = userDao.login(con, user);
			if(currentUser == null){
				System.out.println("Can't find the user");
				System.out.println("User name:"+userName+" Password:"+password);
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} else {
				//store the user into session
				System.out.println("Successfully login!");
				System.out.println("User name:"+userName+" Password:"+password);
				session.setAttribute("currentUser", currentUser);
				resp.sendRedirect("main.jsp");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {	
				dbUtil.closeConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Database: Failed to close");
				e.printStackTrace();
			}
		}
		
	}
	
}
