package org.ancloud.fw.presentation.helper;

import java.io.UnsupportedEncodingException;

import org.springframework.web.util.UriUtils;

public class Jstl {
	public static String escape(String str){
		try {
			return UriUtils.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return str;
	}
}
