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
	 * 测试分页，前台传递当前页currentPage显示每页数据
	 */
	public static void main(String[] args) throws Exception {
		
		int currentPage = 1;   //这里由前台传参获取，默认1
		
		long star = System.currentTimeMillis();
		
		/**********开始构建SQL语句，设置分页条件************/
		/***查询ADDDRESSBOOK表中地址ADDRESS为“成都市”的所有人信息并按性别SEX降序排列***/		 
		SimpleSQLGenerator sqlGen = new SimpleSQLGenerator();
		sqlGen.setTableName("ADDDRESSBOOK");                       //设置数据表ADDDRESSBOOK
		Map<String,Object> fields = new HashMap<String,Object>();
		fields.put("ADDRESS", "成都市");
		sqlGen.setFields(fields);                                  //设置查询条件，如居住地ADDRESS是“成都市”的所有记录
		sqlGen.setOrder(true);                                     //设置排序
		sqlGen.setOrderField("SEX");                               //按性别排序
		sqlGen.setSort(SimpleSQLGenerator.DESC);                   //降序
		
		/**********开始查询分页************/
		PageDBManager pageDB = new PageDBManager( 10,1,sqlGen );
		PageBean myPage = pageDB.getPageBean();
		ArrayList<AddressBookPO> recordList = (ArrayList<AddressBookPO>) pageDB.queryRecordList(sqlGen,AddressBookPO.class);
		myPage.setResultList(recordList);
		/*****分页结束，获得一个PageBean***/
		
		System.out.println("姓名——————性别——————电话——————地址");
		for( AddressBookPO addressBook : recordList ){
			System.out.println(addressBook.getYname()+"——————"+addressBook.getSex()+"——————"+addressBook.getPhone()+"——————"+addressBook.getAddress());
		}
		System.out.println("当前页："+myPage.getCurrentPage());
		System.out.println("总记录："+myPage.getAllRecord());
		System.out.println("总页数："+myPage.getTotalPage());
		
		long end = System.currentTimeMillis();
		System.out.println("运行时间："+(end-star));
	}
}
