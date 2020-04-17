package com.clob;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClobInsertToDB {
	
	public static void main(String[] args) {
		Connection conn = null;
		try{	
	File f = new File("Abc.txt");
	FileReader fr = new FileReader(f);
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://localhost/rr","root","root");
	if(conn!=null)
		System.out.println("Connected");
	PreparedStatement ps = conn.prepareStatement("insert into clob1 values(?,?)");
	ps.setString(1,"Deepika");
	ps.setCharacterStream(2,fr);
	System.out.println("Inserting text : "+f.getAbsolutePath());
	int rc = ps.executeUpdate();
	if(rc!=0)
		System.out.println("Data inserted.");
	else
		System.out.println("Not inserted.");	
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
			}
			catch(SQLException sq){
				sq.printStackTrace();
			}
		}
    }
}