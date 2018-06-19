package cn.itcast.test;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/*
 *  实现综合案例
 *  XML+DOM4j+反射+BeanUtils
 *  读取配置文件中的数据,创建JavaBean对象,注入到JavaBean对象中的成员变量
 */
public class TestDemo {
	@Test
	public void demo()throws Exception{
		//创建SaxReader读取XML
		SAXReader sax = new SAXReader();
		//方法read写文件路径,读取XML
		//返回Document对象
		Document document = sax.read("data.xml");
//		System.out.println(document);
		//Document对象,获取根标签,getRootElement()
		//返回Element对象,表示文件中的标签
		Element rootElement = document.getRootElement();
//		System.out.println(rootElement);
		//通过根标签,获取子标签,Element对象方法 elements()
		//返回List集合,存储就是子标签
		List<Element> beanElements = rootElement.elements();
//		System.out.println(beanElements);
		//遍历集合,获取所有bean子标签
		for(Element beanElement : beanElements){
			//获取子标签的属性值,className属性
			//Element对象的方法attributeValue
			//属性值,就是写好的类的全名
			String className = beanElement.attributeValue("className");
			//		System.out.println(className);
			//反射方式,创建对象
			Class clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			//获取到bean标签的子标签property
			//Element对象的方法 elements()
			List<Element> proElements = beanElement.elements();
			for(Element proElement : proElements){
				//property标签获取属性值
				String name = proElement.attributeValue("name");
				String value = proElement.attributeValue("value");
				//System.out.println(name+"..."+value);
				//BeanUtils工具类,获取到的属性值,注入到JavaBean对象
				BeanUtils.setProperty(obj, name, value);
			}
			System.out.println(obj);
		}
	}
}
