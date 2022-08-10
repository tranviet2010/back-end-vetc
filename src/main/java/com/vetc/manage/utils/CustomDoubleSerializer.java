package com.vetc.manage.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.context.annotation.Configuration;
/** 
 * @Author HungVM
 */
@Configuration
public class CustomDoubleSerializer extends StdSerializer<Double>{

    public CustomDoubleSerializer() {
        this(null);
    }

    public CustomDoubleSerializer(Class t) {
        super(t);
    }


    @Override
    public void serialize(Double aDouble, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        BigDecimal d = new BigDecimal(aDouble);
        jsonGenerator.writeNumber(d.toPlainString());
    }
}
