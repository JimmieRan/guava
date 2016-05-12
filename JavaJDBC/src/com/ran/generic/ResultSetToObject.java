package com.ran.generic;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * ��װʵ����-���ƶ�������������ͨ�����丳ֵ��ʼ������
 * @author Administrator
 */
public class ResultSetToObject {
	
	private static List resultList = null;
	private static Object o = null;
	
	/**
	 * ������������и��׶ˣ�ʵ���������Ա�������ݿ��Ӧ����ֶ�����һ������USER������һ��name����
	 * ��ô��������ݱ�TUSER���ֶ�����Ҳ������NAME��
	 * ����������ƺ��ֶ����Ʋ�һ����ô�죿
	 * ����취�����Խ���������ԺͶ�Ӧ���ݱ���ֶ�����һ��ӳ�䣬��򵥵ľ��Ǵ���һ�������ļ��Լ�ֵ�Է�ʽ���
	 * �磺name:USERNAME��age:USERAGE��ͨ����ȡ�����ļ���ƥ�����Ժ��ֶε�ӳ�䡣
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
