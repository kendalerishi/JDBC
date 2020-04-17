package com.connectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class ConnectionPoolDemo {

	public static void main(String[] args) throws Exception{
		
		MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
		ds.setUrl("jdbc:mysql://localhost/rr");
		ds.setUser("root");
		ds.setPassword("root");
		Connection conn = ds.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from emp1");
		ResultSet rs = ps.executeQuery();
		System.out.println("id"+"\t"+"name"+"\t"+"salary"+"\t"+"address");
		System.out.println("-----------------------------------------------");
		while(rs.next()){
			System.out.print(rs.getInt(1)+"\t");
			System.out.print(rs.getString(2)+"\t");
			System.out.print(rs.getFloat(3)+"\t");
			System.out.println(rs.getString(4));
		}	
	}
}
