package com.thinkgem.jeesite.modules.ws.impl;

import javax.jws.WebService;

import com.thinkgem.jeesite.modules.ws.HelloWorld;

@WebService(endpointInterface="com.thinkgem.jeesite.modules.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String play(String info) {
		// TODO Auto-generated method stub
		System.out.println("play called!");
		return "Dota [ " + info + " ]";
	}

}
