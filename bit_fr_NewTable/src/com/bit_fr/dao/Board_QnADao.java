package com.bit_fr.dao;

public class Board_QnADao {
	private static Board_QnADao dao;

	static {
		dao = new Board_QnADao();
	}

	private Board_QnADao() {

	};

	public static Board_QnADao getInstance() {
		return dao;
	}
}
