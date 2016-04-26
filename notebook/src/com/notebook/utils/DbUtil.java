package com.notebook.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

/**
 * @author hm
 *数据库工具类-20160426
 */
public class DbUtil {
	
	private DbUtil(){};
	
	static{
		try {
			Class.forName(PropertiesUtil.getValue("driver"));		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getCon(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(PropertiesUtil.getValue("url"), PropertiesUtil.getValue("user"), PropertiesUtil.getValue("password"));
		} catch (SQLException e) {
			throw new RuntimeException("连接出错！");
		}
		return conn;
	}
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}