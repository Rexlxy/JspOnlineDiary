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
import com.rex.util.StringUtil;

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
		else  if("preUpdate".equals(action)){
			preUpdate(req, resp);
		}
		else if("update".equals(action)){
			updateDiary(req,resp);
		}
		else if("save".equals(action)){
			saveDiary(req, resp);
		}
		else if("delete".equals(action)){
			deleteDiary(req, resp);
		}
	}
	
	private void saveDiary(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Connection con = null;
		String title = req.getParameter("diary_title");
		String content = req.getParameter("content");
		String typeId = req.getParameter("typeId");
		Diary diary = new Diary(title, content, Integer.parseInt(typeId));
		try {
			con = dbUtil.getConnection();
			int saveNum = 0;
			saveNum = diaryDao.saveDiary(con, diary);
			if(saveNum > 0){
				System.out.println("成功写入diary");
				req.getRequestDispatcher("main?all=true").forward(req, resp);
			} else {
				System.out.println("写入失败");
				req.setAttribute("diary", diary);
				req.setAttribute("mainPage", "/diary/diaryCreate.jsp");
				req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
			};
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
	
	private void deleteDiary(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Connection con = null;
		String diaryId = req.getParameter("diaryId");
		try {
			con = dbUtil.getConnection();
			int resultNum = 0;
			if(StringUtil.isNotEmpty(diaryId)){
				resultNum = diaryDao.removeDiaryById(con, diaryId);
			}
			if(resultNum > 0){
				System.out.println("成功删除日志");
			} else {
				System.out.println("删除日志失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("main");
		
	}
	
	private void preUpdate(HttpServletRequest req, HttpServletResponse resp){
		Connection con = null;
		String diaryId = req.getParameter("diaryId");
		try {
			con = dbUtil.getConnection();
			Diary diary  = diaryDao.getDiaryById(con, diaryId);
			req.setAttribute("diaryId", diaryId);
			req.setAttribute("diary", diary);
			req.setAttribute("mainPage", "/diary/diaryUpdate.jsp");
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void updateDiary(HttpServletRequest req, HttpServletResponse resp){
		Connection con = null;
		//日记id
		String diaryId = req.getParameter("diaryId");
		//更新后的内容
		String title = req.getParameter("diary_title");
		String content = req.getParameter("content");
		String typeId = req.getParameter("typeId");
		//新的diary object
		Diary diary = new Diary(title, content, Integer.parseInt(typeId));
		diary.setDiaryId(Integer.parseInt(diaryId));
		try {
			con = dbUtil.getConnection();
			int resultNum = 0;
			resultNum = diaryDao.update(con, diary);
			if(resultNum > 0){
				System.out.println("更新日志成功");
				//转到更新完的日志
				req.setAttribute("diaryToShow", diary);
				req.setAttribute("mainPage", "/diary/diaryShow.jsp");
				req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
				
			} else {
				System.out.println("更新日志失败");
				req.setAttribute("diary", diary);
				req.setAttribute("mainPage", "/diary/diaryUpdate.jsp");
				req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
