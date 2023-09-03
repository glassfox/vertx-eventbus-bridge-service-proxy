package com.glassfox.vertx.ext.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.serviceproxy.ServiceBinder;

public class MainVerticle extends AbstractVerticle {
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		var binder = new ServiceBinder(vertx);
		var consumer = binder.setAddress(MyService.SERVICE_ADDRESS).register(MyService.class, new MyServiceImpl());
		consumer.endHandler(logger::error);
		
		vertx.deployVerticle(TcpBridgeMyVerticle.class, new DeploymentOptions().setConfig(config().getJsonObject("bridge")))
		.<Void>mapEmpty()
		.onComplete(startPromise);
	}
}
