package com.ran.db;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0���ӳ���
 * @author Administrator
 *
 */
public class DBManager {
	
	private static final Properties pro = new Properties();     //�����ļ�����
	private static final String FILEDIR = "src/dataSource.properties";   //ָ�������ļ���ַ
	public static String DBSOURCE;  //����Դ
	
	private static final DBManager dbm = new DBManager();
	static ComboPooledDataSource cpds = null;
	private Connection conn = null;
	
	private DBManager(){}
	
	public static DBManager getInstance(){
		return dbm;
	}
	
	//ͨ����̬������ȡ����Դ����ִֻ֤��һ��
	static{
		try {
			FileInputStream connFileStream =new FileInputStream(new File(FILEDIR));
			pro.load(connFileStream);   //��ȡ�����ļ�
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBSOURCE = pro.getProperty("DATASOURCE");
		cpds = new ComboPooledDataSource(DBSOURCE);
	}
	
	/**
	 * ��ȡ����
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
