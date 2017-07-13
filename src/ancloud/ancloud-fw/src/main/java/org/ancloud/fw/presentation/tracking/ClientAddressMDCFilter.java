package org.ancloud.fw.presentation.tracking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientAddressMDCFilter extends AbstractMDCFilter {

	private static final String MDC_CLIENT_ADDRESS = "MDC_CLIENT_ADDRESS";

	@Override
	protected String getMDCKey(HttpServletRequest request, HttpServletResponse response) {
		return MDC_CLIENT_ADDRESS;
	}

	@Override
	protected String getMDCValue(HttpServletRequest request, HttpServletResponse response) {
		return request.getRemoteAddr();
	}

}
