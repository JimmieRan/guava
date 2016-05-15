package com.ran.db;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0连接池类
 * @author Administrator
 *
 */
public class DBManager {
	
	private static final Properties pro = new Properties();     //属性文件对象
	private static final String FILEDIR = "src/dataSource.properties";   //指定属性文件地址
	public static String DBSOURCE;  //数据源
	
	private static final DBManager dbm = new DBManager();
	static ComboPooledDataSource cpds = null;
	private Connection conn = null;
	
	private DBManager(){}
	
	public static DBManager getInstance(){
		return dbm;
	}
	
	//通过静态代码块读取数据源，保证只执行一次
	static{
		try {
			FileInputStream connFileStream =new FileInputStream(new File(FILEDIR));
			pro.load(connFileStream);   //读取属性文件
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBSOURCE = pro.getProperty("DATASOURCE");
		cpds = new ComboPooledDataSource(DBSOURCE);
	}
	
	/**
	 * 获取连接
	 * @return
	 */
	public synchronized Connection getConnection(){
		try {
			conn = cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
