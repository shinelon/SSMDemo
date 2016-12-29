package demo.json;

import java.net.URL;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class TestJson {
	public static void main(String[] args) throws ClassNotFoundException {
		
		System.out.println(new Date().getTime());
		
		Class.forName("com.alibaba.fastjson.JSONObject");
		URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
		System.out.println(urls[i].toString());
		}
//		JSONArray jo=new JSONArray();
//		String s="[{\"ad\":\"adno\",\"did\":\"1\",\"date\":\"20160802\"},{\"ad\":\"adno\",\"did\":\"1\",\"date\":\"20160802\"}]";
//		String s1="[{\"ad\":\"adno\",\"did\":\"1\",\"date\":\"20160802\"}]";
//		JSONArray jo1= (JSONArray) JSONObject.parse(s1);
		
		long start = System.currentTimeMillis();
//		JSONObject all=new JSONObject();				
//		JSONObject did1 = new JSONObject();
//		did1.put("did", "1");
//		did1.put("ad", "adno");
//		did1.put("date", "20160802");
//		JSONObject did2 = new JSONObject();
//		did2.put("did", "1");
//		did2.put("ad", "adno");
//		did2.put("date", "20160802");
//        JSONArray arry = new JSONArray();                
//        arry.add(did1);
//        arry.add(did2);       
//        all.put("alread", arry);
//		String s="[{\"ad\":\"adno\",\"did\":\"1\",\"date\":\"20160802\"},{\"ad\":\"adno\",\"did\":\"1\",\"date\":\"20160802\"}]";
//		JSONArray jo= (JSONArray) JSONObject.parse(s);
//		System.out.println(jo.toString());
//		Class.forName("com.alibaba.fastjson.JSONObject");
		JSONObject all=new JSONObject();
		all.put("id1", "123");
		all.put("id2", "123");
		all.put("id3", "123");
		all.put("id4", "123");
		
//		System.out.println(all.toString());		
		
		long end = System.currentTimeMillis();
		System.out.println("共耗时：" + (end - start));
		
//		 jo= (JSONArray) JSONObject.parse(s);
//		long end = System.currentTimeMillis();
//		System.out.println("共耗时：" + (end - start));
		 
     
	}
}
