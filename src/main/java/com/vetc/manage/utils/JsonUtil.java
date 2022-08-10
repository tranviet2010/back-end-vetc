package com.vetc.manage.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
/** 
 * @Author HungVM
 */
@Slf4j
public class JsonUtil {
	private static Gson gson = new Gson();

	public static <T> T deserializeObject(String body, Class<T> classOfT) throws Exception {
		try {
			return gson.fromJson(body, classOfT);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}

	public static String serializeObject(JsonObject object) {
		try {
			return object.toString();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
			// TODO: handle exception
		}
	}
	
	
	public static String serializeObject(Object object) {
		try {
			return gson.toJson(object);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
			// TODO: handle exception
		}
	}

}
