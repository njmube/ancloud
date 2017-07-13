package org.ancloud.fw.core.helper;

import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;

public class GenerationHelper {
	public static String generateCode(String prefix){
		//TODO: prefix 29 code 50
		return GenerationHelper.generateCode(prefix, 10000L);
	}
	
	public static String generateCode(String prefix,Long endExclusive){
		if(prefix!=null){
			return prefix.concat(System.currentTimeMillis() + "_" + RandomUtils.nextLong(0, endExclusive));
		} else {
			return System.currentTimeMillis() + "_" + RandomUtils.nextLong(0, endExclusive);
		}
	}
	
	public static String generateGUID(){
		return UUID.randomUUID().toString();
	}

	public static String generateUserName() {
		return UUID.randomUUID().toString();
	}
}
