package com.vetc.manage.common;


import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.vetc.manage.exception.ValidationException;
import com.vetc.manage.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModCommon {

  public static String getValueFromKeys(JsonObject jTran, String accountno, String name) {

    String strReturn = "";

    try {
      JsonPrimitive jKey = jTran.getAsJsonPrimitive("keys");

      if (name.toUpperCase().equals("@ACCOUNTNO")) {
        strReturn = accountno;
      } else {
        JsonObject jreturn = new Gson().fromJson(jKey.getAsString(), JsonObject.class);
        strReturn =
            (!jreturn.get(name).equals(JsonNull.INSTANCE)) ? jreturn.get(name).getAsString() : "";
      }
    } catch (Exception ex) {

      log.error(String.format("getValueFromKeys: {0}, {1}"), jTran.getAsString(), ex.getMessage());
    }
    return strReturn;
  }

  public static String getJsonValueByIdx(JsonObject jTran, String nameLst, String name, int idx) {
    String strReturn = "";
    JsonObject jBody = jTran.getAsJsonObject("data");

    try {
      strReturn = jBody.getAsJsonArray(nameLst).get(idx).getAsJsonObject().get(name).getAsString();
    } catch (Exception ex) {
      log.error(String.format("getValueFromKeys: {0}, {1}"), jTran.getAsString(), ex.getMessage());
    }
    return strReturn;
  }

  public static String getJsonValueByName(JsonObject jTran, String accountno, String name) {
    String v_strReturn = "";
    try {
      JsonPrimitive jKey = jTran.getAsJsonPrimitive("keys");
      // var jHeader = JObject.Parse(jTran["headers"].ToString());
      JsonObject jBody = jTran.getAsJsonObject("data");

      if ("@ACCOUNTNO".equals(name.toUpperCase())) {
        return accountno;
      }
      if ("@ORDERID".equals(name.toUpperCase())) {
        JsonObject jreturn = new Gson().fromJson(jKey.getAsString(), JsonObject.class);
        v_strReturn = jreturn.get(name).getAsString();
        return v_strReturn;
      }
      if (jBody != null) {
        v_strReturn = jBody.get(name).getAsString();
      }
    } catch (Exception ex) {
      log.error(String.format("getValueFromKeys: {0}, {1}"), jTran.getAsString(), ex.getMessage());

    }
    return v_strReturn;
  }

  public static String transformDateString(String dateInput) throws ValidationException {
    if (StringUtil.isNullOrEmpty(dateInput)) {
      throw new ValidationException(-1, "Empty dateInput");
    }

    return dateInput.replace("-", "/");
  }

}
