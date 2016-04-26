package com.notebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
