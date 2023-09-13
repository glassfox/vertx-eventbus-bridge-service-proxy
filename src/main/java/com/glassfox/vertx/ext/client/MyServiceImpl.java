package com.glassfox.vertx.ext.client;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public class MyServiceImpl implements MyService {

	static final String STRING_RESULT = "string";
	
	@Override
	public Future<JsonObject> status() {
		return Future.succeededFuture(new JsonObject());
	}

	@Override
	public Future<JsonObject> echo(JsonObject json) {
		return Future.succeededFuture(json);
	}

	@Override
	public Future<String> getString() {
		return Future.succeededFuture(STRING_RESULT);
	}

	@Override
	public Future<Void> doSomething() {
		return Future.succeededFuture();
	}

}
