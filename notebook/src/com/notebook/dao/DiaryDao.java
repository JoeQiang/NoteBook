package com.notebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Statement;
import com.notebook.pojo.DiaryDomain;
import com.notebook.utils.DbUtil;

public class DiaryDao{
//	不继承BaseDao--hm
	public DiaryDomain getDiaryById(int id) {
		String querySql="select * from diary where id=?";		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		DiaryDomain diary=new DiaryDomain();
		try{
			conn=DbUtil.getCon();
			ps=conn.prepareStatement(querySql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				diary.setId(rs.getInt("id"));
				diary.setItem(rs.getString("item"));
				diary.setDate(rs.getString("date"));
				diary.setContent(rs.getString("detial"));
				diary.setUserID(rs.getInt("userID"));
			}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DbUtil.close(rs, ps, conn);
			}
		return diary;
	}
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
		String querySql="select distinct item from diary where userID=?";	
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
	/**
	 * @param userID
	 * @return判断该用户是否新建过科目
	 */
	public boolean hasItem(int userID){
		String querySql="select count(*) as icount from diary where userID=?;";		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean hasItem=false;
		try{
			conn=DbUtil.getCon();
			ps=conn.prepareStatement(querySql);
			ps.setInt(1, userID);
			rs=ps.executeQuery();
			while(rs.next()){
				int icount=rs.getInt("icount");
				if(icount!=0){
					hasItem=true;
				}
			}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DbUtil.close(rs, ps, conn);
			}
		return hasItem;
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
		if(depend=="" || depend==null){
			querySql="select * from diary where detial like '%"+detial+"%' and userID="+userID+";";	
		}
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
				list.setTitle(rs.getString("title"));
				diarys.add(list);
			}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DbUtil.close(rs, ps, conn);
			}
		return diarys;
	}
	public ArrayList<DiaryDomain> getDetialByAll(int userID,String item,String date) {
		String querySql="select * from diary where item=? and date=? and userID=?;";		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<DiaryDomain> diarys=new ArrayList<DiaryDomain>();
		try{
			conn=DbUtil.getCon();
			ps=conn.prepareStatement(querySql);
			ps.setString(1, item);
			ps.setString(2, date);
			ps.setInt(3, userID);
			rs=ps.executeQuery();
			while(rs.next()){
				DiaryDomain list=new DiaryDomain();
				list.setId(rs.getInt("id"));
				list.setItem(rs.getString("item"));
				list.setDate(rs.getString("date"));
				list.setContent(rs.getString("detial"));
				list.setUserID(rs.getInt("userID"));
				list.setTitle(rs.getString("title"));
				diarys.add(list);
			}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DbUtil.close(rs, ps, conn);
			}
		return diarys;
	}
	public void updateDiarysById(int id,String detial) {
		String querySql="update diary set detial=? where id=?;";		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getCon();
			ps=conn.prepareStatement(querySql);
			ps.setString(1, detial);
			ps.setInt(2, id);
			int i=ps.executeUpdate();	
			System.out.println("保存成功！"+i);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DbUtil.close(rs, ps, conn);
			}
	}
	/**
	 * @param diary
	 * 保存笔记并且返回主键
	 * @return
	 * @throws SQLException 
	 */
	public int addDiaryReKey(DiaryDomain diary) throws SQLException{
		int key=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sql= new StringBuffer("insert into diary(item,date,detial,userID,title) values(?,?,?,?,?)");
		try{
			conn = DbUtil.getCon();
			pstmt = conn.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, diary.getItem());
			pstmt.setString(2, diary.getDate());
			pstmt.setString(3, diary.getContent());
			pstmt.setInt(4, diary.getUserID());
			pstmt.setString(5, diary.getTitle());
			pstmt.executeUpdate();
			//获得插入主键
			rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				key=rs.getInt(1);
				System.out.println("主键"+key);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			pstmt.close();
			conn.close();
		}
		return key;		
	}
	public void delDiaryById(int diaryID,int userID) {
		String querySql="delete from diary where id=? and userID=?;";		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getCon();
			ps=conn.prepareStatement(querySql);
			ps.setInt(1, diaryID);
			ps.setInt(2, userID);
			ps.executeUpdate();	
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DbUtil.close(rs, ps, conn);
			}
	}
}
