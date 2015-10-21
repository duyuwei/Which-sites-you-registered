package net.duyuwei.validate.analysis;

import net.sf.json.JSONObject;

public class Feng {

	public boolean analysis(JSONObject jsonObject) {
		boolean isRegistered = false;
		if (jsonObject.get("info").toString().contains("username_exists")) {
			isRegistered = true;
			System.out.println("yes");
		}
		return isRegistered;
	}
}
