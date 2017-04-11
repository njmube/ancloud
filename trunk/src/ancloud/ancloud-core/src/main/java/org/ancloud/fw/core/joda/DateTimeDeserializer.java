package org.ancloud.fw.core.joda;

import java.io.IOException;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateTimeDeserializer extends JsonDeserializer<DateTime> {

	@Override
	public DateTime deserialize(JsonParser parser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = parser.getCodec();
		String value = oc.readValue(parser, String.class);
		return new DateTime(Long.parseLong(value));
	}

}
