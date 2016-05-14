package com.ran.db;
import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0���ӳ���
 * @author Administrator
 *
 */
public class DBManager {
	
	private static final DBManager dbm = new DBManager();
	static ComboPooledDataSource cpds = new ComboPooledDataSource("oracle");
	private Connection conn = null;
	
	private DBManager(){}
	
	public static DBManager getInstance(){
		return dbm;
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
	
	//�۲쵱ǰ����״̬(������)
	public synchronized void getInfo() throws Exception{
		System.out.println(cpds.getMaxPoolSize());// ���������
        System.out.println(cpds.getMinPoolSize());// ��С������
        System.out.println(cpds.getNumBusyConnections());// ����ʹ��������
        System.out.println(cpds.getNumIdleConnections());// ����������
        System.out.println(cpds.getNumConnections());// ��������
	}
	
}
