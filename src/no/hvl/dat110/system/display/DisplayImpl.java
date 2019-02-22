package no.hvl.dat110.system.display;

import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;
//Server side
public class DisplayImpl implements RPCImpl {

	public void write(String message) {
		System.out.println("DISPLAY:" + message);
	}
	
	public byte[] invoke(byte[] request) {
		
		// implement unmarshalling, call, and marshall for write RPC method
		String message = RPCUtils.unmarshallString(request);
		write(message);
		
		byte rpcid = request[0];
		byte[] reply = RPCUtils.marshallVoid(rpcid);
		
		return reply;
	}
}
