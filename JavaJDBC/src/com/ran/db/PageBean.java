package com.ran.db;

public class PageBean {

	public int currentPage;  //��ǰҳ
	
	public int totalPage;   //��ҳ��
	
	public int pageRecord;  //ÿҳ��¼��
	
	public int allRecord;  //�ܼ�¼��
	
	public PageBean(){} 
	
	public PageBean( int pageRecord ){
		this.pageRecord = pageRecord;
		if( this.currentPage == 0 ){
			this.currentPage = 1;
		}
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
