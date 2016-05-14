package com.ran.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ran.db.DBManager;
import com.ran.db.JDBCUtil;
import com.ran.db.PageBean;
import com.ran.db.PageDBManager;
import com.ran.generic.SimpleSQLGenerator;
import com.ran.po.AddressBookPO;

public class MyDAO {	
	
	/*
	 * ���Է�ҳ��ǰ̨���ݵ�ǰҳcurrentPage��ʾÿҳ����
	 */
	public static void main(String[] args) throws Exception {
		
		int currentPage = 1;   //������ǰ̨���λ�ȡ��Ĭ��1
		
		long star = System.currentTimeMillis();
		
		/**********��ʼ����SQL��䣬���÷�ҳ����************/
		/***��ѯADDDRESSBOOK���е�ַADDRESSΪ���ɶ��С�����������Ϣ�����Ա�SEX��������***/		 
		SimpleSQLGenerator sqlGen = new SimpleSQLGenerator();
		sqlGen.setTableName("ADDDRESSBOOK");                       //�������ݱ�ADDDRESSBOOK
		Map<String,Object> fields = new HashMap<String,Object>();
		fields.put("ADDRESS", "�ɶ���");
		sqlGen.setFields(fields);                                  //���ò�ѯ���������ס��ADDRESS�ǡ��ɶ��С������м�¼
		sqlGen.setOrder(true);                                     //��������
		sqlGen.setOrderField("SEX");                               //���Ա�����
		sqlGen.setSort(SimpleSQLGenerator.DESC);                   //����
		
		/**********��ʼ��ѯ��ҳ************/
		PageDBManager pageDB = new PageDBManager( 10,1,sqlGen );
		PageBean myPage = pageDB.pageBean;
		ArrayList<AddressBookPO> recordList = (ArrayList<AddressBookPO>) pageDB.queryRecordList(sqlGen,AddressBookPO.class);
		
		System.out.println("�����������������Ա𡪡����������绰��������������ַ");
		for( AddressBookPO addressBook : recordList ){
			System.out.println(addressBook.getYname()+"������������"+addressBook.getSex()+"������������"+addressBook.getPhone()+"������������"+addressBook.getAddress());
		}
		System.out.println("��ǰҳ��"+myPage.getCurrentPage());
		System.out.println("�ܼ�¼��"+myPage.getAllRecord());
		System.out.println("��ҳ����"+myPage.getTotalPage());
		
		long end = System.currentTimeMillis();
		System.out.println("����ʱ�䣺"+(end-star));
	}
}
