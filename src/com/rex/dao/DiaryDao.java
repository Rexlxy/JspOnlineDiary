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
import com.rex.util.DateUtil;

public class DiaryDao {

	public List<Diary> getDiaries(Connection con) throws SQLException, ParseException{
		List<Diary> diaryList = new ArrayList<Diary>();
		StringBuffer stringBuffer = new StringBuffer("select * from t_diary t1,t_diarytype t2 where t1.typeId=t2.diaryTypeId ");
		stringBuffer.append("order by t1.releaseDate DESC");
		PreparedStatement pstmt = con.prepareStatement(stringBuffer.toString());
		ResultSet rs = pstmt.executeQuery();
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
