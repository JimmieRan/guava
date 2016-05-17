package com.ran.generic;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 封装实体类-将制定对象类型数据通过反射赋值初始化对象
 * @author Administrator
 */
public class ResultSetToObject {
	
	private static List resultList = null;
	private static Object o = null;
	
	/**
	 * 下面这个方法有个弊端：实体对象的属性必须和数据库对应表的字段名称一样，如USER对象有一个name属性
	 * 那么其对于数据表TUSER的字段名称也必须是NAME。
	 * 如果属性名称和字段名称不一致怎么办？
	 * 解决办法：可以将对象的属性和对应数据表的字段名做一个映射，最简单的就是创建一个属性文件以键值对方式存放
	 * 如：name:USERNAME，age:USERAGE。通过读取属性文件来匹配属性和字段的映射。
	 * @param c
	 * @param resultSetList
	 * @return
	 * @throws Exception
	 */
	public static List ToObjectList( Class c, List<Map<String,Object>> resultSetList ) throws Exception{
		
		resultList = new ArrayList();
		Field[] fields = c.getDeclaredFields();
		
		for( Map<String,Object> map : resultSetList ){
			o = c.newInstance();
			for( Field f:fields ){
				String fieldName = f.getName();
				String methodName = "set"+fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
				Method method = c.getMethod(methodName, new Class[]{f.getType()});
				method.invoke(o, map.get(f.getName().toUpperCase()));
			}
			resultList.add(o);
		}		
			return resultList;
	}
	
}
