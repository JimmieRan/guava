package com.ran.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SimpleSQLGenerator {
	
	private String tableName;    //目标表
	
	private int editFlag;       //1=insert,2=update,3=delete
	
	private String[] queryFields;  //查询字段组: select String[0],String[1],String[2] from table
	
	private Map<String,Object> updateFields = null;  //UPDATE字段-字段值 ：SET name="zhangsan",sex="男"
	
	private Map<String,Object> insertFields = null;  //INSERT字段-字段值 ：INSERT INTO TABLE(KEYSET) VALUES(VALUES);
	
	private Map<String,Object> fields = null;  //WHERE条件字段-字段值 ：WHERE name="zhangsan" 
	
	private boolean isOrder;   //是否排序
	
	private String orderField;  //排序字段名 order by orderField
	
	private String sort;    //升序或降序
	
	public static final String ASC = "ASC";  //升序
	
	public static final String DESC = "DESC";  //降序
	
	private String sql;        //生成SQLxxssss
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(int editFlag) {
		this.editFlag = editFlag;
	}

	public String[] getQueryFields() {
		return queryFields;
	}

	public void setQueryFields(String[] queryFields) {
		this.queryFields = queryFields;
	}

	public Map<String, Object> getUpdateFields() {
		return updateFields;
	}

	public void setUpdateFields(Map<String, Object> updateFields) {
		this.updateFields = updateFields;
	}

	public Map<String, Object> getInsertFields() {
		return insertFields;
	}

	public void setInsertFields(Map<String, Object> insertFields) {
		this.insertFields = insertFields;
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	public boolean isOrder() {
		return isOrder;
	}

	public void setOrder(boolean isOrder) {
		this.isOrder = isOrder;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public SimpleSQLGenerator(){}
	
	public SimpleSQLGenerator( String tableName,String[] queryFields,Map<String,Object> updateFields,Map<String,Object> insertFields,Map<String,Object> fields, 
			boolean isOrder,String orderField,String sort){
		
		this.tableName = tableName;
		this.queryFields = queryFields;
		this.updateFields = updateFields;
		this.insertFields = insertFields;
		this.fields = fields;
		this.isOrder = isOrder;
		this.orderField = orderField;
		this.sort = sort;
	}
	
	public boolean isNULLForMap( Map map ){
		if( null != map && map.keySet().size() > 0 ){
			return true;
		}else{
			return false;
		}
	}
	
	//拼接WHERE后的条件字段 ： WHERE NAME=?,SEX=?,ADDRESS=?......
	public void spiltWHERE(){
		if( this.isNULLForMap(fields) ){
			Set<String> fieldSet = fields.keySet();
			sql += "where ";
			for(String field:fieldSet  ){
				sql += " "+field+" =? and";
			}
			sql = sql.substring(0,sql.lastIndexOf("and"));
		}
	}
	
	
	//生成聚合countSQL用于查询结果集总记录数
	public String countSQL(){
		sql = "select count(*) from "+tableName+" t ";
		this.spiltWHERE();
		return sql;
	}
	
	//增删改SQL生成器,参数editFlag：0=insert,1=update,2=delete
	public String editSQL( int editFlag ){
		if( editFlag == 0 ){
			if( this.isNULLForMap(insertFields) ){
				Set<String> insertFieldSet = insertFields.keySet();
				int filedsSize = insertFieldSet.size();
				//构建INSERT语句
				if( filedsSize != 0 ){
					sql = "insert into "+tableName+" ( ";
					for( String field : insertFieldSet  ){
						sql += field+",";
					}
					sql = sql.substring(0, sql.length()-1)+" ) values( ";
					for( String field : insertFieldSet  ){
						sql += "?,";
					}
					sql = sql.substring(0, sql.length()-1)+" )";
				}
			}
		//构建UPDATE语句	
		}else if( editFlag == 1 ){
			sql = "UPDATE "+tableName+" SET ";
			Set<String> updateFieldSet = updateFields.keySet();
			for( String field : updateFieldSet  ){
				sql += field+"=? ,";
			}
			sql = sql.substring(0,sql.length()-1);
			this.spiltWHERE();
		//构建DELETE语句
		}else if( editFlag == 2 ){
			sql = "DELETE FROM "+tableName+" ";
			this.spiltWHERE();
		}
		return sql;
	}
	
	//条件查询SQL
	public String querySQL(){
		sql = "SELECT ";
		if( queryFields.length > 0 ){
			for( String queryField:queryFields ){
				sql += queryField+" , ";
			}
			sql = sql.substring(0,sql.lastIndexOf(","))+" FROM "+tableName+" "; 
		}else{
			sql = "SELECT * FROM  "+tableName+" ";
		}
		this.spiltWHERE();
		if( this.isOrder ){
			sql += " ORDER BY "+orderField+" "+sort;
		}
		return sql;
	}
	
	//Oracle分页SQL
	public String pageSQLForOracle( int pageRecord , int currentPage ){
		//排序分页：select * from ( select rownum rn, t.* from ( select * from ADDDRESSBOOK order by SEX ) t where rownum <= 5) where rn > 0
		//无排序分页：select * from ( select rownum rn, t.* from ADDDRESSBOOK t where rownum <= 5) where rn > 0
		if( this.isOrder ){
			sql = "select * from ( select rownum rn, t.* from ( select * from "+tableName+" ";
			this.spiltWHERE();
			sql += " ORDER BY "+orderField+" "+sort+" ) t where rownum <= "+currentPage*pageRecord+" ) where rn > "+(currentPage-1)*pageRecord;
		}else{
			sql = "select * from ( select rownum rn,* from "+tableName+" where ";
			this.spiltWHERE();
			sql += " and rownum <= "+currentPage*pageRecord+" ) where rn > "+(currentPage-1)*pageRecord;
		}
		return sql;
	}
	
	
	public static void main(String[] args) {
		Map<String,Object> fields = new HashMap<String,Object>();
		fields.put("SEX", "女");
		fields.put("ADDRESS", "成都市");
		Map<String,Object> insertFields = new HashMap<String,Object>();
		insertFields.put("YNAME", "张莉");
		insertFields.put("PHONE", "13242221112");
		insertFields.put("ADDRESS", "西昌市");
		Map<String,Object> updateFields = new HashMap<String,Object>();
		updateFields.put("YNAME", "张莉");
		updateFields.put("PHONE", "13242221112");
		updateFields.put("ADDRESS", "西昌市");
		SimpleSQLGenerator sQLGenerator = new SimpleSQLGenerator("ADDRESSBOOK",new String[]{},updateFields,insertFields,fields,true,"SEX",SimpleSQLGenerator.DESC);
		System.out.println(sQLGenerator.countSQL());
		System.out.println(sQLGenerator.editSQL(0));
		System.out.println(sQLGenerator.editSQL(1));
		System.out.println(sQLGenerator.editSQL(2));
		System.out.println(sQLGenerator.querySQL());
		System.out.println(sQLGenerator.pageSQLForOracle(5, 1));
	}
}

