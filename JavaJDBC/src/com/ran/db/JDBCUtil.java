package com.ran.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ran.generic.ResultSetToObject;


public class JDBCUtil {
	
 	public Connection conn = null;
	public  PreparedStatement ps = null;
	public  ResultSet rs = null;
	private List entityList = null;
	
	public JDBCUtil(){}
	
	/**
	 * 获取一个连接对象
	 */
	public Connection getConnection(){
		try {
			 conn = DBManager.getInstance().getConnection();  //C3P0连接池控制Connection
		} catch (Exception e) {
			System.out.println("数据库连接失败！");
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 优化后的query,只需传递sql命令和需要返回的对象类型就可获得一个对象集合，
	 * 在展示层只需变量集合直接操作对象获取属性值，完全面向对象的方式
	 * @param sql —— 查询SQL命令
	 * @param parms —— 查询条件
	 * @param classPo —— 需要返回结果集合的类型
	 * @return —— 返回一个LIST容器装PO对象，前台可直接遍历操作对象
	 */
	public List query( String sql , List parms,Class classPo ){
		conn = this.getConnection();
		entityList = new ArrayList();
		//Map结构：key:表字段名   value：表字段值
		//LIST结果：获取每一行数据，
		//如：[{PHONE=13441231244, ADDRESS=成都市, SEX=男, YNAME=张三}, {PHONE=13551234123, ADDRESS=成都市, SEX=男, YNAME=李大}]
		List<Map<String,Object>> resultList = new  ArrayList<Map<String,Object>>();
		try {
			ps = conn.prepareStatement(sql);    //预编译SQL
			if( 0!=parms.size() ){
				for( int i = 0; i<parms.size(); i++ ){
					ps.setObject(i+1, parms.get(i));   //循环设置参数
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
	public int countQuery( String sql,List parms){
		conn = this.getConnection();
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);  //预编译SQL
			if( null!= parms && 0!= parms.size() ){
				for( int i = 0; i<parms.size(); i++ ){
					ps.setObject(i+1, parms.get(i));   //循环设置参数
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
	public int edit( String sql, List pares ){
		conn = this.getConnection();
		int hasEffect = 0;
		try {
			ps = conn.prepareStatement(sql);  //预编译SQL
			if( 0 != pares.size() ){
				for( int i = 0; i<pares.size(); i++ ){
					System.out.println(pares.get(i).getClass().getName());
					ps.setObject(i+1, pares.get(i));   //循环设置参数
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
