package demo.json;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonDemo {
	private <T> T getInstanceFromSaveLog(String jsonStr,Class<T> clazz){
		JSON jsonObj= (JSON) JSON.parse(jsonStr);
		return com.alibaba.fastjson.JSONObject.toJavaObject(jsonObj, clazz);
	}
	private <T> List<T> getListInstanceFromSaveLog(String jsonStr,Class<T> clazz){
		List<T> retList=new ArrayList<T>();
		JSONArray arr=JSONArray.parseArray(jsonStr);
		for (Object object : arr) {
			JSONObject jsonObj = (JSONObject) object;
			T obj = JSONObject.toJavaObject(jsonObj, clazz);
            retList.add(obj);
        }
		return retList;
	}
}
