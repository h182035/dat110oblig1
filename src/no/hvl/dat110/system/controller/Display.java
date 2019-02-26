package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCStub;
import no.hvl.dat110.rpc.RPCUtils;
import no.hvl.dat110.system.display.DisplayImpl;
//Client side
public class Display extends RPCStub {

	private byte RPCID = 1;

	public void write(String message) {

		// implement marshalling, call and unmarshalling for write RPC method
		byte[] marshalled = rmiclient.call(RPCUtils.marshallString(RPCID, message));
		RPCUtils.unmarshallString(marshalled);
		
	}
}
