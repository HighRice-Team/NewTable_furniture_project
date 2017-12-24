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
	
	
	// 매개변수로 SQL을 받아와 그 에 맞는 List를 반환한다.
	public ArrayList<ProductVo>	customizeProduct(String sql)
	{
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		
		try {
			
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				list.add(new ProductVo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getInt(8)));
			}
			
			ConnectionProvider.close(conn, stmt, rs);
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
		return list;
		
	}
}
