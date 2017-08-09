package com.rex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rex.model.DiaryType;

public class DiaryTypeDao {

	public List<DiaryType> diaryTypeCountList(Connection con) throws SQLException{
		List<DiaryType> diaryTypeCountList = new ArrayList<DiaryType>();
		String sql = "SELECT diaryTypeId,typeName,COUNT(diary_Id) AS total FROM t_diary a RIGHT JOIN t_diarytype b ON a.typeId=b.diaryTypeId GROUP BY typeName;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		//add all Diary into the List
		while(rs.next()){
			DiaryType diaryType = new DiaryType();
			diaryType.setDiaryCount(rs.getInt("total"));
			diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
			diaryType.setTypeName(rs.getString("typeName"));
			diaryTypeCountList.add(diaryType);
		}
		return diaryTypeCountList;
	}
}
