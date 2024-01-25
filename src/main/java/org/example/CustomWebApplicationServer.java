package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomWebApplicationServer {
	private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);
	private final int port;

	public CustomWebApplicationServer(int port) {
		this.port = port;
	}

	public void start() throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			logger.info("[CustomWebApplicationServer] started {} port.", port);

			Socket clientSocket;
			logger.info("[CustomWebApplicationServer] waiting for client.");

			while ((clientSocket = serverSocket.accept()) != null) {
				logger.info("[CustomWebApplicationServer] client connected.");

				try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
					BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
					DataOutputStream dos = new DataOutputStream(out);

					String line;
					while ((line = br.readLine()) != "") {
						System.out.println(line);
					}
				}
			}
		}
	}
}
