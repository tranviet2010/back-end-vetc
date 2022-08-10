package com.vetc.manage.common;

import java.text.Normalizer;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ConvertUtil {
    public static String removingUnicodeAccents(String str) {
        try {
            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String stripAccents(String s)
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
}
