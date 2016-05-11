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
	
	private static String CLASSNAME;  //������
	
	private static String DRIVER;  //��������д���ݿ�IP���˿ں����ݿ���
	
	private static String DBUNAME;  //���ݿ��û���
	
	private static String DBPWD;  //���ݿ�����
	
	private static final Properties pro = new Properties();     //�����ļ�����
	private static final String FILEDIR = "src/conn.properties";   //ָ�������ļ���ַ
 	public Connection conn = null;
	public  PreparedStatement ps = null;
	public  ResultSet rs = null;
	private List entityList = null;
	
	public JDBCUtil(){
		this.getConnection();   //��ʼ������
	}
	
	//ͨ����̬������ȡ�����ļ���ע�����ݿ���������ִֻ֤��һ��
	static{
		try {
			FileInputStream connFileStream =new FileInputStream(new File(FILEDIR));
			pro.load(connFileStream);   //��ȡ�����ļ�
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ȡ��Ӧֵ
		CLASSNAME = pro.getProperty("CLASSNAME");
		DRIVER = pro.getProperty("DRIVER");
		DBUNAME = pro.getProperty("DBUNAME");
		DBPWD = pro.getProperty("DBUPWD");
		try {
			Class.forName(CLASSNAME);   //��������
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡһ�����Ӷ���
	 */
	public void getConnection(){
		try {
			// conn = DriverManager.getConnection(DRIVER,DBUNAME,DBPWD);   //JDBC-ODBC����
			 conn = new DBManager().getConnection();  //C3P0
		} catch (Exception e) {
			System.out.println("���ݿ�����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	
	/**
	 * �Ż����query,ֻ�贫��sql�������Ҫ���صĶ������;Ϳɻ��һ�����󼯺ϣ�
	 * ��չʾ��ֻ���������ֱ�Ӳ��������ȡ����ֵ����ȫ�������ķ�ʽ
	 * @param sql ���� ��ѯSQL����
	 * @param parms ���� ��ѯ����
	 * @param classPo ���� ��Ҫ���ؽ�����ϵ�����
	 * @return ���� ����һ��LIST����װPO����ǰ̨��ֱ�ӱ�����������
	 */
	public List query( String sql , Object[] parms,Class classPo ){
		entityList = new ArrayList();
		//Map�ṹ��key:���ֶ���   value�����ֶ�ֵ
		//LIST�������ȡÿһ�����ݣ�
		//�磺[{PHONE=13441231244, ADDRESS=�ɶ���, SEX=��, YNAME=����}, {PHONE=13551234123, ADDRESS=�ɶ���, SEX=��, YNAME=���}]
		List<Map<String,Object>> resultList = new  ArrayList<Map<String,Object>>();
		try {
			ps = conn.prepareStatement(sql);    //Ԥ����SQL
			if( 0!=parms.length ){
				for( int i = 0; i<parms.length; i++ ){
					ps.setObject(i+1, parms[i]);   //ѭ�����ò���
				}
			}
			rs = ps.executeQuery();   //ִ�в�ѯ����
			//���濪ʼ��װÿһ�����ݷ���MAP������ÿ�����ݷ���LIST
			if( rs != null ){
				ResultSetMetaData rsm = rs.getMetaData();   //���ڻ�ȡ��������е����ͺ�������Ϣ����   
				while( rs.next() ){
					Map<String,Object> map = new HashMap<String,Object>();
					for( int i = 1; i<=rsm.getColumnCount();i++ ){	
						map.put(rsm.getColumnName(i), rs.getObject(i));  //�ֶ����ơ����ֶ�ֵ
					}
					resultList.add(map);   //��һ�е����ݷ���LIST
				}
			}
			//���÷�������ס���ݷ���һ��ָ���������͵����ݼ�
			//LIST�ṹ��[AddressBookPO@9446e4, AddressBookPO@ba5c7a, AddressBookPO@10d593e]
			  entityList = ResultSetToObject.ToObjectList(classPo, resultList);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.close();   //�ر����ж���
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityList;
	}
	
	/**
	 * ��ѯ��¼����
	 * @param sql   
	 * @param parms
	 * @return
	 */
	public int countQuery( String sql,Object[] parms){
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);  //Ԥ����SQL
			if( 0!=parms.length ){
				for( int i = 0; i<parms.length; i++ ){
					ps.setObject(i+1, parms[i]);   //ѭ�����ò���
				}
			}
			rs = ps.executeQuery();   //ִ�в�ѯ����
			if( rs.next() ){
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * ��ɾ�Ĳ���
	 * @param sql  SQL��ѯ���
	 * @param pares  �ж�����
	 * @return
	 */
	public int edit( String sql, Object[] pares ){
		int hasEffect = 0;
		try {
			ps = conn.prepareStatement(sql);  //Ԥ����SQL
			
			if( 0 != pares.length ){
				for( int i = 0; i<pares.length; i++ ){
					ps.setObject(i+1, pares[i]);   //ѭ�����ò���
				}
			}
			hasEffect = ps.executeUpdate();  //ִ����ɾ�ķ���Ӱ������
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			this.close();   //�ر����ж���
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasEffect;
	}
	
	
	/**
	 * �ر����ж���
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
