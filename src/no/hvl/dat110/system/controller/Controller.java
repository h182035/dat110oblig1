package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServerStopStub;
import no.hvl.dat110.rpc.RPCUtils;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {
		
		Display display;
		Sensor sensor;
		
		RPCClient displayclient,sensorclient;
		
		System.out.println("Controller starting ...");
				
		RPCServerStopStub stopdisplay = new RPCServerStopStub();
		RPCServerStopStub stopsensor = new RPCServerStopStub();
		
		
		
		// TODO
		// create display and sensor object
		// create RPC clients for display device and sensor device
		// register RPC methods in the RPC layer
		
		sensor = new Sensor();
		display = new Display();
		displayclient = new RPCClient(Common.DISPLAYHOST,Common.DISPLAYPORT);
		sensorclient = new RPCClient(Common.SENSORHOST,Common.SENSORPORT);
		displayclient.connect();
		sensorclient.connect();
		displayclient.register(display);
		sensorclient.register(sensor);
		
		// register stop methods in the RPC middleware
		displayclient.register(stopdisplay);
		sensorclient.register(stopsensor);
		
		// loop while reading from sensor and write to display via RPC
		for(int i = 0; i < 5; i++) {
			String read = String.valueOf(sensor.read());
//			byte sensorid = 1;
//			byte[] readmarshalled = RPCUtils.marshallInteger(sensorid, read);
//			byte[] sensorreply = sensorclient.call(readmarshalled);
//			displayclient.call(sensorreply);
			
			display.write(read);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
		
		stopdisplay.stop();
		stopsensor.stop();
		
		displayclient.disconnect();
		sensorclient.disconnect();
		
		
	
		
		
		System.out.println("Controller stopping ...");
		
	}
}
