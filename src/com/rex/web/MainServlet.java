package com.rex.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.dao.DiaryDao;
import com.rex.dao.DiaryTypeDao;
import com.rex.model.Diary;
import com.rex.model.PageBean;
import com.rex.util.DbUtil;
import com.rex.util.PropertiesUtil;
import com.rex.util.StringUtil;

public class MainServlet extends HttpServlet {

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
		// get or set the page number
		String page = req.getParameter("page");
		String all = req.getParameter("all");
		String search_key = req.getParameter("search_key");
		String s_releaseDateStr = req.getParameter("s_releaseDateStr");
		String s_typeId = req.getParameter("s_typeId");
		HttpSession session = req.getSession();
		
		Diary diary = new Diary(); //the diary storing information what kind of diaries we want
		/*
		if(StringUtil.isEmpty(s_releaseDateStr)&&StringUtil.isEmpty(s_typeId)&&StringUtil.isEmpty(search_key)){
			clearSession(session);
		}
		*/
		//全部
		if("true".equals(all)){
			//模糊搜索
			if(StringUtil.isNotEmpty(search_key)){
				diary.setTitle(search_key);
			}
			session.setAttribute("search_key", search_key);
			//去除session存的时间和类别信息
			session.removeAttribute("releaseDateStr");
			session.removeAttribute("typeId");
		} else {
		//check if we need to get diaries according to releaseDate 
			//根据时间显示所有
		if(StringUtil.isNotEmpty(s_releaseDateStr)){
			s_releaseDateStr = new String(s_releaseDateStr.getBytes("ISO-8859-1"),"utf-8");
			diary.setReleaseDateStr(s_releaseDateStr);
			session.setAttribute("s_releaseDateStr", s_releaseDateStr);
			session.removeAttribute("s_typeId");
			session.removeAttribute("search_key");
		}
		
		//check if we need to get diaries according to types
		//根据类别显示所有
		if(StringUtil.isNotEmpty(s_typeId)){
			diary.setTypeId(Integer.parseInt(s_typeId));
			session.setAttribute("s_typeId", s_typeId);
			session.removeAttribute("s_releaseDateStr");
			session.removeAttribute("search_key");
		}
		
		//根据时间，翻页
		if(StringUtil.isEmpty(s_releaseDateStr)){
			Object o = session.getAttribute("s_releaseDateStr");
			if(o != null){
				diary.setReleaseDateStr((String)o);
			}
		}
		//根据类别，翻页
		if(StringUtil.isEmpty(s_typeId)){
			Object o = session.getAttribute("s_typeId");
			if(o != null){
				diary.setTypeId(Integer.parseInt((String)o));
			}
		}
		
		//模糊搜索，翻页
		if(StringUtil.isEmpty(search_key)){
			Object o = session.getAttribute("search_key");
			if(o != null){
				diary.setTitle((String)o);
			}
		}
		}
		Connection con = null;
		if(StringUtil.isEmpty(page)){
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		try {
			con = dbUtil.getConnection();
			//get total number of diaries
			int total = diaryDao.getDiaryCount(con, diary);
			String pageCode = this.genPagination(total, Integer.parseInt(page), pageBean.getPageSize());
			Map<Integer, String> typeMap = diaryDao.getTypeMap(con);
			session.setAttribute("diaryTypeCountList", diaryTypeDao.diaryTypeCountList(con));
			session.setAttribute("diaryCountList",diaryDao.diaryCountList(con));
			//set request attributes (pass to the page)
			req.setAttribute("diaryList", diaryDao.getDiaries(con, pageBean, diary));
			req.setAttribute("pageCode", pageCode);
			req.setAttribute("typeMap", typeMap);
			req.setAttribute("mainPage", "/diary/diaryList.jsp");
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void clearSession(HttpSession session){
		session.removeAttribute("s_releaseDateStr");
		session.removeAttribute("s_typeId");
		session.removeAttribute("search_key");
	}

	private String genPagination(int total, int curPage, int pageSize){
		int totalPage = total%pageSize==0?(total/pageSize):(total/pageSize)+1;
		StringBuffer code = new StringBuffer();
		//the beginning part
		code.append("<li><a href='main?page=1'><span>首页</span></a> </li>");
		if(curPage == 1){
			code.append("<li class='disabled'><span>上一页</span> </li>");
		} else {
			code.append("<li><a href='main?page="+(curPage-1)+"'><span>上一页</span></a> </li>");
		}

		for(int i=curPage-2; i<=curPage+2; i++){
			if(i<1 || i>totalPage){
				continue;
			}
			if(i==curPage){
				code.append("<li class='active'><span>"+i+"</span> </li>");
			} else {
				code.append("<li><a href='main?page="+i+"'><span>"+i+"</span></a> </li>");
			}
		}

		//the end part
		if(curPage == totalPage){
			code.append("<li class='disabled'><a href='#'><span>下一页</span></a> </li>");
		} else {
			code.append("<li><a href='main?page="+(curPage+1)+"'><span>下一页</span></a> </li>");
		}
		code.append("<li><a href='main?page="+totalPage+"'><span>尾页</span></a> </li>");

		return code.toString();
	}


}
