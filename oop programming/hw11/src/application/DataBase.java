package application;

import java.util.*;

public class DataBase {
	private Map<String, String> loginVal = new Hashtable<String, String>();

	public DataBase() {
		loginVal.put("phbum0", "12345");
		loginVal.put("id01", "pw01");
		loginVal.put("id02", "pw02");
	}

	public boolean isUser(String id, String pw) {
		if (loginVal.containsKey(id)) {
			if (loginVal.get(id).equals(pw)) {
				return true;
			}
		}
		return false;
	}
}