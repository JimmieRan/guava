package com.ran.db;

import java.util.List;

public class PageBean {

	private int currentPage;  //��ǰҳ
	
	private int totalPage;   //��ҳ��
	
	private int pageRecord;  //ÿҳ��¼��
	
	private int allRecord;  //�ܼ�¼��
	
	private List resultList = null;  //�����
	
	public PageBean(){} 
	
	public PageBean( int pageRecord ){
		this.pageRecord = pageRecord;
		if( this.currentPage == 0 ){
			this.currentPage = 1;
		}
	}
	
	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageRecord() {
		return pageRecord;
	}

	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	} 
	
	public int getAllRecord() {
		return allRecord;
	}

	public void setAllRecord(int allRecord) {
		this.allRecord = allRecord;
	}

	/**
	 * ��ʼ����ҳ��
	 * @return  ������ҳ��
	 */
	public void initTotalPage(){
		if( this.allRecord % this.pageRecord == 0 ){
			this.totalPage = allRecord / pageRecord;
		}else{
			this.totalPage = allRecord / pageRecord + 1;
		}
	}

}
