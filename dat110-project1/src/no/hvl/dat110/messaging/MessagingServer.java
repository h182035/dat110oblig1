package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MessagingServer {

	private ServerSocket welcomeSocket;

	public MessagingServer(int port) {

		try {

			this.welcomeSocket = new ServerSocket(port);

		} catch (IOException ex) {

			System.out.println("Messaging server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * @return null if failed to connect
	 */
	public Connection accept() {

		Connection connection = null;

		try {
			Socket acceptedSocket = welcomeSocket.accept();
			connection = new Connection(acceptedSocket);
		} catch (IOException e) {
			System.err.println("KLARTE IKKE Å LAGE SOCKET TIL CLIENT");
			e.printStackTrace();
		}

		return connection;

	}

	public void stop() {

		if (welcomeSocket != null) {

			try {
				welcomeSocket.close();
			} catch (IOException ex) {

				System.out.println("Messaging server: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

}
