package com.glassfox.vertx.ext.client;

import java.io.InputStream;
import java.util.Scanner;

import io.vertx.core.json.JsonObject;

public class TestUtils {
	public static String readFile(String relativePath) {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
        
        InputStream resourceAsStream = cl.getResourceAsStream(relativePath);
        if(null == resourceAsStream) return null;
        
		try (Scanner scanner = new Scanner(resourceAsStream).useDelimiter("\\A")) {
        		return scanner.next();
        }
	}
	
	public static JsonObject readJson(String relativePath) {
		return new JsonObject(readFile(relativePath));
	}
}
