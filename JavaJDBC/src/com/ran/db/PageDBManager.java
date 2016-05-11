package com.ran.db;

import java.util.ArrayList;
import java.util.List;

public class PageDBManager {
	
	private static final JDBCUtil jdbc = new JDBCUtil();
	public PageBean pageBean = null;
	
	public PageDBManager(){}
	
	public PageDBManager( int pageRecord,String tableName,String[] fields,Object[] fieldsValue ){
		pageBean = new PageBean(pageRecord);  //����һ��PageBean���󲢳�ʼ��ÿҳ��¼��
		this.searchAllRecord(tableName, fields, fieldsValue);  //��ѯ�ܼ�¼��
		pageBean.initTotalPage();     //��ʼ����ҳ��
	}
	
	/**
	 * ��ʼ����ҳ��
	 * @param tableName  ����
	 * @param fields     �����ֶ�
	 * @param fieldsValue �ֶ�ֵ
	 */
	public void searchAllRecord( String tableName,String[] fields,Object[] fieldsValue ){
		String sql = "select count(*) as allRecord from "+tableName+" t ";
		if( 0!=fields.length ){
			sql += "where ";
			for(String field:fields  ){
				sql += " "+field+" =? ";
			}
		}
		pageBean.setAllRecord(jdbc.countQuery(sql, fieldsValue));
	}
	
	public List queryRecordList( String tableName,String[] fields,Object[] fieldsValue,Class classPo ){
		List recordList = new ArrayList();
		String sql = "select * from ( select rownum rn,t.* from "+tableName+" t where";
		if( 0!=fields.length ){
			for(String field:fields  ){
				sql += " t."+field+" =? and ";
			}
		}
		sql += " rownum <= "+pageBean.currentPage * pageBean.pageRecord+") where rn > "+(pageBean.currentPage - 1)* pageBean.pageRecord;
		System.out.println(sql);
		recordList = jdbc.query(sql, fieldsValue, classPo);
		return recordList;
	}
	
	

}
