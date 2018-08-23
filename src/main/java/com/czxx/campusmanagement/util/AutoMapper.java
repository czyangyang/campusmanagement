package com.czxx.campusmanagement.util;
 
import java.lang.reflect.Method;
import java.util.List;
 
public class AutoMapper
{
	public static <TSource,TDestination> void mapping(TSource source,TDestination destination)
	{
		Method[] srcMethods = source.getClass().getMethods();
		Method[] destMethods = destination.getClass().getMethods();
		for(Method m :srcMethods)
		{
			String srcMethodName = m.getName();
			if(srcMethodName.startsWith("get"))			//调用get方法
			{
				try
				{
				   Object getValue =m.invoke(source);	//获取当前方法返回值(获取当前属性值)
				   for(Method dm : destMethods)
				   {
					   String destMethodName  = dm.getName();		//目标方法名称
					   if(destMethodName.startsWith("set") && destMethodName.substring(3, destMethodName.length()).equals(srcMethodName.substring(3, srcMethodName.length())))
					   {
						   dm.invoke(destination, getValue);
					   }
				   }
				}
				catch (Exception e)
				{
				}
			}
		}
	}
	
 
	public static <S, T> List<T> mappingList(List<S> src, List<T> target, Class<?> targetClass)
	{
		for(int i = 0; i < src.size(); i++)
		{
			try
			{
				Object object = targetClass.newInstance();
				target.add((T) object);
				mapping(src.get(i), object);
			}
			catch (Exception e)
			{
				continue;// 某个方法反射异常
			}
		}
		return target;
	}
}

