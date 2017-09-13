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
	
	//得到diary的分类（日记分类管理）
	public List<DiaryType> diaryTypeList(Connection con) throws NumberFormatException, SQLException {
		List<DiaryType> diaryTypeList = new ArrayList<DiaryType>();
		String sql = "SELECT * from t_diarytype";
		PreparedStatement  pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			DiaryType diaryType = new DiaryType();
			diaryType.setTypeName(rs.getString("typeName"));
			diaryType.setDiaryTypeId(Integer.parseInt(rs.getString("diaryTypeId")));
			diaryTypeList.add(diaryType);
		}
		return diaryTypeList;
	}
	
	//增加日志类别
	public int addDiaryType(Connection con, DiaryType diaryType) throws SQLException{
		String sql = "INSERT INTO t_diarytype VALUES(NULL, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, diaryType.getTypeName());
		return pstmt.executeUpdate();
	}
	
	//删除日志类别
	public int deleteDiaryType(Connection con, DiaryType diaryType) throws SQLException{
		String sql = "DELETE FROM t_diarytype WHERE diaryTypeId="+diaryType.getDiaryTypeId();
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
