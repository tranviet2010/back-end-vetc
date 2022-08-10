package com.vetc.manage.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Author HungVM
 */
@Service
public class DtoMapper {

  protected TypeFactory _typeFactory;

  @Autowired
  @Qualifier("objectMapper")
  ObjectMapper objectMapper;
  @Autowired
  @Qualifier("xmlMapper")
  private XmlMapper xmlMapper;


  public <T> T map(Object object, Class<T> toValueType) {
    T s = objectMapper.convertValue(object, toValueType);
    return s;
  }

  public String map(Object object) throws Exception {
    return objectMapper.writeValueAsString(object);
  }

  public <T> List<T> map(List<?> fromValue, Class<T> toValueType) throws Exception {
    List<T> ls = new ArrayList<T>();
    for (Object o : fromValue) {
      T dto = map(o, toValueType);
			if (dto == null) {
				throw new Exception("Cannot convert Entity to Dto (" + toValueType + ")");
			}
      ls.add(dto);
    }
    return ls;
  }

  public <T> List<T> map(String fromValue, Class<T> toValueType) throws Exception {
    List<T> ls = objectMapper.readValue(fromValue,
        objectMapper.getTypeFactory().constructCollectionType(List.class, toValueType));
    return ls;
  }

  public <T> T convertXmlToDto(String xmlString, Class<T> toValueType) throws IOException {
    T res = xmlMapper.readValue(xmlString, toValueType);
    return res;
  }

  public <T> T convertStringToObject(String fromValue, Class<T> toValueType) throws Exception {
    T res = objectMapper.readValue(fromValue, toValueType);
    return res;
  }
}
