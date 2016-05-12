package com.ran.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SimpleSQLGenerator {
	
	private String tableName;    //��ѯĿ���
	
	private String[] queryFields;  //��ѯ�ֶ���
	
	private Map<String,Object> fields = new HashMap<String,Object>();  //�����ֶ�-�ֶ�ֵ ��name="zhangsan"
	
	private boolean isOrder;   //�Ƿ�����
	
	private String orderField;  //�����ֶ���
	
	private String sql;        //����SQL
	
	public SimpleSQLGenerator(){}
	
	public SimpleSQLGenerator( String tableName,String[] queryFields,Map<String,Object> fields, 
			boolean isOrder,String orderField){
		
		this.tableName = tableName;
		this.queryFields = queryFields;
		this.fields = fields;
		this.isOrder = isOrder;
		this.orderField = orderField;
	}
	
	//���ɾۺ�countSQL���ڲ�ѯ������ܼ�¼��
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
		fields.put("YNAME", "����");
		SimpleSQLGenerator sQLGenerator = new SimpleSQLGenerator("ADDRESSBOOK",new String[]{},fields,false,"");
		System.out.println(sQLGenerator.countSQL());	
	}
}
