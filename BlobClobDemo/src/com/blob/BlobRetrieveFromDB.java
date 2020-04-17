package com.blob;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BlobRetrieveFromDB {

	public static void main(String[] args) {
		Connection conn=null;
		try{
		File f = new File("Deepika_blobl1.jpg");
		FileOutputStream fos = new FileOutputStream(f);
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/rr","root","root");
		if(conn!=null)
			System.out.println("Connected");
		PreparedStatement ps = conn.prepareStatement("Select * from blob1");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			String name = rs.getString(1);
			InputStream ir = rs.getBinaryStream(2);
			byte[] buffer = new byte[1024];
			while(ir.read(buffer)>0){         //read from DB into buffer
				fos.write(buffer);            // write from buffer to target file
			}
			fos.flush();
			System.out.println("Open the image of " + name + "at : " + f.getAbsolutePath());
			}
		fos.close();
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


// Buffer concept required to increase performance. Buffer is just a byte array. Read data first from DB into buffer and then transfer to file.