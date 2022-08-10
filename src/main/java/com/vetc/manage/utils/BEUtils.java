package com.vetc.manage.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetc.manage.common.Response;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author HungVM
 */
@Slf4j
public class BEUtils {


  public static String likeActionInt(String action) {
    if ("like".equalsIgnoreCase(action)) {
      return "1";
    }
    if ("dislike".equalsIgnoreCase(action)) {
      return "0";
    }
    if ("share".equalsIgnoreCase(action)) {
      return "2";
    }
    return action;
  }

  public static String toXmlString(Object obj) {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    XMLEncoder xmlEncoder = new XMLEncoder(baos);
    xmlEncoder.writeObject(obj);
    xmlEncoder.close();

    String xml = null;
    try {
      xml = baos.toString("UTF-8");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

    return xml;
  }

  public static String getMessageError(String msg) {
    String fStr = "ValidationError: (u'";
    String s = msg.substring(msg.lastIndexOf(fStr) + fStr.length(), msg.length() - 1);
    s = s.substring(0, s.indexOf("',"));
    return s;
  }

  public static float roundOff(Float val, int scale) {
    BigDecimal a = new BigDecimal(val);
    BigDecimal roundOff = a.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
    return roundOff.floatValue();
  }

  public static String objToStringJson(Object obj) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage(), e);
      return "";
    }
  }

  public static Map<String, Object> jsonToMap(String json) {
    try {
      return new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {
      });
    } catch (JsonParseException e) {
      log.error(e.getMessage(), e);
    } catch (JsonMappingException e) {
      log.error(e.getMessage(), e);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }

  public static <T> T respToObject(Response resp, Class<T> clazz) {
    if (resp.getResult() != null) {
      return (T) resp.getResult();
    } else {
      return null;
    }
  }

  public static Double bigDecimalToDouble(BigDecimal val) {
		if (val != null) {
			return val.doubleValue();
		}
    return null;
  }

  public static BigDecimal doubleToBigDecimal(Double val) {
		if (val != null) {
			return BigDecimal.valueOf(val);
		}
    return null;
  }

  public static BigDecimal longToBigDecimal(Long val) {
		if (val != null) {
			return BigDecimal.valueOf(val);
		}
    return null;
  }

  public static Integer intNvl(Integer val, int defaultVal) {
		if (val == null) {
			return defaultVal;
		}
    return val;
  }

  public static Object getValueInMap(Map<String, Object> map, String... strings) {
    Map<String, Object> childMap = new HashMap<>(map);
    String key = null;
    for (int i = 0; i < strings.length; i++) {
      key = strings[i];
      if (i < (strings.length - 1)) {
        childMap = (Map<String, Object>) childMap.get(key);
      }
    }
    return childMap.get(key);
  }

  public static int random(int maxValue, int ignore) {
		if (maxValue <= 0) {
			return -1;
		}
    int randomNumber = -1;
    do {
      randomNumber = new Random().nextInt(maxValue);
      System.out.println(randomNumber + "random in: maxValue=" + maxValue + ", ignore=" + ignore);
    } while (randomNumber == ignore && maxValue > 1);
    return randomNumber;
  }

  static List<String> vinas = Arrays.asList("088", "091", "094", "083", "084", "085", "081", "082");

  public static boolean isVinaNetwork(String mobile) {
    String strPrefix;
    if (mobile.startsWith("84")) {
      strPrefix = "0" + mobile.substring(2, 4);
    } else {
      strPrefix = mobile.substring(0, 3);
    }
		if (vinas.contains(strPrefix)) {
			return true;
		}
    return false;
  }

  public static Hashtable<String, String> copyValuedFields(Object fromObj) {
		if (fromObj == null) {
			throw new NullPointerException("Source and destination objects must be non-null");
		}

    Class fromClass = fromObj.getClass();
    Field[] fields = fromClass.getDeclaredFields();
    Hashtable<String, String> htResults = new Hashtable<String, String>();

    for (Field t : fields) {
      try {
        // extend this if to copy more immutable types if interested
        if (t.getType() == String.class || t.getType() == int.class || t.getType() == Integer.class
            || t.getType() == char.class || t.getType() == Character.class
            || t.getType() == Long.class
            || t.getType() == long.class || t.getType() == Byte.class || t.getType() == byte.class
            || t.getType() == Boolean.class || t.getType() == boolean.class) {
          t.setAccessible(true);

					if (t.getName().equalsIgnoreCase("serialVersionUID") || t.get(fromObj) == null || (
							t.getType() == String.class && String.valueOf(t.get(fromObj)).isEmpty())) {
						continue;
					}
          htResults.put(t.getName(), String.valueOf(t.get(fromObj)));
        } else if (t.getType() == Date.class) {
          // dates are not immutable, so clone non-null dates into the destination object
          continue;
          // Date d = (Date) t.get(fromObj);
          // t.setAccessible(true);
          // htResults.put(t.getName(), d);
        }
      } catch (IllegalAccessException ex) {
        log.error("Unable to copy field: {}", t.getName());
      }
    }
    return htResults;
  }

  public static void copyMatchingFields(Object fromObj, Object toObj) {
		if (fromObj == null || toObj == null) {
			throw new NullPointerException("Source and destination objects must be non-null");
		}

    Class fromClass = fromObj.getClass();
    Class toClass = toObj.getClass();

    Field[] fields = fromClass.getDeclaredFields();
    for (Field f : fields) {
      try {
        Field t = toClass.getDeclaredField(f.getName());
        if (t.getType() == f.getType()) {
          // extend this if to copy more immutable types if interested
          if (t.getType() == String.class || t.getType() == int.class
              || t.getType() == Integer.class
              || t.getType() == char.class || t.getType() == Character.class
              || t.getType() == Long.class
              || t.getType() == long.class || t.getType() == Byte.class || t.getType() == byte.class
              || t.getType() == Boolean.class || t.getType() == boolean.class) {
            f.setAccessible(true);
            t.setAccessible(true);
						if (t.getName().equalsIgnoreCase("serialVersionUID") || f.get(fromObj) == null || (
								f.getType() == String.class && String.valueOf(f.get(fromObj)) == "")) {
							continue;
						}
            t.set(toObj, f.get(fromObj));
          } else if (t.getType() == Date.class) {
            // dates are not immutable, so clone non-null dates into the destination object
            f.setAccessible(true);
            t.setAccessible(true);
            Date d = (Date) f.get(fromObj);
            t.set(toObj, d != null ? d.clone() : null);
          }
        }
      } catch (NoSuchFieldException ex) {
        // skip it
      } catch (IllegalAccessException ex) {
        log.error("Unable to copy field: {}", f.getName());
      }
    }
  }

  public static void main(String[] a) {
    System.out.println(random(2, 0));
    System.out.println(isVinaNetwork("095338891919"));
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String stringencoder = encoder.encode("123");

    boolean isPasswordMatch = encoder.matches("123", stringencoder);
    System.out.println(
        "Password : " + "123, stringencoder :" + stringencoder + ",   isPasswordMatch    : "
            + isPasswordMatch);
  }
}
