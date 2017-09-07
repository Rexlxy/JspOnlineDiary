package com.rex.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.dao.DiaryDao;
import com.rex.dao.DiaryTypeDao;
import com.rex.model.Diary;
import com.rex.util.DbUtil;

public class DiaryActionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil = new DbUtil();
	DiaryDao diaryDao = new DiaryDao();
	DiaryTypeDao diaryTypeDao = new DiaryTypeDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if("showDiary".equals(action)){
			try {
				displayDiary(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if("preSave".equals(action)){
			req.setAttribute("mainPage", "/diary/diaryCreate.jsp");
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		}
		else if("save".equals(action)){
			saveDiary(req, resp);
		}
	}
	
	private void saveDiary(HttpServletRequest req, HttpServletResponse resp){
		Connection con = null;
		String title = req.getParameter("diary_title");
		String content = req.getParameter("content");
		String typeId = req.getParameter("typeId");
		Diary diary = new Diary(title, content, Integer.parseInt(typeId));
		try {
			con = dbUtil.getConnection();
			if(diaryDao.saveDiary(con, diary)){
				System.out.println("成功写入diary");
			} else {
				System.out.println("写入失败");
			};
			req.setAttribute("mainPage", "/diary/diaryShow.jsp");
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void displayDiary(HttpServletRequest req, HttpServletResponse resp){
		Connection con = null;
		String diaryId = req.getParameter("diaryId");
		try {
			con = dbUtil.getConnection();
			Diary diaryToShow = diaryDao.getDiaryById(con, diaryId);
			req.setAttribute("diaryToShow", diaryToShow);
			req.setAttribute("mainPage", "/diary/diaryShow.jsp");
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
