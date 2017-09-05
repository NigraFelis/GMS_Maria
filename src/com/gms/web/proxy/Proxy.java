package com.gms.web.proxy;

import javax.servlet.http.HttpServletRequest;

public abstract class Proxy {
	HttpServletRequest request;
	public Proxy(HttpServletRequest request) {
		this.request=request;
	}
}
