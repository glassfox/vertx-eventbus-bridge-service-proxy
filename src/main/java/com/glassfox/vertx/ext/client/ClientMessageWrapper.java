package com.glassfox.vertx.ext.client;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public class ClientMessageWrapper<T> implements Message<T> {
	private static final Logger logger = LogManager.getLogger();
	
	private io.vertx.eventbusclient.Message<T> delegate;

	public ClientMessageWrapper(io.vertx.eventbusclient.Message<T> delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public String address() {
		return delegate.address();
	}

	@Override
	public MultiMap headers() {
		return EventBusForEventBusClientWrapper.mapToMultiMap(delegate.headers());
	}

	@Override
	public T body() {
		return (T)(new JsonObject((Map<String, Object>)delegate.body()));
	}

	@Override
	public @Nullable String replyAddress() {
		return delegate.replyAddress();
	}

	@Override
	public boolean isSend() {
		return true;
	}

	@Override
	public void reply(@Nullable Object message, DeliveryOptions options) {
		delegate.reply(message, EventBusForEventBusClientWrapper.parseOptions(options));
	}

	@Override
	public <R> Future<Message<R>> replyAndRequest(@Nullable Object message, DeliveryOptions options) {
		Promise<io.vertx.eventbusclient.Message<R>> p = Promise.promise();
		delegate.<R>replyAndRequest(message, EventBusForEventBusClientWrapper.parseOptions(options), event -> {
			if(event.failed()) 
				p.fail(event.cause());
			else 
				p.complete(event.result());
		});
		return p.future().map(ClientMessageWrapper::new);
	}
}