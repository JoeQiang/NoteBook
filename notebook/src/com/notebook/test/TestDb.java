package com.notebook.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.notebook.biz.UserBiz;
import com.notebook.pojo.User;

public class TestDb {
	private UserBiz dao;

	@Before
	public void init() {
		dao = UserBiz.getBiz();
	}

	@Test
	public void tests() {
		Object find = dao.find(27);
		System.out.println(find);
	}

	@Test
	public void testd() {
		dao.delete(32);
	}

	@Test
	public void testsd() {
		User u = new User(22, "22", "22", "11");
		dao.save(u);
	}

	@Test
	public void testsdw() {
		User u = new User(22, "22", "22", "11");
		boolean login = dao.login("22", "11");
		System.out.println(login);
	}
	
	@Test
	public void tests2() {
		List list = dao.findAll();
		for (Object object : list) {
			System.out.println(object);
		}
	}
}
