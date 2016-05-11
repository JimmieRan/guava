package com.ran.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * JDBC����Oracle����
 * ������������ֻ���޸������ࡢ�������û�������
 * @author Administrator
 *
 */
public class JDBCTest {
	
	private static String CLASSNAME = "oracle.jdbc.driver.OracleDriver";  //������
	private static String DRIVER = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL"; //��������д���ݿ�IP���˿ں����ݿ���
	private static String USERNAME  = "scott"; //���ݿ��û���
	private static String PWD = "tiger";   //���ݿ�����
	
	private Connection conn = null;  //���Ӷ���
	private PreparedStatement ps = null; //Ԥ���ض���
	private ResultSet rs = null;  //�����
	
	/**
	 * ��ȡһ�����Ӷ���
	 * @return
	 */
	public Connection getConnection(){
		try {
			Class.forName(CLASSNAME);
		} catch (ClassNotFoundException e) {
			System.out.println("û���ҵ������࣡");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DRIVER, USERNAME, PWD);
		} catch (SQLException e) {
			System.out.println("��ȡ����ʧ�ܣ�");
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * ��ѯ����(һ����Ŀ���Ƿ���ResultSet��һ�㷵�ط�װ�õĶ��󼯺ϻ򵥸�����) 
	 * @param sql  SQL��ѯ���
	 * @param pares  ��ѯ����
	 * @return
	 */
	public ResultSet query( String sql,Object[] pares ){
		
		try {
			ps = conn.prepareStatement(sql);  //Ԥ����SQL
			
			for( int i = 0; i<pares.length; i++ ){
				ps.setObject(i+1, pares[i]);   //ѭ�����ò���
			}
			rs = ps.executeQuery();   //ִ�в�ѯ���ؽ����
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	

}
