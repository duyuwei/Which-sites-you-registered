package net.duyuwei.validate;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import net.duyuwei.common.util.HttpTool;
import net.sf.json.JSONObject;

public class Validate {

	@Test
	public void doValidate(){
		Class<?> analysis = null;
		
		Map<String, Object> parm= new HashMap<String, Object>();
		parm.put("r", "user/RegisterValidate");
		parm.put("username", "aaa");
		try {
			JSONObject array = HttpTool.sendGet("http://passport.feng.com/index.php", parm);
			analysis = Class.forName("net.duyuwei.validate.analysis.Feng");
			analysis.getMethod("analysis",JSONObject.class).invoke(analysis.newInstance(), array);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
