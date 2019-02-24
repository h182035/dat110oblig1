package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingClient;

public class RPCClient extends RPCStub {

	private MessagingClient msgclient;
	private Connection connection;

	public RPCClient(String server, int port) {

		msgclient = new MessagingClient(server, port);
	}

	public void register(RPCStub remote) {
		remote.register(this);
	}

	public void connect() {
		// Er det s� lett?
		connection = msgclient.connect();

	}

	public void disconnect() {
		// er det s� lett?
		connection.close();

	}

	public byte[] call(byte[] rpcrequest) {

		byte[] rpcreply = {0};

		/*
		 * TODO:
		 * 
		 * Make a remote call on the RPC server by sending a request message and receive
		 * a reply message
		 * 
		 * rpcrequest is the marshalled rpcrequest from the client-stub rpctreply is the
		 * rpcreply to be unmarshalled by the client-stub
		 * 
		 */
		Message melding = new Message(rpcrequest);
		if (connection.send(melding)) {
			Message reply = connection.receive();
			rpcreply = reply.getData();
		} else {
			disconnect();
		}

		return rpcreply;

	}

}
