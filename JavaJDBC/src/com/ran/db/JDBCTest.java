package com.ran.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * JDBC连接Oracle数据
 * 连接其它数据只需修改驱动类、驱动和用户、密码
 * @author Administrator
 *
 */
public class JDBCTest {
	
	private static String CLASSNAME = "oracle.jdbc.driver.OracleDriver";  //驱动类
	private static String DRIVER = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL"; //驱动，填写数据库IP、端口和数据库名
	private static String USERNAME  = "scott"; //数据库用户名
	private static String PWD = "tiger";   //数据库密码
	
	private Connection conn = null;  //连接对象
	private PreparedStatement ps = null; //预加载对象
	private ResultSet rs = null;  //结果集
	
	/**
	 * 获取一个连接对象
	 * @return
	 */
	public Connection getConnection(){
		try {
			Class.forName(CLASSNAME);
		} catch (ClassNotFoundException e) {
			System.out.println("没有找到驱动类！");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DRIVER, USERNAME, PWD);
		} catch (SQLException e) {
			System.out.println("获取连接失败！");
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 查询操作(一般项目不是返回ResultSet，一般返回封装好的对象集合或单个对象) 
	 * @param sql  SQL查询语句
	 * @param pares  查询条件
	 * @return
	 */
	public ResultSet query( String sql,Object[] pares ){
		
		try {
			ps = conn.prepareStatement(sql);  //预编译SQL
			
			for( int i = 0; i<pares.length; i++ ){
				ps.setObject(i+1, pares[i]);   //循环设置参数
			}
			rs = ps.executeQuery();   //执行查询返回结果集
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	

}
