package no.hvl.dat110.messaging;

import java.util.Arrays;
import java.util.Base64;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		if(payload.length <= MessageConfig.SEGMENTSIZE) {
			this.payload = payload; 
		}
		
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
		int lengde = payload.length;
		byte[] encapsulated = new byte[MessageConfig.SEGMENTSIZE];
		encapsulated[0] = (byte) lengde;
		
		for(int i = 1; i < lengde+1; i++) {
			encapsulated[i] = payload[i-1];
		}
		
		return encapsulated;
		
	}


	public void decapsulate(byte[] received) {

		// decapsulate data in received and put in payload. Done, må sees nærmere på
		payload = new byte[received[0]];
		System.out.println(received[0]);
		for(int i = 1; i < payload.length; i++) {
			payload[i-1] = received[i];
		}
		
	}
}
