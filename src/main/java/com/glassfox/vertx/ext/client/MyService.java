package com.glassfox.vertx.ext.client;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

@ProxyGen
public interface MyService {
	final String SERVICE_ADDRESS = "service.glassfox.my"; 

	Future<JsonObject> status();
	
	Future<JsonObject> echo(JsonObject json);
	
	Future<String> getString();
	
	Future<Void> doSomething();
}
