package com.glassfox.vertx.ext.client;

import io.vertx.core.json.JsonObject;
import io.vertx.eventbusclient.json.JsonCodec;

public class JsonObjectCodec extends JsonCodec {

	@Override
	public String encode(Object obj) {
		return JsonObject.mapFrom(obj).toString();
	}

	@Override
	public <T> T decode(String json, Class<T> type) {
		return (T)new JsonObject(json).getMap();
	}
}
