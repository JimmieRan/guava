package com.ran.db;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ran.generic.SimpleSQLGenerator;

public class PageDBManager {
	
	private static final JDBCUtil jdbc = new JDBCUtil();    //���ݿ�JDBC������
	private PageBean pageBean = null;
	private List fieldsValue = null;
	
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public PageDBManager(){}
	
	public PageDBManager( int pageRecord,int currentPage,SimpleSQLGenerator sqlGen ){
		pageBean = new PageBean(pageRecord);  //����һ��PageBean���󲢳�ʼ��ÿҳ��¼��
		pageBean.setCurrentPage(currentPage); //���õ�ǰҳ
		this.searchAllRecord(sqlGen);  //��ѯ�ܼ�¼��
		pageBean.initTotalPage();     //��ʼ����ҳ��
	}
	
	/**
	 * ��ʼ����ҳ��
	 * @param sqlGen   SQL������
	 */
	public void searchAllRecord( SimpleSQLGenerator sqlGen ){
		if( null != sqlGen.getFields() ){
			fieldsValue = new ArrayList( sqlGen.getFields().values() );
		}
		pageBean.setAllRecord(jdbc.countQuery(sqlGen.countSQL(), fieldsValue));
	}
	
	/**
	 * ������ҳ��ѯ
	 * @param sqlGen    SQL������
	 * @param classPo   ���������
	 * @return
	 */
	public List queryRecordList( SimpleSQLGenerator sqlGen,Class classPo ){
		List recordList = new ArrayList();
		if( null != sqlGen.getFields() ){
			fieldsValue = new ArrayList( sqlGen.getFields().values() );
		}
		if( "mysql".equals(DBManager.DBSOURCE) ){
			recordList = jdbc.query(sqlGen.pageSQLForMySQL(pageBean.getPageRecord(), pageBean.getCurrentPage()), fieldsValue, classPo);
		}else if( "oracle".equals(DBManager.DBSOURCE) ){
			recordList = jdbc.query(sqlGen.pageSQLForOracle(pageBean.getPageRecord(), pageBean.getCurrentPage()), fieldsValue, classPo);
		}
		return recordList;
	}
	
	

}
