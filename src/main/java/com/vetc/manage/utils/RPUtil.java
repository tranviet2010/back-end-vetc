package com.vetc.manage.utils;
/** 
 * @Author HungVM
 */

import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
@Slf4j
public class RPUtil {
	private static final String PATH = "path";
	private static final String METHOD = "method";
	private static final String HEADERS = "headers";
	private static final String DATA = "data";
	private static final String KEYS = "keys";

	private Map<String, Object> htValues = new HashMap<String, Object>();
	private String strValues = null;
	private JsonObject jsonValues = null;

	public RPUtil(HttpServletRequest request, String value) {
		buildObject(request, value);
	}

	public RPUtil(HttpServletRequest request, Object value) {
		String strValue = "";
		if (value != null)
			strValue = JsonUtil.serializeObject(value);
		buildObject(request, strValue);
	}

	private String getKeysKeyName(String name) {
		if (name == null || name.isEmpty())
			return null;
		return getKeyName(KEYS, name);
	}

	private String getDataKeyName(String name) {
		if (name == null || name.isEmpty())
			return null;
		return getKeyName(DATA, name);
	}

	private String getHeaderKeyName(String name) {
		if (name == null || name.isEmpty())
			return null;
		return getKeyName(HEADERS, name);
	}

	private String getKeyName(String prefix, String name) {
		if (prefix == null || prefix.isEmpty())
			return name;
		return prefix + "_" + name;
	}

	private List<String> lsHeaderIgnore = Arrays.asList(new String[] { "postman-token", "cache-control", "user-agent",
			"connection", "accept-encoding", "accept", "content-length" });

	private void buildObject(HttpServletRequest request, String value) {
		String body = "";
		Map<String, String> htHeaderTemp = new HashMap<>();
		String path = request.getRequestURI();
		htValues.put(PATH, path);
		htValues.put(METHOD, request.getMethod());

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			if (lsHeaderIgnore.contains(headerName))
				continue;
			htValues.put(getHeaderKeyName(headerName.replace("-", "")), request.getHeader(headerName));
			htHeaderTemp.put(headerName.replace("-", ""), request.getHeader(headerName));
		}

		try {
			body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			if (body != null && !body.isEmpty())
				body = body.replaceAll("\n", "");
			copyAllBodyValues(body, htValues);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}

		if (value != null) {
			copyAllKeysValues(value, htValues);
		}
		strValues = getMessageToString(htHeaderTemp, body, request.getMethod(), path, value);
		htHeaderTemp.clear();
	}

	private void copyAllKeysValues(String keys, Map<String, Object> htValues) {
		if (keys == null || keys.isEmpty())
			return;
		Map<String, String> htKeys = convertStringToMap(keys);
		if (htKeys == null || htKeys.size() <= 0)
			return;
		for (String key : htKeys.keySet()) {
			htValues.put(getKeysKeyName(key), htKeys.get(key));
		}
	}

	private void copyAllHeaderValues(String header, Map<String, Object> htValues) {
		if (header == null || header.isEmpty())
			return;
		Map<String, String> htHeader = convertStringToMap(header);
		if (htHeader == null || htHeader.size() <= 0)
			return;
		for (String key : htHeader.keySet()) {
			htValues.put(getHeaderKeyName(key), htHeader.get(key));
		}
	}

	private void copyAllBodyValues(String body, Map<String, Object> htValues) {
		if (body == null || body.isEmpty())
			return;
		Map<String, String> htBody = convertStringToMap(body);
		if (htBody == null || htBody.size() <= 0)
			return;
		for (String key : htBody.keySet()) {
			htValues.put(getDataKeyName(key), htBody.get(key));
		}
	}

	private Map<String, String> convertStringToMap(String values) {
		if (values == null || values.isEmpty()) {
			return null;
		}

		values = values.replaceAll("\\{", "");
		values = values.replaceAll("\\}", "");
		Map<String, String> map = new Hashtable<String, String>();
		String[] arrValue = values.split(",");
		if (arrValue == null || arrValue.length <= 0)
			return null;
		for (String str : arrValue) {
			String[] keys = str.split(":");
			if (keys != null && keys.length == 2) {
				map.put(keys[0].replaceAll("\n", "").replaceAll("\"", "").trim(),
						keys[1].replaceAll("\n", "").replaceAll("\"", "").trim());
			}
		}
		return map;
	}

	private String getMessageToString(Map<String, String> header, String body, String method, String path,
			String value) {
		Map<String, Object> htMessageTemp = new HashMap<>();
		htMessageTemp.put(HEADERS, header);
		htMessageTemp.put(PATH, path);
		htMessageTemp.put(METHOD, method);
		if (body != null && !body.isEmpty()) {
			htMessageTemp.put(DATA, body);
		}
		if (value != null && value != null) {
			htMessageTemp.put(KEYS, value);
		}
		String valueString = JsonUtil.serializeObject(htMessageTemp);
		htMessageTemp.clear();
		return valueString;
	}

	public String getMessage() {
		return strValues;
	}

	public JsonObject getJsonMessage() {
		try {
			if (this.jsonValues == null)
				this.jsonValues = JsonUtil.deserializeObject(this.strValues, JsonObject.class);
			return jsonValues;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getLocalizedMessage());
			return null;
		}
	}

	// v_blockqtty

	public String getDataValueByKey(String key) {
		try {
			return String.valueOf(htValues.get(getDataKeyName(key)));
		} catch (Exception e) {
			// TODO: handle exception
			log.debug(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	public String getKeysValueByKey(String key) {
		try {
			return String.valueOf(htValues.get(getKeysKeyName(key)));
		} catch (Exception e) {
			// TODO: handle exception
			log.debug(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	public String getHeaderValueByKey(String key) {
		try {
			return String.valueOf(htValues.get(getHeaderKeyName(key)));
		} catch (Exception e) {
			// TODO: handle exception
			log.debug(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	public String getValueByKey(String key) {
		try {
			return String.valueOf(htValues.get(key));
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public int getIntValueByKey(String key) {
		try {
			String value = getValueByKey(key);
			if (value == null)
				return -1;
			return Integer.parseInt(value);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug(ExceptionUtils.getStackTrace(e));
		}
		return -1;
	}

	public long getLongValueByKey(String key) {
		try {
			String value = getValueByKey(key);
			if (value == null)
				return -1;
			return Long.parseLong(value);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug(ExceptionUtils.getStackTrace(e));
		}
		return Long.valueOf(-1);
	}

	private String getPath(String url) {
		try {
			String[] endpoints = url.split("/");
			for (int i = endpoints.length - 1; i >= 0; --i) {
				if (Character.compare(endpoints[i].charAt(0), '{') != 0) {
					return endpoints[i];
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
}
