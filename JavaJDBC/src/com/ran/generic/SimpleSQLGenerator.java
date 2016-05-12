package com.ran.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SimpleSQLGenerator {
	
	private String tableName;    //查询目标表
	
	private String[] queryFields;  //查询字段组
	
	private Map<String,Object> fields = new HashMap<String,Object>();  //条件字段-字段值 ：name="zhangsan"
	
	private boolean isOrder;   //是否排序
	
	private String orderField;  //排序字段名
	
	private String sql;        //生成SQL
	
	public SimpleSQLGenerator(){}
	
	public SimpleSQLGenerator( String tableName,String[] queryFields,Map<String,Object> fields, 
			boolean isOrder,String orderField){
		
		this.tableName = tableName;
		this.queryFields = queryFields;
		this.fields = fields;
		this.isOrder = isOrder;
		this.orderField = orderField;
	}
	
	//生成聚合countSQL用于查询结果集总记录数
	public String countSQL(){
		sql = "select count(*) from "+tableName+" t ";
		Set<String> fieldSet = (Set<String>) fields.keySet();
		if( 0!=fieldSet.size() ){
			sql += "where ";
			for(String field:fieldSet  ){
				sql += " "+field+" =? ";
			}
		}
		return sql;
	}
	
	
	public static void main(String[] args) {
		Map<String,Object> fields = new HashMap<String,Object>();
		fields.put("YNAME", "张三");
		SimpleSQLGenerator sQLGenerator = new SimpleSQLGenerator("ADDRESSBOOK",new String[]{},fields,false,"");
		System.out.println(sQLGenerator.countSQL());	
	}
}
