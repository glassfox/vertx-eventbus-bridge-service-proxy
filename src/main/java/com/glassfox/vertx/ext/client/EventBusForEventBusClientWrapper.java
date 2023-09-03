package com.glassfox.vertx.ext.client;

import java.util.Map;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.DeliveryContext;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.eventbus.MessageProducer;
import io.vertx.eventbusclient.EventBusClient;

public class EventBusForEventBusClientWrapper implements EventBus {
	private static final Logger logger = LogManager.getLogger();
	
	private EventBusClient client;

	public EventBusForEventBusClientWrapper(EventBusClient client) {
		this.client = client;
	}
	
	@Override
	public EventBus send(String address, @Nullable Object message) {
		client.send(address, message);
		return this;
	}

	static io.vertx.eventbusclient.DeliveryOptions parseOptions(DeliveryOptions options){
		
		var clientOptions = new io.vertx.eventbusclient.DeliveryOptions();
		options.getHeaders().forEach((k,v) -> clientOptions.addHeader(k,v));
		clientOptions.setSendTimeout(options.getSendTimeout());
		return clientOptions;
	}
	
	@Override
	public EventBus send(String address, @Nullable Object message, DeliveryOptions options) {
		client.send(address, message, parseOptions(options));
		return this;
	}

	static final MultiMap mapToMultiMap(Map<String,String> headers){
		throw new UnsupportedOperationException("mapToMultiMap"); 
	}
	
	@Override
	public <T> Future<Message<T>> request(String address, @Nullable Object message, DeliveryOptions options) {
		Promise<io.vertx.eventbusclient.Message<T>> p = Promise.promise();
		
		client.<T>request(address, message, parseOptions(options), res -> {
			if(res.failed()) 
				p.fail(res.cause()); 
			else 
				p.complete(res.result()); 
		});
		return p.future().map(ClientMessageWrapper::new);
	}

	@Override
	public EventBus publish(String address, @Nullable Object message) {
		client.publish(address, message);
		return this;
	}

	@Override
	public EventBus publish(String address, @Nullable Object message, DeliveryOptions options) {
		throw new UnsupportedOperationException("publish");
	}
	
	@Override
	public <T> MessageConsumer<T> consumer(String address) {
		throw new UnsupportedOperationException("consumer");
	}

	@Override
	public <T> MessageConsumer<T> consumer(String address, Handler<Message<T>> handler) {
		throw new UnsupportedOperationException("consumer");
	}

	@Override
	public <T> MessageConsumer<T> localConsumer(String address) {
		throw new UnsupportedOperationException("localConsumer");
	}

	@Override
	public <T> MessageConsumer<T> localConsumer(String address, Handler<Message<T>> handler) {
		throw new UnsupportedOperationException("localConsumer");
	}

	@Override
	public <T> MessageProducer<T> sender(String address) {
		throw new UnsupportedOperationException("sender");
	}

	@Override
	public <T> MessageProducer<T> sender(String address, DeliveryOptions options) {
		throw new UnsupportedOperationException("sender");
	}

	@Override
	public <T> MessageProducer<T> publisher(String address) {
		throw new UnsupportedOperationException("publisher");
	}

	@Override
	public <T> MessageProducer<T> publisher(String address, DeliveryOptions options) {
		throw new UnsupportedOperationException("publisher");
	}

	@Override
	public EventBus registerCodec(MessageCodec codec) {
		throw new UnsupportedOperationException("registerCodec");
	}

	@Override
	public EventBus unregisterCodec(String name) {
		throw new UnsupportedOperationException("unregisterCodec");
	}

	@Override
	public <T> EventBus registerDefaultCodec(Class<T> clazz, MessageCodec<T, ?> codec) {
		throw new IllegalStateException();
	}

	@Override
	public EventBus unregisterDefaultCodec(Class clazz) {
		throw new UnsupportedOperationException("unregisterDefaultCodec");
	}

	@Override
	public EventBus codecSelector(Function<Object, String> selector) {
		throw new UnsupportedOperationException("codecSelector");
	}

	@Override
	public <T> EventBus addOutboundInterceptor(Handler<DeliveryContext<T>> interceptor) {
		throw new UnsupportedOperationException("addOutboundInterceptor");
	}

	@Override
	public <T> EventBus removeOutboundInterceptor(Handler<DeliveryContext<T>> interceptor) {
		throw new UnsupportedOperationException("removeOutboundInterceptor");
	}

	@Override
	public <T> EventBus addInboundInterceptor(Handler<DeliveryContext<T>> interceptor) {
		throw new UnsupportedOperationException("addInboundInterceptor");
	}

	@Override
	public <T> EventBus removeInboundInterceptor(Handler<DeliveryContext<T>> interceptor) {
		throw new UnsupportedOperationException("removeInboundInterceptor");
	}

	@Override
	public EventBus clusterSerializableChecker(Function<String, Boolean> classNamePredicate) {
		throw new UnsupportedOperationException("clusterSerializableChecker");
	}

	@Override
	public EventBus serializableChecker(Function<String, Boolean> classNamePredicate) {
		throw new UnsupportedOperationException("serializableChecker");
	}
}
