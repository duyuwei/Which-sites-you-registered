package net.duyuwei.common.util;

import java.util.HashMap;
import java.util.Map;

import net.duyuwei.common.util.HttpTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

public class DuTest {
	
	public static void main(String args[]){
		DuTest duTest = new DuTest();
		duTest.test();
	}

	@Test
	public void test(){
		Map<String, Object> parm= new HashMap<String, Object>();
		parm.put("r", "user/RegisterValidate");
		parm.put("username", "aaa");
		try {
			JSONObject array = HttpTool.sendGet("http://passport.feng.com/index.php", parm);
			if (array.get("info").toString().contains("username_exists")) {
				System.out.println("true");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
