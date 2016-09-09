package com.mkyong.ws;
 
import javax.jws.WebService;
 
//Service Implementation
@WebService(endpointInterface = "com.mkyong.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld{
 
	@Override
	public String getHelloWorldAsString(String data) {
		
		System.out.println(data);
		return "accepted";
	}
 
}