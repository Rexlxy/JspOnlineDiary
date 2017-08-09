package com.rex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rex.model.Diary;
import com.rex.model.PageBean;
import com.rex.util.DateUtil;

public class DiaryDao {

	// get the list of diary objects
	public List<Diary> getDiaries(Connection con, PageBean pageBean) throws SQLException, ParseException{
		List<Diary> diaryList = new ArrayList<Diary>();
		StringBuffer stringBuffer = new StringBuffer("select * from t_diary t1,t_diarytype t2 where t1.typeId=t2.diaryTypeId ");
		stringBuffer.append("order by t1.releaseDate DESC");
		// only query limit amount of items
		if(pageBean != null){
			stringBuffer.append(" limit "+pageBean.getStartIndex()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(stringBuffer.toString());
		ResultSet rs = pstmt.executeQuery();
		//add all Diary into the List
		while(rs.next()){
			Diary diary = new Diary();
			diary.setDiaryId(rs.getInt("diary_Id"));
			diary.setContent(rs.getString("content"));
			diary.setTitle(rs.getString("title"));
			diary.setTypeId(Integer.parseInt(rs.getString("typeId")));
			diary.setReleaseDate(DateUtil.StringToDate(rs.getString("releaseDate"), "yyyy-MM-dd HH:mm:ss"));
			diaryList.add(diary);
		}
		return diaryList;
	}
	
	//get the number of diaries in the table
	public int getDiaryCount(Connection con) throws SQLException{
		String sql = "select count(*) as totalCount from t_diary t1,t_diarytype t2 where t1.typeId=t2.diaryTypeId";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("totalCount");
		}else {
			return 0;
		}
	}
	
	public List<Diary> diaryCountList(Connection con) throws SQLException{
		List<Diary> diaryCountList = new ArrayList<Diary>();
		String sql = "SELECT DATE_FORMAT(releaseDate, '%Y年%m月') AS releaseDateStr, COUNT(DATE_FORMAT(releaseDate, '%Y年%m月')) AS total FROM t_diary GROUP BY DATE_FORMAT(releaseDate, '%Y年%m月') ORDER BY releaseDate DESC";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			Diary diary = new Diary();
			diary.setReleaseDateStr(rs.getString("releaseDateStr"));
			diary.setDiaryCount(rs.getInt("total"));
			diaryCountList.add(diary);
		}
		return diaryCountList;
	}
	
	public Map<Integer, String> getTypeMap(Connection con) throws SQLException{
		Map<Integer, String> typeMap = new HashMap<>();
		String sql = "select * from t_diarytype";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			typeMap.put(rs.getInt("diaryTypeId"), rs.getString("typeName"));
		}
		return typeMap;
	}
}
