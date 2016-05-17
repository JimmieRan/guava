package com.ran.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.ran.db.PageBean;
import com.ran.db.PageDBManager;
import com.ran.generic.SimpleSQLGenerator;
import com.ran.po.AddressBookPO;

public class MyDAO {	
	
	/*
	 * 测试分页
	 */
	public void initIndexPage() {
		
		int currentPage = 1;   //获取当前页，默认1
		int pageRecord = 10;   //每页显示记录数
		
		long star = System.currentTimeMillis();
		
		/**********开始设置分页条件************/
		/***例：分页查询ADDDRESSBOOK表中最新添加的地址ADDRESS为“成都市”的所有人信息***/		 
		SimpleSQLGenerator sqlGen = new SimpleSQLGenerator();
		sqlGen.setTableName("ADDDRESSBOOK");                       //设置数据表ADDDRESSBOOK
		Map<String,Object> fields = new HashMap<String,Object>();
		fields.put("ADDRESS", "成都市");
		sqlGen.setFields(fields);                                  //设置查询条件，如居住地ADDRESS是“成都市”的所有记录
		sqlGen.setOrder(true);                                     //设置排序
		sqlGen.setOrderField("CREATETIME");                        //按创建时间排序x
		sqlGen.setSort(SimpleSQLGenerator.DESC);                   //降序
		
		/**********开始查询分页************/
		PageDBManager pageDB = new PageDBManager( pageRecord,currentPage,sqlGen );
		PageBean myPage = pageDB.getPageBean();
		ArrayList<AddressBookPO> recordList = (ArrayList<AddressBookPO>) pageDB.queryRecordList(sqlGen,AddressBookPO.class);
		myPage.setResultList(recordList);
		/*****分页结束，获得一个PageBean***/
		
		
		/*****输出分页结果***/
		System.out.println("姓名——————性别————————电话——————————地址————————————创建时间");
		for( AddressBookPO addressBook : recordList ){
			System.out.println(addressBook.getYname()+"——————"+addressBook.getSex()+"——————"+addressBook.getPhone()+"——————"+addressBook.getAddress()+"——————"+addressBook.getCreatetime());
		}
		System.out.print("当前页："+myPage.getCurrentPage());
		System.out.print("  总记录："+myPage.getAllRecord());
		System.out.println("  总页数："+myPage.getTotalPage());
		
		long end = System.currentTimeMillis();
		System.out.println("运行时间："+(end-star));
	}
	
	public static void main(String[] args) {
		MyDAO mydao = new MyDAO();
		mydao.initIndexPage();
		//mydao.insetRecord();
	
	}
	
}
