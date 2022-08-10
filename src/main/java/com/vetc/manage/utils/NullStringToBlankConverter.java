package com.vetc.manage.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
/** 
 * @Author HungVM
 */
@Configuration
@Getter
@Setter
public class NullStringToBlankConverter extends StdSerializer<String> {
	public NullStringToBlankConverter() {
		this(null);
	}

	public NullStringToBlankConverter(Class t) {
		super(t);
	}

	@Override
	public void serialize(String input, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		String output = (input.equalsIgnoreCase("null")) ? "" : input;
		jsonGenerator.writeString(output);
	}
}
