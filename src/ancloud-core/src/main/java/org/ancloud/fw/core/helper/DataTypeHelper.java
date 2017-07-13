package org.ancloud.fw.core.helper;
import java.io.IOException;

import org.ancloud.fw.core.joda.DateTimeDeserializer;
import org.ancloud.fw.core.joda.DateTimeSerializer;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class DataTypeHelper {
	
	@SuppressWarnings("unchecked")
	public static <T> T convertTo(String numberString,final Class<T> clazz){
		return (T)ConvertUtils.convert(numberString,clazz);
	}
	
	public static boolean equal(Object arg01,Object arg02){
		boolean result= false;
		if(arg01==null && arg02==null){
			result = true;
		} else {
			result = !(arg01 ==null || !(arg01.toString().equals(arg02==null?arg02:arg02.toString())));
		}
		return result;
	}
	
	
	public static boolean notEqual(Object arg01,Object arg02){
		return !DataTypeHelper.equal(arg01, arg02);
	}
	
	public static String toJson(Object obj){
		JsonFactory json = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(json);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		SimpleModule module = new JodaModule();
		module.addSerializer(DateTime.class, new DateTimeSerializer());
		module.addDeserializer(DateTime.class, new DateTimeDeserializer());
		mapper.registerModule(module);
		ObjectWriter writer = mapper.writer().without(SerializationFeature.FAIL_ON_EMPTY_BEANS)
											.without(SerializationFeature.FAIL_ON_SELF_REFERENCES);;
		
		try {
			return writer.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
	}
	
	public static<T> T toObject(String  objJson,final Class<T> clazz){
		if(objJson==null) return null;
		JsonFactory json = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(json);
		ObjectReader reader = mapper.reader()
									.forType(clazz)
									.without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			return reader.readValue(objJson);
		}  catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
