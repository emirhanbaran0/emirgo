package com.proje.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class DBConnection {
	
	private static final Logger log=(Logger) LogManager.getLogger(); 
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	static {
		
		Properties properties=new Properties();
		try {
			InputStream inputStream=new FileInputStream("src/main/resources/database.properties");
			properties.load(inputStream);
			driver=properties.getProperty("db_driver");
			url=properties.getProperty("db_url");
			username=properties.getProperty("db_user");
			password=properties.getProperty("db_password");
			
			
		} catch (FileNotFoundException e) {
			log.warn("Dosya Bulunamadı! Hata: "+e);
			//e.printStackTrace();
		} catch (IOException e) {
			log.warn("Properties Yüklenemedi! Hata:"+e);
			//e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection()
	{
		Connection con=null;
		try {
			Class.forName(driver);
			log.info("Driver'a basarıyla baglanildi.");
		} catch (ClassNotFoundException e) {
			log.warn("Driver'a baglanirken hata olustu! Hata: "+e);
			//e.printStackTrace();
		}
		
		try {
			con=DriverManager.getConnection(url, username, password);
			log.info("Database ile baglanti kuruldu.");
		} catch (SQLException e) {
			log.info("Database ile baglanti kurulurken hata meydana geldi! Hata"+e);
			//e.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection(Connection con)
	{
		if(con!=null)
		{
			try {
				con.close();
				log.info("Driver baglantisi basariyla kapatildi");
			} catch (SQLException e) {
				log.warn("Driver baglantisi kapatilirken hata meydana geldi! Hata: "+e);
				//e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet rS)
	{
		if(rS!=null)
		{
			try {
				rS.close();
			} catch (SQLException e) {
				log.warn("Resultset kapatilirken hata meydana geldi! Hata: "+e);
				//e.printStackTrace();
			}
		}
	}
	
	public static void closePreparedStatement(PreparedStatement pS)
	{
		if(pS!=null)
		{
			try {
				pS.close();
	
			} catch (SQLException e) {
				log.warn("Prepared Statement kapatilirken hata meydana geldi! Hata: "+e);
				//e.printStackTrace();
			}
		}
	}
}
