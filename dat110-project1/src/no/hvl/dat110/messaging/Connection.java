package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {

	private DataOutputStream outStream; // for writing bytes to the TCP connection
	private DataInputStream inStream; // for reading bytes from the TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public Connection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {
		// Tror det fungerer som det skal. Litt usikker på "encapsulate" - Ole
		// encapsulate the data contained in the message and write to the output stream
		try {
			outStream.write(message.encapsulate());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Message receive() {

		Message message;
		byte[] recvbuf;
		// Litt usikker på om i får inn antall bytes, og litt usikker på om bytes blir lagret i message
		// read a segment from the input stream and decapsulate into message
		int i;
		try {
			i = inStream.readByte();
			recvbuf = new byte[i];
			for (int j = 0; j < i; j++) {

				recvbuf[j] = inStream.readByte();

			}
			message = new Message();
			message.decapsulate(recvbuf);
			return message;
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}

		

		
		
	}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {

			outStream.close();
			inStream.close();

			socket.close();
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}