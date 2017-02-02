package org.ancloud.fw.core.util;

import org.apache.commons.lang3.RandomUtils;

public class GenerationUtil {
	public static String generateCode(String prefix){
		return GenerationUtil.generateCode(prefix, 10000L);
	}
	
	public static String generateCode(String prefix,Long endExclusive){
		if(prefix!=null){
			return prefix.concat(System.currentTimeMillis() + "." + RandomUtils.nextLong(0, endExclusive));
		} else {
			return System.currentTimeMillis() + "." + RandomUtils.nextLong(0, endExclusive);
		}
	}
}
