package com.vetc.manage.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vetc.manage.annotation.CustomDateSerializerInputFormat;
import com.vetc.manage.annotation.CustomDateSerializerOutputFormat;
import java.io.IOException;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;

/**
 * @Author HungVM
 */
@Configuration
@Getter
@Setter
public class CustomDateTypeSerializer extends StdSerializer<Date> implements ContextualSerializer {

  private String inputFormat = "yyyy-MM-dd";
  private String outputFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

  public CustomDateTypeSerializer() {
    this(null);
  }

  public CustomDateTypeSerializer(Class t) {
    super(t);
  }

  public CustomDateTypeSerializer(Class t, String inputFormat) {
    super(t);
    this.inputFormat = inputFormat;
  }

  public CustomDateTypeSerializer(Class t, String inputFormat, String outputFormat) {
    this(t, inputFormat);
    this.outputFormat = outputFormat;
  }

  @SneakyThrows
  @Override
  public void serialize(Date dateInput, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    String output = DateUtil.formatDate(dateInput, inputFormat, outputFormat);
    jsonGenerator.writeString(output);
  }

  @Override
  public JsonSerializer<?> createContextual(SerializerProvider serializerProvider,
      BeanProperty beanProperty) throws JsonMappingException {
    CustomDateSerializerInputFormat inputFormatAnnotation = null;
    CustomDateSerializerOutputFormat outputFormatAnnotation = null;

    if (beanProperty != null) {
      inputFormatAnnotation = beanProperty.getAnnotation(CustomDateSerializerInputFormat.class);
      outputFormatAnnotation = beanProperty.getAnnotation(CustomDateSerializerOutputFormat.class);
    }
    if (inputFormatAnnotation != null) {
      inputFormat = inputFormatAnnotation.inputFormat();
    }
    if (outputFormatAnnotation != null) {
      outputFormat = outputFormatAnnotation.outputFormat();
    }

    return new CustomDateTypeSerializer(this._handledType, inputFormat, outputFormat);
  }
}
