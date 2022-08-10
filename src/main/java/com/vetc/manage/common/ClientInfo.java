package com.vetc.manage.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class ClientInfo {

	@Autowired
	private HttpServletRequest request;

	public String getClientIp() {
		String remoteAddr = "";
		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || remoteAddr.trim().isEmpty()) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		return remoteAddr;
	}

	public String getSessionId() {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			return session.getId();
		} catch (Exception e) {
			return null;
		}
	}
}
