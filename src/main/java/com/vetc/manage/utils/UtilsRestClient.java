package com.vetc.manage.utils;

import java.util.ArrayList;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
/** 
 * @Author HungVM
 */
@Slf4j
public class UtilsRestClient {

    protected static Object selectBody(Object obj, MultiValueMap<String, Object> formParams, MediaType contentType) {
        boolean isForm = MediaType.MULTIPART_FORM_DATA.isCompatibleWith(contentType)
                || MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(contentType);
        return isForm ? formParams : obj;
    }

  
    private static Map<String, Object> getMapValue(Map<String, Object> mRs, String key) {
        if (mRs != null && mRs.containsKey(key)) {
            return (Map<String, Object>) mRs.get(key);
        }
        return null;
    }

    private static ArrayList<Map<String, Object>> getArrayListValue(Map<String, Object> mRs, String key) {
        if (mRs != null && mRs.containsKey(key)) {
            ArrayList<Map<String, Object>> lstRs = (ArrayList<Map<String, Object>>) mRs.get(key);
            return lstRs;
        }
        return null;
    }

    private static Map<String, Object> getArrayListMapValue(ArrayList<Map<String, Object>> lstRs, String key) {
        if (lstRs != null && lstRs.size() > 0) {
            for (Map<String, Object> map : lstRs) {
                if (map.containsKey(key)) {
                    return map;
                }
            }
        }
        return null;
    }

}
