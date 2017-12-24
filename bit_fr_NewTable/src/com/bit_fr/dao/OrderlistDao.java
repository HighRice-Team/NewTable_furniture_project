package com.bit_fr.dao;

public class OrderlistDao {
	private static OrderlistDao dao;

	static {
		dao = new OrderlistDao();
	}

	private OrderlistDao() {
		
	};

	public static OrderlistDao getInstance() {
		return dao;
	}

}
