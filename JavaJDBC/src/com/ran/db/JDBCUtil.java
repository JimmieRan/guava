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
	 * ��ȡһ�����Ӷ���
	 */
	public Connection getConnection(){
		try {
			 conn = DBManager.getInstance().getConnection();  //C3P0���ӳؿ���Connection
		} catch (Exception e) {
			System.out.println("���ݿ�����ʧ�ܣ�");
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * �Ż����query,ֻ�贫��sql�������Ҫ���صĶ������;Ϳɻ��һ�����󼯺ϣ�
	 * ��չʾ��ֻ���������ֱ�Ӳ��������ȡ����ֵ����ȫ�������ķ�ʽ
	 * @param sql ���� ��ѯSQL����
	 * @param parms ���� ��ѯ����
	 * @param classPo ���� ��Ҫ���ؽ�����ϵ�����
	 * @return ���� ����һ��LIST����װPO����ǰ̨��ֱ�ӱ�����������
	 */
	public List query( String sql , List parms,Class classPo ){
		conn = this.getConnection();
		entityList = new ArrayList();
		//Map�ṹ��key:���ֶ���   value�����ֶ�ֵ
		//LIST�������ȡÿһ�����ݣ�
		//�磺[{PHONE=13441231244, ADDRESS=�ɶ���, SEX=��, YNAME=����}, {PHONE=13551234123, ADDRESS=�ɶ���, SEX=��, YNAME=���}]
		List<Map<String,Object>> resultList = new  ArrayList<Map<String,Object>>();
		try {
			ps = conn.prepareStatement(sql);    //Ԥ����SQL
			if( 0!=parms.size() ){
				for( int i = 0; i<parms.size(); i++ ){
					ps.setObject(i+1, parms.get(i));   //ѭ�����ò���
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
	public int countQuery( String sql,List parms){
		conn = this.getConnection();
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);  //Ԥ����SQL
			if( null!= parms && 0!= parms.size() ){
				for( int i = 0; i<parms.size(); i++ ){
					ps.setObject(i+1, parms.get(i));   //ѭ�����ò���
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
	public int edit( String sql, List pares ){
		conn = this.getConnection();
		int hasEffect = 0;
		try {
			ps = conn.prepareStatement(sql);  //Ԥ����SQL
			if( 0 != pares.size() ){
				for( int i = 0; i<pares.size(); i++ ){
					System.out.println(pares.get(i).getClass().getName());
					ps.setObject(i+1, pares.get(i));   //ѭ�����ò���
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
