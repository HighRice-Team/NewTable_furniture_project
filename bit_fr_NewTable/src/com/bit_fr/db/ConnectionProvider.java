package com.bit_fr.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionProvider {
	
	//Connection 생성 전달 역할. 각 Dao에서 사용.
	public static Connection getConnection()	
	{
		Connection conn = null;
		try {
			
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/bit");
			conn = ds.getConnection();
			
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
		
		return conn;
	}
	
	
	//getConnection()을 이용 하여 사용한 객체들을 닫는다. 
	public static void close(Connection conn,Statement stmt,ResultSet rs)
	{
		
		try {
			
			if( conn != null )
			{	conn.close();	}
			
			if( stmt != null )
			{	stmt.close();	}
			
			if( rs != null )
			{	rs.close();		}
			
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
	}
}
