package com.blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class BlobInsertToDB {

	public static void main(String[] args) {
		Connection conn=null;
		try{
		File f = new File("deepika.jpg");
		FileInputStream fis = new FileInputStream(f);
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/rr","root","root");
		if(conn!=null)
			System.out.println("Connected");
		PreparedStatement ps = conn.prepareStatement("insert into blob1 values(?,?)");
		ps.setString(1,"Deepika");
		ps.setBinaryStream(2,fis);
		System.out.println("Inserting Image of Deepika : "+f.getAbsolutePath());
		int rc = ps.executeUpdate();
		if(rc!=0)
			System.out.println("Image inserted.");
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