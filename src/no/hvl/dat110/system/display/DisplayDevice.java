package no.hvl.dat110.system.display;

import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.controller.Common;


public class DisplayDevice {
	
	public static void main(String[] args) {
		
		System.out.println("Display server starting ...");
		
		DisplayImpl display = new DisplayImpl();
		
		RPCServer displayServer = new RPCServer(Common.DISPLAYPORT);
		
		//f�rste tall er n�kkel. Kanskje 2 er feil n�kkel? n�kkelen er 1 i sensordevice.
		displayServer.register(1, display);
		
		displayServer.run();
		
		displayServer.stop();
		
		System.out.println("Display server stopping ...");
		
	}
}
