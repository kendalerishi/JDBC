package com.clob;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class ClobRetrieveFromDB {

	public static void main(String[] args) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{	
	File f = new File("AbcWrite.txt");
	FileWriter fw = new FileWriter(f);
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://localhost/rr","root","root");
	if(conn!=null)
		System.out.println("Connected");
	ps = conn.prepareStatement("select * from clob1");
	rs = ps.executeQuery();
	while(rs.next()){
	String name = rs.getString(1);
	Reader r = rs.getCharacterStream(2);
	char[] buffer = new char[1024];
	while(r.read(buffer)>0){
	fw.write(buffer);
		}
	fw.flush();
	}
	System.out.println("Inserting text : "+f.getAbsolutePath());	
		}
		catch(IOException io){
			io.printStackTrace();
		}
		catch(SQLException sq){
			sq.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
			if(conn!=null)
				conn.close();
			if(rs!=null)
				conn.close();
			if(ps!=null)
				conn.close();
			}
			catch(SQLException sq){
				sq.printStackTrace();
			}
		}
    }
}
