package com.freefire.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class ApiServer {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        String portEnv = System.getenv("PORT");
        if (portEnv != null) port = Integer.parseInt(portEnv);

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/v1/auth", new AuthHandler());
        server.setExecutor(null);
        server.start();
        
        System.out.println("[+] API de Segurança Ativa!");
    }

    static class AuthHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            
            // Resposta que libera o Bypass
            String response = "{\"status\":\"authorized\", \"token\":\"SECURE_TOKEN_BYPASS\", \"shield\":\"active\"}";
            byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
            
            exchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(responseBytes);
            os.close();
        }
    }
}
