package com.notebook.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.notebook.dao.UserDao;
import com.notebook.pojo.User;
import com.notebook.utils.DbUtil;

public class UserBiz{
	UserDao dao = new UserDao();
	private static UserBiz biz = new UserBiz();
	
	private UserBiz(){}
	
	public static UserBiz getBiz(){
		return biz;	
	}
	
	public boolean delete(int id) {
		int rows = dao.delete(id);
		boolean flag = false;
		if (rows>0) {
			flag = true;
		}
		return flag;
	}

	public Object find(int id) {
		return dao.findById(id);
	}

	public List findAll() {
		return dao.findAll();
	}

	public boolean save(Object obj) {
		int rows = dao.save(obj);
		boolean flag = false;
		if (rows>0) {
			flag = true;
		}
		return flag;
	}

	public boolean update(Object obj) {
		int rows = dao.update(obj);
		boolean flag = false;
		if (rows>0) {
			flag = true;
		}
		return flag;
	}

	public boolean login(String loginName, String password) {
		return dao.login(loginName, password);
	}
	
	public boolean regit(String nickname,String name){
		 return dao.findByNameOrNickName(nickname, name)!=null;
	}
	
	public String getNickName(String name){
		return dao.findByNickName(name).getNickname();
	}
	public String getNickNameByUserID(int userID){
		String nickName="";
		String querySql="select nickname from t_user where id=?";	
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=DbUtil.getCon();
			ps=conn.prepareStatement(querySql);
			ps.setInt(1, userID);
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
}
