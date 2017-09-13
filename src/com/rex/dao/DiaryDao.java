package com.rex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rex.model.Diary;
import com.rex.model.PageBean;
import com.rex.util.DateUtil;
import com.rex.util.StringUtil;

public class DiaryDao {

	// get the list of diary objects
	public List<Diary> getDiaries(Connection con, PageBean pageBean, Diary infoDiary) throws SQLException, ParseException{
		List<Diary> diaryList = new ArrayList<Diary>();
		StringBuffer stringBuffer = new StringBuffer("select * from t_diary t1,t_diarytype t2 where t1.typeId=t2.diaryTypeId"); //默认搜索所有
		
		//模糊搜索的情况
		if(StringUtil.isNotEmpty(infoDiary.getTitle())){
			stringBuffer = new StringBuffer("select * from t_diary t1 where title like '%"+infoDiary.getTitle()+"%'");
		}
		//if we want diaries according to diary type
		//按类别的情况
		if(infoDiary.getTypeId() !=-1 ){
			stringBuffer.append(" and typeId="+infoDiary.getTypeId());
		}
		//if we want diaries according to certain dates.
		//按时间的情况
		if(StringUtil.isNotEmpty(infoDiary.getReleaseDateStr())){
			stringBuffer.append(" and DATE_FORMAT(releaseDate, '%Y年%m月')='"+infoDiary.getReleaseDateStr()+"'");
		} 
		//stringBuffer.append(",t_diarytype t2 where t1.typeId=t2.diaryTypeId ");
		stringBuffer.append(" order by t1.releaseDate DESC");
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
	public int getDiaryCount(Connection con, Diary infoDiary) throws SQLException{
		String sql= "select count(*) as totalCount from t_diary t1,t_diarytype t2 where t1.typeId=t2.diaryTypeId";
		
		//模糊搜索的情况
		if(StringUtil.isNotEmpty(infoDiary.getTitle())){
			sql = "select count(*) as totalCount from t_diary where title like '%"+infoDiary.getTitle()+"%'";
		}
		//按时间的情况
		if(StringUtil.isNotEmpty(infoDiary.getReleaseDateStr())){
			 sql = "select count(*) as totalCount from t_diary where DATE_FORMAT(releaseDate, '%Y年%m月')='"+infoDiary.getReleaseDateStr()+"'";
		} 
		//按类别的情况
		if(infoDiary.getTypeId()!=-1){
			sql = "select count(*) as totalCount from t_diary where typeId="+infoDiary.getTypeId();
		} 
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("totalCount");
		}else {
			return 0;
		}
	}

	//得到根据时间分的日志信息
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

	//得到指定Id的日记
	public Diary getDiaryById(Connection con, String diaryId) throws SQLException, ParseException{
		Diary diary = new Diary();
		String sql = "select * from t_diary t1,t_diarytype t2 where t1.typeId=t2.diaryTypeId and t1.diary_Id="+diaryId;
		PreparedStatement pstmt = con.prepareStatement(sql);
		//pstmt.setString(1, diaryId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			diary.setContent(rs.getString("content"));		
			diary.setReleaseDate(DateUtil.StringToDate(rs.getString("releaseDate"), "yyyy-MM-dd HH:mm:ss"));
			diary.setTypeName(rs.getString("typeName"));
			diary.setTitle(rs.getString("title"));
			diary.setDiaryId(rs.getInt("diary_Id"));
		}
			return diary;
	}
	
	//存日记
	public int saveDiary(Connection con, Diary diary) throws SQLException{
		String sql = "insert into t_diary (title, content, typeId, releaseDate) values(?,?,?,?)";  //diary_Id, title, content, typeId, releaseDate
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, diary.getTitle());
		pstmt.setString(2, diary.getContent());
		pstmt.setString(3, Integer.toString(diary.getTypeId()));
		pstmt.setString(4, DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println("saveDiary:正在写入diary.....");
		return pstmt.executeUpdate();
	}
	
	//删除日记
	public int removeDiaryById(Connection con, String diaryId) throws SQLException{
		String sql = "DELETE FROM t_diary WHERE diary_Id="+diaryId;
		PreparedStatement pstmt = con.prepareStatement(sql);
		System.out.println("removeDiaryById:正在删除diary.....");
		return pstmt.executeUpdate();
	}
	
	//更新日记
	public int update(Connection con, Diary diary) throws SQLException{
		String sql = "UPDATE t_diary SET title=?, content=?, typeId=?, releaseDate=? WHERE diary_Id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, diary.getTitle());
		pstmt.setString(2, diary.getContent());
		pstmt.setString(3, Integer.toString(diary.getTypeId()));
		pstmt.setString(4, DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		pstmt.setString(5, Integer.toString(diary.getDiaryId()));
		System.out.println("update:正在更新diary.....");
		return pstmt.executeUpdate();
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
