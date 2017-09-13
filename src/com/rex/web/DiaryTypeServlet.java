package com.rex.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.dao.DiaryTypeDao;
import com.rex.model.DiaryType;
import com.rex.util.DbUtil;
import com.rex.util.StringUtil;

public class DiaryTypeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DiaryTypeDao diaryTypeDao = new DiaryTypeDao();
	private DbUtil dbUtil = new DbUtil();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req,  resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if("showTypes".equals(action)){
			displayTypes(req, resp);
		} 
		else if("delete".equals(action)){
			deleteDiaryType(req, resp);
		}
		else if("modify".equals(action)){
			modifyDiaryType(req, resp);
		}
		else if("preAdd".equals(action)){
			req.setAttribute("mainPage", "/diary/diaryTypeAdd.jsp");
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		}
		else if("addType".equals(action)){
			addDiaryType(req, resp);
		}
	}
	
	//显示所有当前的类别
	private void displayTypes(HttpServletRequest req, HttpServletResponse resp){
		Connection con = null;
		
		try {
			con = dbUtil.getConnection();
			List<DiaryType> diaryTypeList = diaryTypeDao.diaryTypeList(con);
			req.setAttribute("diaryTypeList", diaryTypeList);
			req.setAttribute("mainPage", "/diary/diaryTypeManage.jsp");
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteDiaryType(HttpServletRequest req, HttpServletResponse resp){
		String diaryTypeId = req.getParameter("diaryTypeId");
		Connection con = null;
		try {
			con = dbUtil.getConnection();
			int deleteNum = 0;
			if(StringUtil.isNotEmpty(diaryTypeId)){
				DiaryType diaryType = new DiaryType();
				diaryType.setDiaryTypeId(Integer.parseInt(diaryTypeId));
				deleteNum = diaryTypeDao.deleteDiaryType(con, diaryType);
			}
			if(deleteNum > 0){
				System.out.println("删除日志类别成功");
			} else {
				System.out.println("删除日志类别失败");
			}
			req.getRequestDispatcher("diaryTypeManage?action=showTypes").forward(req, resp);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void modifyDiaryType(HttpServletRequest req, HttpServletResponse resp){
		
	}
	
	private void addDiaryType(HttpServletRequest req, HttpServletResponse resp){
		String newTypeName = req.getParameter("newDiaryTypeName");
		Connection con = null;
		try {
			con = dbUtil.getConnection();
			int resultNum = 0;
			if(StringUtil.isNotEmpty(newTypeName)){
				resultNum = diaryTypeDao.addDiaryType(con, new DiaryType(newTypeName));
			}
			if(resultNum > 0){
				System.out.println("成功添加新的类别："+newTypeName);				
			} else {
				System.out.println("添加新的类别失败: "+newTypeName);				
			}
			req.getRequestDispatcher("diaryTypeManage?action=showTypes").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
