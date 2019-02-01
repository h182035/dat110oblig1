package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class RPCUtils {

	public static byte[] marshallString(byte rpcid, String str){
		// Sett rpcid -> [0] og resten av stringen inn etterpå.
		// Hva er rpcid? Og hva blir den brukt til?s
		int length;

		if (str == null)
			length = 0;
		else
			length = str.length();

		if (length > 128) {
			System.err.println("THE STRING IS TOO LONG! RETURNING NULL");
			return null;
		}

		byte[] encoded = new byte[length + 1];

		encoded[0] = rpcid;

		byte[] temp = str.getBytes();
		for (int i = 1; i < encoded.length; i++) {
			encoded[i] = temp[i - 1];
		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {
		
		byte[] cutout = Arrays.copyOfRange(data, 1, data.length);
		
		
		return new String(cutout);
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = new byte[1];

		encoded[0] = rpcid;

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO: unmarshall void type
		// Um... what is to marshall?
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] result = new byte[5];
		result[0] = rpcid;

		result[1] = (byte) (x >> 24);
		result[2] = (byte) (x >> 16);
		result[3] = (byte) (x >> 8);
		result[4] = (byte) (x /* >> 0 */);

		return result;
	}

	public static int unmarshallInteger(byte[] data) {
		return ByteBuffer.wrap(Arrays.copyOfRange(data, 1, 5)).getInt();
	}
}
