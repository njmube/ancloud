package org.ancloud.domain.constant;

import java.io.File;

public class SystemConstant {
	public static final String PATH_HOME = System.getProperty("user.home") + File.separator + "ancloud";
	public static final String PATH_QR_CODE = PATH_HOME + File.separator + "qr";
	
	public static final String BEAN_NAME_MESSAGES = "messages";
	public static final String BEAN_NAME_PAGING = "page";
}
