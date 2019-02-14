package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;
import no.hvl.dat110.system.sensor.SensorImpl;

public class Sensor extends RPCStub {

	private byte RPCID = 1;
	
	public int read() {
		
		
		
		// implement marshalling, call and unmarshalling for read RPC method
		byte[] marshalled = RPCUtils.marshallVoid(RPCID);
		SensorImpl im = new SensorImpl();
		byte[] reply = im.invoke(marshalled);
		int temp = RPCUtils.unmarshallInteger(reply);
		
		
		return temp;
	}
	
}
