package com.glassfox.vertx.ext.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.bridge.BridgeOptions;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.eventbus.bridge.tcp.TcpEventBusBridge;

public class TcpBridgeMyVerticle extends AbstractVerticle {
	private static final Logger logger = LogManager.getLogger();
	public static final Integer SERVICE_PORT = 50068;
	
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		logger.debug("start");
		Future<Void> lastFuture = mountTcpBridge(config());
		
		lastFuture.onComplete(startPromise);
	}
	
	private Future<Void> mountTcpBridge(JsonObject config) {
		logger.debug("mountTcpBridge");
		
		var bridge = TcpEventBusBridge.create(
		        vertx,
		        new BridgeOptions()
		            .addInboundPermitted(new PermittedOptions().setAddress(config.getString("inboind.address", MyService.SERVICE_ADDRESS)))
		            .addOutboundPermitted(new PermittedOptions().setAddress(config.getString("outboind.address", MyService.SERVICE_ADDRESS))));
		return bridge.listen(config.getInteger("port", SERVICE_PORT)).mapEmpty();
	}
}
