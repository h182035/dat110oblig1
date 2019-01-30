package no.hvl.dat110.messaging;

import java.util.Arrays;
import java.util.Base64;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		this.payload = payload; // TODO: check for length within boundary. Done.
		System.out.println(payload.length);
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
				
		// TODO
		// encapulate/encode the payload of the message. Done, må sees nærmere på 
		byte[] encoded = Base64.getEncoder().encode(getData());
		System.out.println(new String(encoded));	
		
		return encoded;
		
	}


	public void decapsulate(byte[] received) {

		// TODO
		// decapsulate data in received and put in payload. Done, må sees nærmere på 
		byte[] decoded = Base64.getDecoder().decode(received);
		System.out.println(new String(decoded));		
		
	}
}
