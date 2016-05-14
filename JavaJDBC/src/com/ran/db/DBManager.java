package com.ran.db;
import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0连接池类
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
	
	//观察当前连接状态(测试用)
	public synchronized void getInfo() throws Exception{
		System.out.println(cpds.getMaxPoolSize());// 最大连接数
        System.out.println(cpds.getMinPoolSize());// 最小连接数
        System.out.println(cpds.getNumBusyConnections());// 正在使用连接数
        System.out.println(cpds.getNumIdleConnections());// 空闲连接数
        System.out.println(cpds.getNumConnections());// 总连接数
	}
	
}
