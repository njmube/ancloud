package org.ancloud.fw.core.util;
import java.io.IOException;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class DataTypeUtil {
	
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
	
	public static String toJson(Object obj){
		JsonFactory json = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(json);
		ObjectWriter writer = mapper.writer().without(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		
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
