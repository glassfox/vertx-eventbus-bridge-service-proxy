package com.glassfox.vertx.ext.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.eventbusclient.EventBusClient;
import io.vertx.eventbusclient.EventBusClientOptions;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import io.vertx.serviceproxy.ServiceProxyBuilder;

@ExtendWith(VertxExtension.class)
public class TcpBridgeClientTest {
	private static final Logger logger = LogManager.getLogger();
	
	@BeforeEach
	protected void setUp(Vertx vertx, VertxTestContext testContext) throws Exception {
		vertx.deployVerticle(MainVerticle.class, new DeploymentOptions())
		.onComplete(testContext.succeedingThenComplete());
	}

	@Test
	void testService(VertxTestContext testContext) throws Exception {
		var options = new EventBusClientOptions()
			      .setHost("127.0.0.1")
			      .setPort(TcpBridgeMyVerticle.SERVICE_PORT);
		EventBusClient eventBusClient = EventBusClient.tcp(options);
		
		var instance = new ServiceProxyBuilder(new VertxForEventBusClientWrapper(eventBusClient))
				.setAddress(MyService.SERVICE_ADDRESS).build(MyService.class);
		
		instance.status()
		.map(j-> { 
			Assertions.assertEquals(new JsonObject(), j); 
			return null;
		})
		.onComplete(testContext.succeedingThenComplete());
	}
}
