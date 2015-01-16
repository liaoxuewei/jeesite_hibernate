package com.thinkgem.jeesite.modules.ws;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
	public String play(String info);
}
