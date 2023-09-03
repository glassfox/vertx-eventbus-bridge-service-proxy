package com.glassfox.vertx.ext.client;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import io.netty.channel.EventLoopGroup;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.TimeoutStream;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.dns.DnsClient;
import io.vertx.core.dns.DnsClientOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.shareddata.SharedData;
import io.vertx.core.spi.VerticleFactory;
import io.vertx.eventbusclient.EventBusClient;

public class VertxForEventBusClientWrapper implements Vertx {

	private EventBus eventBus;

	public VertxForEventBusClientWrapper(EventBusClient eventBusClient) {
		eventBus = new EventBusForEventBusClientWrapper(eventBusClient);
	}
	
	@Override
	public Context getOrCreateContext() {
		throw new UnsupportedOperationException("getOrCreateContext");
	}

	@Override
	public NetServer createNetServer(NetServerOptions options) {
		throw new UnsupportedOperationException("createNetServer");
	}

	@Override
	public NetClient createNetClient(NetClientOptions options) {
		throw new UnsupportedOperationException("createNetClient");
	}

	@Override
	public HttpServer createHttpServer(HttpServerOptions options) {
		throw new UnsupportedOperationException("createHttpServer");
		
	}

	@Override
	public HttpClient createHttpClient(HttpClientOptions options) {
		throw new UnsupportedOperationException("createHttpClient");
	}

	@Override
	public DatagramSocket createDatagramSocket(DatagramSocketOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileSystem fileSystem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventBus eventBus() {
		return eventBus;
	}

	@Override
	public DnsClient createDnsClient(int port, String host) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DnsClient createDnsClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DnsClient createDnsClient(DnsClientOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SharedData sharedData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long setTimer(long delay, Handler<Long> handler) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TimeoutStream timerStream(long delay) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long setPeriodic(long initialDelay, long delay, Handler<Long> handler) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TimeoutStream periodicStream(long initialDelay, long delay) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cancelTimer(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void runOnContext(Handler<Void> action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Future<Void> close() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close(Handler<AsyncResult<Void>> completionHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deployVerticle(Verticle verticle, Handler<AsyncResult<String>> completionHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Future<String> deployVerticle(Verticle verticle, DeploymentOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<String> deployVerticle(Class<? extends Verticle> verticleClass, DeploymentOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<String> deployVerticle(Supplier<Verticle> verticleSupplier, DeploymentOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deployVerticle(Verticle verticle, DeploymentOptions options,
			Handler<AsyncResult<String>> completionHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deployVerticle(Class<? extends Verticle> verticleClass, DeploymentOptions options,
			Handler<AsyncResult<String>> completionHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deployVerticle(Supplier<Verticle> verticleSupplier, DeploymentOptions options,
			Handler<AsyncResult<String>> completionHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Future<String> deployVerticle(String name, DeploymentOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deployVerticle(String name, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Future<Void> undeploy(String deploymentID) {
		throw new UnsupportedOperationException("undeploy");
	}

	@Override
	public void undeploy(String deploymentID, Handler<AsyncResult<Void>> completionHandler) {
		throw new UnsupportedOperationException("undeploy");
		
	}

	@Override
	public Set<String> deploymentIDs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerVerticleFactory(VerticleFactory factory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterVerticleFactory(VerticleFactory factory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<VerticleFactory> verticleFactories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isClustered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EventLoopGroup nettyEventLoopGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkerExecutor createSharedWorkerExecutor(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkerExecutor createSharedWorkerExecutor(String name, int poolSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkerExecutor createSharedWorkerExecutor(String name, int poolSize, long maxExecuteTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkerExecutor createSharedWorkerExecutor(String name, int poolSize, long maxExecuteTime,
			TimeUnit maxExecuteTimeUnit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNativeTransportEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Throwable unavailableNativeTransportCause() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertx exceptionHandler(@Nullable Handler<Throwable> handler) {
		throw new UnsupportedOperationException("exceptionHandler");
		
	}

	@Override
	public @Nullable Handler<Throwable> exceptionHandler() {
		throw new UnsupportedOperationException("exceptionHandler");
	}

}
