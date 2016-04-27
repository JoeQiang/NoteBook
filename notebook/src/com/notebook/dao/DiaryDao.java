package com.notebook.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.notebook.pojo.DiaryDomain;
import com.notebook.utils.DbUtil;

public class DiaryDao{
//	²»¼Ì³ÐBaseDao--hm
	public List<DiaryDomain> getDiarys(int userID) {
		List<DiaryDomain> diarys = new ArrayList<DiaryDomain>();
		String querySql="select * from diary where userID=?";		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getCon();
			ps=conn.prepareStatement(querySql);
			ps.setInt(1, userID);
			rs=ps.executeQuery();
			while(rs.next()){
				DiaryDomain list=new DiaryDomain();
				list.setId(rs.getInt("id"));
				list.setItem(rs.getString("item"));
				list.setDate(rs.getString("date"));
				list.setContent(rs.getString("detial"));
				list.setUserID(rs.getInt("userID"));
				diarys.add(list);
			}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DbUtil.close(rs, ps, conn);
			}
		return diarys;
	}
	public List<String> getItemByUserID(int userID){
		List<String> items = new ArrayList<String>();
		String querySql="select item from diary where userID=?";		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getCon();
			ps=conn.prepareStatement(querySql);
			ps.setInt(1, userID);
			rs=ps.executeQuery();
			while(rs.next()){
				String item=rs.getString("item");
				items.add(item);
			}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DbUtil.close(rs, ps, conn);
			}
		return items;
	}
	public void addDiary(DiaryDomain diary) throws SQLException{
		Connection conn=null;
		PreparedStatement pstmt=null;
		conn=null;
		StringBuffer sql= new StringBuffer("insert into diary(item,date,detial,userID) values(?,?,?,?)");
		try{
			conn = DbUtil.getCon();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, diary.getItem());
			pstmt.setString(2, diary.getDate());
			pstmt.setString(3, diary.getContent());
			pstmt.setInt(4, diary.getUserID());
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			pstmt.close();
			conn.close();
		}
	}
	public ArrayList<DiaryDomain> getDiarysByArg(String detial,String depend,int userID) {
		ArrayList<DiaryDomain> diarys=new ArrayList<DiaryDomain>();
		String querySql="select * from diary where "+depend+" like '%"+detial+"%' and userID="+userID+";";		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getCon();
			ps=conn.prepareStatement(querySql);
			rs=ps.executeQuery();
			while(rs.next()){
				DiaryDomain list=new DiaryDomain();
				list.setId(rs.getInt("id"));
				list.setItem(rs.getString("item"));
				list.setDate(rs.getString("date"));
				list.setContent(rs.getString("detial"));
				list.setUserID(rs.getInt("userID"));
				diarys.add(list);
			}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DbUtil.close(rs, ps, conn);
			}
		return diarys;
	}
}
