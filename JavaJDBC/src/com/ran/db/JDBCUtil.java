package com.ran.db;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.ran.generic.ResultSetToObject;


public class JDBCUtil {
	
	private static String CLASSNAME;  //驱动类
	
	private static String DRIVER;  //驱动，填写数据库IP、端口和数据库名
	
	private static String DBUNAME;  //数据库用户名
	
	private static String DBPWD;  //数据库密码
	
	private static final Properties pro = new Properties();     //属性文件对象
	private static final String FILEDIR = "src/conn.properties";   //指定属性文件地址
 	public Connection conn = null;
	public  PreparedStatement ps = null;
	public  ResultSet rs = null;
	private List entityList = null;
	
	public JDBCUtil(){
		this.getConnection();   //初始化连接
	}
	
	//通过静态代码块读取属性文件和注册数据库驱动，保证只执行一次
	static{
		try {
			FileInputStream connFileStream =new FileInputStream(new File(FILEDIR));
			pro.load(connFileStream);   //读取属性文件
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取对应值
		CLASSNAME = pro.getProperty("CLASSNAME");
		DRIVER = pro.getProperty("DRIVER");
		DBUNAME = pro.getProperty("DBUNAME");
		DBPWD = pro.getProperty("DBUPWD");
		try {
			Class.forName(CLASSNAME);   //加载驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取一个连接对象
	 */
	public void getConnection(){
		try {
			// conn = DriverManager.getConnection(DRIVER,DBUNAME,DBPWD);   //JDBC-ODBC连接
			 conn = new DBManager().getConnection();  //C3P0
		} catch (Exception e) {
			System.out.println("数据库连接失败！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 优化后的query,只需传递sql命令和需要返回的对象类型就可获得一个对象集合，
	 * 在展示层只需变量集合直接操作对象获取属性值，完全面向对象的方式
	 * @param sql —— 查询SQL命令
	 * @param parms —— 查询条件
	 * @param classPo —— 需要返回结果集合的类型
	 * @return —— 返回一个LIST容器装PO对象，前台可直接遍历操作对象
	 */
	public List query( String sql , Object[] parms,Class classPo ){
		entityList = new ArrayList();
		//Map结构：key:表字段名   value：表字段值
		//LIST结果：获取每一行数据，
		//如：[{PHONE=13441231244, ADDRESS=成都市, SEX=男, YNAME=张三}, {PHONE=13551234123, ADDRESS=成都市, SEX=男, YNAME=李大}]
		List<Map<String,Object>> resultList = new  ArrayList<Map<String,Object>>();
		try {
			ps = conn.prepareStatement(sql);    //预编译SQL
			if( 0!=parms.length ){
				for( int i = 0; i<parms.length; i++ ){
					ps.setObject(i+1, parms[i]);   //循环设置参数
				}
			}
			rs = ps.executeQuery();   //执行查询操作
			//下面开始封装每一行数据放入MAP，并将每行数据放入LIST
			if( rs != null ){
				ResultSetMetaData rsm = rs.getMetaData();   //用于获取结果集中列的类型和属性信息对象   
				while( rs.next() ){
					Map<String,Object> map = new HashMap<String,Object>();
					for( int i = 1; i<=rsm.getColumnCount();i++ ){	
						map.put(rsm.getColumnName(i), rs.getObject(i));  //字段名称——字段值
					}
					resultList.add(map);   //将一行的数据放入LIST
				}
			}
			//利用反射来封住数据返回一个指定对象类型的数据集
			//LIST结构：[AddressBookPO@9446e4, AddressBookPO@ba5c7a, AddressBookPO@10d593e]
			  entityList = ResultSetToObject.ToObjectList(classPo, resultList);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.close();   //关闭所有对象
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityList;
	}
	
	/**
	 * 查询记录总数
	 * @param sql   
	 * @param parms
	 * @return
	 */
	public int countQuery( String sql,Object[] parms){
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);  //预编译SQL
			if( 0!=parms.length ){
				for( int i = 0; i<parms.length; i++ ){
					ps.setObject(i+1, parms[i]);   //循环设置参数
				}
			}
			rs = ps.executeQuery();   //执行查询操作
			if( rs.next() ){
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 增删改操作
	 * @param sql  SQL查询语句
	 * @param pares  判断条件
	 * @return
	 */
	public int edit( String sql, Object[] pares ){
		int hasEffect = 0;
		try {
			ps = conn.prepareStatement(sql);  //预编译SQL
			
			if( 0 != pares.length ){
				for( int i = 0; i<pares.length; i++ ){
					ps.setObject(i+1, pares[i]);   //循环设置参数
				}
			}
			hasEffect = ps.executeUpdate();  //执行增删改返回影响行数
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			this.close();   //关闭所有对象
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasEffect;
	}
	
	
	/**
	 * 关闭所有对象
	 * @throws Exception
	 */
	public void close() throws Exception{
		if( rs != null ){
			rs.close();
		}
		if( ps != null ){
			ps.close();
		}
		if( conn != null ){
			conn.close();
		}
	}
	

}
