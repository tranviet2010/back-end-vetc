package com.vetc.manage.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
/** 
 * @Author HungVM
 */
@Slf4j
public class ObjectUtil {
	public static Hashtable<String, String> copyValuedFields(Object fromObj) {
		if (fromObj == null)
			throw new NullPointerException("Source and destination objects must be non-null");

		Class fromClass = fromObj.getClass();
		Field[] fields = fromClass.getDeclaredFields();
		Hashtable<String, String> htResults = new Hashtable<String, String>();

		for (Field t : fields) {
			try {
				if (t.getType() == String.class || t.getType() == int.class || t.getType() == Integer.class
						|| t.getType() == char.class || t.getType() == Character.class || t.getType() == Long.class
						|| t.getType() == long.class || t.getType() == Byte.class || t.getType() == byte.class
						|| t.getType() == Boolean.class || t.getType() == boolean.class) {
					t.setAccessible(true);
					if (t.getName().equalsIgnoreCase("serialVersionUID") || t.get(fromObj) == null
							|| (t.getType() == String.class && String.valueOf(t.get(fromObj)).isEmpty()))
						continue;
					htResults.put(t.getName(), String.valueOf(t.get(fromObj)));
				} else if (t.getType() == Date.class) {
					continue;
				}
			} catch (IllegalAccessException ex) {
				log.error("Unable to copy field: {}", t.getName());
			}
		}
		return htResults;
	}

	public static void copyMatchingFields(Object fromObj, Object toObj) {
		if (fromObj == null || toObj == null)
			throw new NullPointerException("Source and destination objects must be non-null");

		Class fromClass = fromObj.getClass();
		Class toClass = toObj.getClass();

		Field[] fields = fromClass.getDeclaredFields();
		for (Field f : fields) {
			try {
				Field t = toClass.getDeclaredField(f.getName());
				if (t.getType() == f.getType()) {
					if (t.getType() == String.class || t.getType() == int.class || t.getType() == Integer.class
							|| t.getType() == char.class || t.getType() == Character.class || t.getType() == Long.class
							|| t.getType() == long.class || t.getType() == Byte.class || t.getType() == byte.class
							|| t.getType() == Boolean.class || t.getType() == boolean.class) {
						f.setAccessible(true);
						t.setAccessible(true);
						if (t.getName().equalsIgnoreCase("serialVersionUID") || f.get(fromObj) == null
								|| (f.getType() == String.class && String.valueOf(f.get(fromObj)) == ""))
							continue;
						t.set(toObj, f.get(fromObj));
					} else if (t.getType() == Date.class) {
						Date d = (Date) f.get(fromObj);
						f.setAccessible(true);
						t.setAccessible(true);
						t.set(toObj, d != null ? d.clone() : null);
					}
				}
			} catch (NoSuchFieldException ex) {
			} catch (IllegalAccessException ex) {
				log.error("Unable to copy field: {}", f.getName());
			}
		}
	}

	public static Object patchObjectProperties(Object target, Map<String, Object> sourceItems)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (Map.Entry<String, Object> entry : sourceItems.entrySet()) {
			String fieldName = entry.getKey();
			Stream<Method> classMethods$ = Arrays.stream(Object.class.getMethods());
			Optional<Method> setterMaybe = classMethods$
					.filter(x -> x.getName().startsWith("set") && x.getName().toLowerCase().contains(fieldName.toLowerCase()))
					.findFirst();
			if (!setterMaybe.isPresent()) continue;

			Method setter = setterMaybe.get();
			setter.invoke(target, entry.getValue());
		}
		return target;
	}
}
