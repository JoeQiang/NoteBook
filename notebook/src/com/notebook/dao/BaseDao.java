package com.notebook.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.notebook.utils.JdbcUtils;

public class BaseDao {
	protected Connection conn;
	protected JdbcUtils ds;
	protected PreparedStatement pst;

	public BaseDao() {
		ds = JdbcUtils.getInstance();
	}

	/**
	 * 获取连接并检查
	 * */
	protected Connection checkConn() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = ds.getConnection();
				return conn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通用的查询方法(查询一行)
	 * */
	protected ResultSet query(String sql, Object... params) {
		if (checkConn() != null) {
			try {
				pst = conn.prepareStatement(sql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						pst.setObject(i + 1, params[i]);
					}
				}
				return pst.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 不能关闭连接，不用ResultSet就不能用了。
		}
		return null;
	}

	/**
	 * 通用的更新方法，合并
	 * */
	protected int merge(String sql, Object... params) {
		if (checkConn() != null) {
			try {
				pst = conn.prepareStatement(sql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						pst.setObject(i + 1, params[i]);
					}
				}
				return pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
		}
		return -1;
	}

	/**
	 * 关闭Connection
	 * */
	protected void closeDB() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成pojoTitle
	 * */
	protected void autoGenPojoTitle() {
		Object pojo = null;
		Class<?> daoClz = this.getClass();
		String pojoName = daoClz.getName().substring(
				daoClz.getName().indexOf("dao") + 4);

		StringBuilder toCopy = new StringBuilder("//");
		StringBuilder title = new StringBuilder(
				"import java.io.Serializable;\n\n");
		title.append("public class ");
		title.append(pojoName.substring(0, pojoName.length() - 3));
		title.append(" implements Serializable {\n\n");
		String name;
		if (this.checkConn() != null) {
			try {
				ResultSet rs = conn.prepareStatement(
						"desc t_"
								+ pojoName.substring(0, pojoName.length() - 3))
						.executeQuery();
				String typeName = "";
				while (rs.next()) {
					title.append("    private ");
					typeName = rs.getString("type").substring(0, 3);
					if ("var".equals(typeName)) {
						title.append("  String ");
					} else if ("int".equals(typeName)) {
						title.append("  Integer");
					} else if ("dat".equals(typeName)) {
						title.append("  Date");
					} else if ("dou".equals(typeName)) {
						title.append("  Double");
					} else {
						title.append(" 自己定夺 ");
					}
					title.append("  ");
					name = rs.getString("field");
					title.append(name);
					toCopy.append(name);
					toCopy.append(",");

					title.append(";\n");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
		}
		System.out.println(title.toString());
		System.out.println(toCopy.toString());
	}

	/**
	 * 利用反射自动封装对象
	 * */
	public Object autoSetter(ResultSet rs) {
		Object pojo = null;
		Class daoClz = this.getClass();
		String pojoName = daoClz.getName().replace("dao", "pojo");
		pojoName = pojoName.substring(0, pojoName.length() - 3);
		try {
			Class<?> pojoClz = Class.forName(pojoName);
			pojo = pojoClz.newInstance();
			Field[] fields = pojoClz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String setMethodName = "set"
						+ fields[i].getName().substring(0, 1).toUpperCase()
						+ fields[i].getName().substring(1);
				Method method = pojoClz.getMethod(setMethodName, fields[i]
						.getType());
				Object obj = rs.getObject(i + 1);
				if (obj != null) {
					method.invoke(pojo, obj);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pojo;
	}

	/**
	 * 发起查询并返回一个pojo的方法
	 * */
	protected Object findOne(String sql, Object... params) {
		ResultSet rs = query(sql, params);
		try {
			if (rs.next()) {
				return autoSetter(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return null;
	}

	/**
	 * 发起查询并返回多个pojo的方法
	 * */
	protected List findSome(String sql, Object... params) {
		ResultSet rs = query(sql, params);
		List datas = new ArrayList();
		try {
			while (rs.next()) {
				datas.add(autoSetter(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return datas;
	}

	/**
	 * 通用的删除
	 * */
	public int delete(int id) {
		Class daoClz = this.getClass();
		String pojoName = daoClz.getName().replaceAll("dao", "pojo");
		pojoName = pojoName.substring(0, pojoName.length() - 3);
		try {
			Class<?> pojoClz = Class.forName(pojoName);
			StringBuffer sql = new StringBuffer("delete from t_");
			sql.append(pojoClz.getName().substring(
					pojoClz.getName().indexOf("pojo") + 5));
			sql.append(" where ");
			Field[] fs = pojoClz.getDeclaredFields();
			sql.append(fs[0].getName());
			sql.append(" = ?");

			return this.merge(sql.toString(), id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 通用的更新
	 * */
	public int update(Object pojo) {
		Class<?> pojoClz = pojo.getClass();
		StringBuilder sql = new StringBuilder("update t_");
		sql.append(pojoClz.getName().substring(
				pojoClz.getName().indexOf("pojo") + 5));
		sql.append(" set ");
		Field[] fs = pojoClz.getDeclaredFields();
		for (int i = 1; i < fs.length; i++) {
			sql.append(fs[i].getName());
			sql.append(" = ?");
			if (i < fs.length - 1) {
				sql.append(",");
			}
		}
		sql.append(" where ");
		sql.append(fs[0].getName());
		sql.append(" = ?");
		Object[] params = new Object[fs.length];
		try {
			for (int i = 1; i < fs.length; i++) {
				params[i - 1] = pojoClz.getMethod(
						"get" + fs[i].getName().substring(0, 1).toUpperCase()
								+ fs[i].getName().substring(1)).invoke(pojo);
			}
			params[params.length - 1] = pojoClz.getMethod("get"
					+ fs[0].getName().substring(0, 1).toUpperCase()
					+ fs[0].getName().substring(1)).invoke(pojo);
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.merge(sql.toString(), params);
	}

	/**
	 * 通用的保存
	 * */
	public int save(Object pojo) {
		Class<?> pojoClz = pojo.getClass();
		StringBuilder sql = new StringBuilder("insert into t_");
		sql.append(pojoClz.getName().substring(
				pojoClz.getName().indexOf("pojo") + 5));
		sql.append("(");
		Field fs[] = pojoClz.getDeclaredFields();
		for (int i = 1; i < fs.length; i++) {
			sql.append(fs[i].getName());
			if (i < fs.length - 1) {
				sql.append(",");
			}
		}
		sql.append(") values (");
		for (int i = 1; i < fs.length; i++) {
			sql.append("?");
			if (i < fs.length - 1) {
				sql.append(",");
			}
		}
		sql.append(")");
		Object[] params = new Object[fs.length - 1];
		for (int i = 1; i < fs.length; i++) {
			try {
				params[i - 1] = pojoClz.getMethod(
						"get" + fs[i].getName().substring(0, 1).toUpperCase()
								+ fs[i].getName().substring(1)).invoke(pojo);

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(sql.toString());
		return this.merge(sql.toString(), params);
	}
}
