package com.notebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.notebook.pojo.DiaryDomain;
import com.notebook.pojo.User;
import com.notebook.utils.DbUtil;

public class UserDao extends BaseDao {
	
	public User findById(int id) {
		String sql = "select * from t_user where id = ?";
		return (User) super.findOne(sql, id);
	}
	
	public User findByNameOrNickName(String nickname, String name) {
		String sql = "select * from t_user where nickname = ? or name=?";
		return (User) super.findOne(sql, nickname,name);
	}
	public int findByName(String name) {
		int userID=0;
		String querySql="select id from t_user where name=?";		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getCon();
			ps=conn.prepareStatement(querySql);
			ps.setString(1, name);
			rs=ps.executeQuery();
			while(rs.next()){
				userID=rs.getInt("id");
			}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DbUtil.close(rs, ps, conn);
			}
		return userID;
	}
	public String findNickNameById(int id) {
		String nickName="";
		String querySql="select nickname from t_user where id=?";		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getCon();
			ps=conn.prepareStatement(querySql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				nickName=rs.getString("nickname");
			}			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DbUtil.close(rs, ps, conn);
			}
		return nickName;
	}
	public User findByNickName(String name){
		String sql = "select * from t_user where name = ? ";
		return (User) super.findOne(sql, name);
	}
	
	public List findAll(){
		String sql = "select * from t_user";
		return super.findSome(sql);
	}
	
	public boolean login(String loginName, String password){
		String sql = "select * from t_user where name=? and password=?";
		User user = (User) super.findOne(sql, new Object[]{loginName, password});
		if (user != null) {
			return true;
		}
		return false;
	}
	
	public int save(User user){
		String sql = "insert into t_user(name,nickname,password) values(?,?,?)";
		return super.merge(sql, new Object[]{user.getName(),user.getNickname(),user.getPassword()});
	}
	
	public int update(User user){
//		String sql = "update t_user set name=?,login=?,password=? where id=?";
//		return super.merge(sql, user.getName(),user.getLogin(),user.getPassword(),user.getId());
		return super.update(user);
	}
	 
	public int delete(int id){
		String sql = "delete from t_user where id=?";
		return super.merge(sql, id);
		
	}
}
