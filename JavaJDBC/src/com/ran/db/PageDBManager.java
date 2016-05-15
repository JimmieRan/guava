package com.ran.db;

import java.util.ArrayList;
import java.util.List;

import com.ran.generic.SimpleSQLGenerator;

public class PageDBManager {
	
	private static final JDBCUtil jdbc = new JDBCUtil();    //数据库JDBC操作类
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
		pageBean = new PageBean(pageRecord);  //构建一个PageBean对象并初始化每页记录数
		pageBean.setCurrentPage(currentPage); //设置当前页
		this.searchAllRecord(sqlGen);  //查询总记录数
		pageBean.initTotalPage();     //初始化总页数
	}
	
	/**
	 * 初始化总页数
	 * @param sqlGen   SQL构造器
	 */
	public void searchAllRecord( SimpleSQLGenerator sqlGen ){
		if( null != sqlGen.getFields() ){
			fieldsValue = new ArrayList( sqlGen.getFields().values() );
		}
		pageBean.setAllRecord(jdbc.countQuery(sqlGen.countSQL(), fieldsValue));
	}
	
	/**
	 * 条件分页查询
	 * @param sqlGen    SQL构造器
	 * @param classPo   结果集类型
	 * @return
	 */
	public List queryRecordList( SimpleSQLGenerator sqlGen,Class classPo ){
		List recordList = new ArrayList();
		if( null != sqlGen.getFields() ){
			fieldsValue = new ArrayList( sqlGen.getFields().values() );
		}
		recordList = jdbc.query(sqlGen.pageSQLForOracle(pageBean.getPageRecord(), pageBean.getCurrentPage()), fieldsValue, classPo);
		return recordList;
	}
	
	

}
