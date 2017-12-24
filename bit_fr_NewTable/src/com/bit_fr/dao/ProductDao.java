package com.bit_fr.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.bit_fr.db.ConnectionProvider;
import com.bit_fr.vo.ProductVo;

public class ProductDao {
	private static ProductDao dao;
	
	static {
		dao = new ProductDao();
	}
	
	private ProductDao() {
	};
	
	public static ProductDao getInstance() {
		return dao;
	}
	
	
	// 질의문을 매개변수로 받아 해당 LIST를 반환.
	public ArrayList<ProductVo>	customizeProduct(String sql)
	{
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		
		try {
			
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				list.add(new ProductVo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10)));
			}
			
			ConnectionProvider.close(conn, stmt, rs);
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
		return list;
		
	}
}
