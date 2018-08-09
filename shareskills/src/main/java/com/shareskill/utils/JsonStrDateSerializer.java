package com.shareskill.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;
/**
 * Used to serialize Java.util.Date, which is not a common JSON
 * type, so we have to create a custom serialize method;.
 *
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
@Component
public class JsonStrDateSerializer extends JsonSerializer<String>{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void serialize(String date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
       // String formattedDate = dateFormat.format(date);
        Date formattedDate= null;
        try {
            formattedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        gen.writeObject(formattedDate);
    }
}