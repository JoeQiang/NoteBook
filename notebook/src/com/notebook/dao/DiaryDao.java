package com.notebook.dao;

import java.util.List;

import com.notebook.pojo.User;

public class DiaryDao extends BaseDao {
	
	public User findById(int id) {
		String sql = "select * from t_user where id = ?";
		return (User) super.findOne(sql, id);
	}
	
	public User findByNameOrNickName(String nickname, String name) {
		String sql = "select * from t_user where nickname = ? or name=?";
		return (User) super.findOne(sql, nickname,name);
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
