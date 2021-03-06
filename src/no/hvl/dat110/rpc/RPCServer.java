package no.hvl.dat110.rpc;

import java.util.HashMap;

import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;
import no.hvl.dat110.system.sensor.SensorImpl;

public class RPCServer {

	private MessagingServer msgserver;
	private Connection connection;

	// hashmap to register RPC methods which are required to implement RPCImpl

	private HashMap<Integer, RPCImpl> services;

	public RPCServer(int port) {

		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Integer, RPCImpl>();

		// the stop RPC methods is built into the server
		services.put((int) RPCCommon.RPIDSTOP, new RPCServerStopImpl());
	}

	public void run() {

		System.out.println("RPC SERVER RUN - Services: " + services.size());

		connection = msgserver.accept();

		System.out.println("RPC SERVER ACCEPTED");

		boolean stop = false;

		while (!stop) {

			int rpcid = 0;
			RPCImpl method = null;
			// TODO
			// - receive message containing RPC request
			// - find the identifier for the RPC methods to invoke
			// - lookup the methods to be invoked
			// - invoke the method
			// - send back message containing RPC reply

			Message received = connection.receive();

			byte[] data = received.getData();
			if (data.length > 0) {
				rpcid = data[0];	

				method = services.get(rpcid);
			}

			if (method != null) {
				SensorImpl im = new SensorImpl();
				byte[] reply = im.invoke(data);
				int temp = RPCUtils.unmarshallInteger(reply);
				Message melding = new Message(method.invoke(data));
				connection.send(melding);
			}

			if (rpcid == RPCCommon.RPIDSTOP) {
				stop = true;
			}

		}

	}

	public void register(int rmid, RPCImpl impl) {
		services.put(rmid, impl);
	}

	public void stop() {
		connection.close();
		msgserver.stop();

	}
}
