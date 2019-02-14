package no.hvl.dat110.messaging;

import java.io.IOException;

import java.net.Socket;

public class MessagingClient {

	private String server;
	private int port;

	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}

	/**
	 * @return null if failed to connect
	 */
	public Connection connect() {

		Socket clientSocket;
		Connection connection = null;
		
		try {
			clientSocket = new Socket(server, port);
			connection = new Connection(clientSocket);
		} catch (IOException e) {
			System.err.println("KLARTE IKKE Å LAGE SOCKET TIL CLIENT");
			e.printStackTrace();
		}
		
		return connection;
	}
}
