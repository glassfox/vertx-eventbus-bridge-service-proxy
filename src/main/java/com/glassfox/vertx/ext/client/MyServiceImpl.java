package com.glassfox.vertx.ext.client;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public class MyServiceImpl implements MyService {

	@Override
	public Future<JsonObject> status() {
		return Future.succeededFuture(new JsonObject());
	}

}
