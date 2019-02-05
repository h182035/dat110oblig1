package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.*;

public class RPCClient extends RPCStub {

	private MessagingClient msgclient;
	private Connection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void register(RPCStub remote) {
		remote.register(this);
	}
	
	public void connect() {
		// Er det så lett?
		connection = msgclient.connect();
			
	}
	
	public void disconnect() {
		// er det så lett?
		connection.close();
		
	}
	
	public byte[] call(byte[] rpcrequest) {
		
		byte[] rpcreply;
		
		/* TODO: 
		
		Make a remote call on the RPC server by sending a request message
		and receive a reply message
		
		rpcrequest is the marshalled rpcrequest from the client-stub
		rpctreply is the rpcreply to be unmarshalled by the client-stub
		
		*/
		Message melding = new Message();
		melding.decapsulate(rpcrequest);
		connection.send(melding);
		Message reply = connection.receive();
		rpcreply = reply.encapsulate();
	
		
		return rpcreply;
		
	}

}
