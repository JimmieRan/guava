package com.ran.dao;
import java.util.ArrayList;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ran.db.JDBCUtil;
import com.ran.db.PageBean;
import com.ran.db.PageDBManager;
import com.ran.po.AddressBookPO;


public class MyDAO {
	
	public static void main(String[] args) throws Exception {
		long star = System.currentTimeMillis();
		
//		
//		for( int i = 1; i<=800; i++ ){
//			JDBCUtil jdbc = new JDBCUtil();
//			String sql = "select * from adddressbook t where t.address = '南充市'";
//			ArrayList<AddressBookPO> addressBookList = (ArrayList<AddressBookPO>) jdbc.query(sql, new Object[]{}, AddressBookPO.class);
//			for(AddressBookPO addressBook:addressBookList ){
//				System.out.println(addressBook.getYname());  
//			}
//		}
		
		PageDBManager pageDB = new PageDBManager( 5,"ADDDRESSBOOK",new String[]{},new Object[]{} );
		PageBean myPage = pageDB.pageBean;
		ArrayList<AddressBookPO> recordList = (ArrayList<AddressBookPO>) pageDB.queryRecordList("ADDDRESSBOOK",new String[]{},new Object[]{},AddressBookPO.class);
		System.out.println("当前页："+myPage.getCurrentPage());
		System.out.println("总记录："+myPage.getAllRecord());
		System.out.println("总页数："+myPage.getTotalPage());
		
		for( AddressBookPO addressbook : recordList ){
			System.out.println(addressbook.yname);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("运行时间："+(end-star));
		System.out.println("zzzzzzzzzzz");
	}
}
