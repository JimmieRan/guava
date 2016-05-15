package com.ran.dao;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ran.db.JDBCUtil;
import com.ran.db.PageBean;
import com.ran.db.PageDBManager;
import com.ran.generic.SimpleSQLGenerator;
import com.ran.po.AddressBookPO;

public class MyDAO {	
	
	/*
	 * ���Է�ҳ
	 */
	public void initIndexPage() {
		
		int currentPage = 1;   //��ȡ��ǰҳ��Ĭ��1
		int pageRecord = 10;   //ÿҳ��ʾ��¼��
		
		long star = System.currentTimeMillis();
		
		/**********��ʼ���÷�ҳ����************/
		/***������ҳ��ѯADDDRESSBOOK����������ӵĵ�ַADDRESSΪ���ɶ��С�����������Ϣ***/		 
		SimpleSQLGenerator sqlGen = new SimpleSQLGenerator();
		sqlGen.setTableName("ADDDRESSBOOK");                       //�������ݱ�ADDDRESSBOOK
		Map<String,Object> fields = new HashMap<String,Object>();
		fields.put("ADDRESS", "�ɶ���");
		sqlGen.setFields(fields);                                  //���ò�ѯ���������ס��ADDRESS�ǡ��ɶ��С������м�¼
		sqlGen.setOrder(true);                                     //��������
		sqlGen.setOrderField("CREATETIME");                        //������ʱ������
		sqlGen.setSort(SimpleSQLGenerator.DESC);                   //����
		
		/**********��ʼ��ѯ��ҳ************/
		PageDBManager pageDB = new PageDBManager( pageRecord,currentPage,sqlGen );
		PageBean myPage = pageDB.getPageBean();
		ArrayList<AddressBookPO> recordList = (ArrayList<AddressBookPO>) pageDB.queryRecordList(sqlGen,AddressBookPO.class);
		myPage.setResultList(recordList);
		/*****��ҳ���������һ��PageBean***/
		
		
		/*****�����ҳ���***/
		System.out.println("�����������������Ա𡪡��������������绰����������������������ַ����������������������������ʱ��");
		for( AddressBookPO addressBook : recordList ){
			System.out.println(addressBook.getYname()+"������������"+addressBook.getSex()+"������������"+addressBook.getPhone()+"������������"+addressBook.getAddress()+"������������"+addressBook.getCreatetime());
		}
		System.out.print("��ǰҳ��"+myPage.getCurrentPage());
		System.out.print("  �ܼ�¼��"+myPage.getAllRecord());
		System.out.println("  ��ҳ����"+myPage.getTotalPage());
		
		long end = System.currentTimeMillis();
		System.out.println("����ʱ�䣺"+(end-star));
	}
	
	public void insetRecord(){
		SimpleSQLGenerator sqlGen = new SimpleSQLGenerator();
		sqlGen.setTableName("ADDDRESSBOOK");
		JDBCUtil jdbc = new JDBCUtil();
		for( int i = 1; i<=50 ; i++ ){
			Map<String,Object> insertFields = new HashMap<String,Object>();
			insertFields.put("YNAME", "����"+i);
			if( i % 2 == 0 ){
				insertFields.put("SEX", "��");
			}else{
				insertFields.put("SEX", "Ů");
			}
			insertFields.put("PHONE", "135411526"+i);
			insertFields.put("ADDRESS", "�ɶ���");
			insertFields.put("CREATETIME",new Timestamp(System.currentTimeMillis()));
			sqlGen.setInsertFields(insertFields);
			List parms = new ArrayList(insertFields.values());
			jdbc.edit(sqlGen.editSQL(0),parms );
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MyDAO mydao = new MyDAO();
		mydao.initIndexPage();
		//mydao.insetRecord();
	
	}
	
}
