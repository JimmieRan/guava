package com.ran.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ran.generic.SimpleSQLGenerator;

public class PageDBManager {
	
	private static final JDBCUtil jdbc = new JDBCUtil();    //���ݿ�JDBC������
	public PageBean pageBean = null;
	public List fieldsValue = null;
	
	public PageDBManager(){}
	
	public PageDBManager( int pageRecord,int currentPage,SimpleSQLGenerator sqlGen ){
		pageBean = new PageBean(pageRecord);  //����һ��PageBean���󲢳�ʼ��ÿҳ��¼��
		pageBean.setCurrentPage(currentPage); //���õ�ǰҳ
		this.searchAllRecord(sqlGen);  //��ѯ�ܼ�¼��
		pageBean.initTotalPage();     //��ʼ����ҳ��
	}
	
	/**
	 * ��ʼ����ҳ��
	 * @param tableName  ����
	 * @param fields     �����ֶ�
	 * @param fieldsValue �ֶ�ֵ
	 */
	public void searchAllRecord( SimpleSQLGenerator sqlGen ){
		if( null != sqlGen.getFields() ){
			fieldsValue = new ArrayList( sqlGen.getFields().values() );
		}
		pageBean.setAllRecord(jdbc.countQuery(sqlGen.countSQL(), fieldsValue));
	}
	
	
	public List queryRecordList( SimpleSQLGenerator sqlGen,Class classPo ){
		List recordList = new ArrayList();
		if( null != sqlGen.getFields() ){
			fieldsValue = new ArrayList( sqlGen.getFields().values() );
		}
		recordList = jdbc.query(sqlGen.pageSQLForOracle(pageBean.getPageRecord(), pageBean.getCurrentPage()), fieldsValue, classPo);
		return recordList;
	}
	
	

}
