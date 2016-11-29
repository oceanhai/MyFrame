/*
 * Name        : HexEncoder.java
 * Classes     : HexEncoder
 * Version     : 1.0
 * Date        : 2013-09-13
 *
 * Copyright 2013 CMCC.  All rights reserved.
 * This material, including documentation and any related computer
 * programs, is protected by copyright controlled by CMCC.  All
 * rights are reserved.  Copying, including reproducing, storing,
 * adapting or translating, any or all of this material requires the
 * prior written consent of CMCC.  This material also contains
 * confidential information which may not be disclosed to others
 * without the prior written consent of CMCC.
 */
package com.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class HexEncoder {

	protected static final byte[] encodingTable = { (byte) '0', (byte) '1',
			(byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6',
			(byte) '7', (byte) '8', (byte) '9', (byte) 'a', (byte) 'b',
			(byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f' };

	protected static final HashMap<Byte, Byte> decodingTable = new HashMap<Byte, Byte>();

	static {
		for (int i = 0; i < encodingTable.length; i++) {
			decodingTable.put(encodingTable[i], (byte) i);
		}

		decodingTable.put((byte) 'A', decodingTable.get((byte) 'a'));
		decodingTable.put((byte) 'B', decodingTable.get((byte) 'b'));
		decodingTable.put((byte) 'C', decodingTable.get((byte) 'c'));
		decodingTable.put((byte) 'D', decodingTable.get((byte) 'd'));
		decodingTable.put((byte) 'E', decodingTable.get((byte) 'e'));
		decodingTable.put((byte) 'F', decodingTable.get((byte) 'f'));
	}

	private static int encode(byte[] data, int off, int length, OutputStream out)
			throws IOException {
		for (int i = off; i < (off + length); i++) {
			int v = data[i] & 0xff;
			out.write(encodingTable[(v >>> 4)]);
			out.write(encodingTable[v & 0xf]);
		}
		return length * 2;
	}

	private static boolean ignore(char c) {
		return (c == '\n' || c == '\r' || c == '\t' || c == ' ');
	}

	private static int decode(byte[] data, int off, int length, OutputStream out)
			throws IOException {
		byte b1, b2;
		int outLen = 0;
		int end = off + length;
		while (end > off) {
			if (!ignore((char) data[end - 1])) {
				break;
			}
			end--;
		}

		int i = off;
		byte temp;
		while (i < end) {
			while (i < end && ignore((char) data[i])) {
				i++;
			}
			temp = data[i++];
			if (decodingTable.containsKey(temp)) {
				b1 = decodingTable.get(temp);
			} else {
				throw new IllegalArgumentException("data is not 0-9 or a-f!");
			}

			while (i < end && ignore((char) data[i])) {
				i++;
			}
			temp = data[i++];
			if (decodingTable.containsKey(temp)) {
				b2 = decodingTable.get(temp);
			} else {
				throw new IllegalArgumentException("data is not 0-9 or a-f!");
			}
			out.write((b1 << 4) | b2);
			outLen++;
		}
		return outLen;
	}

	private static int decode(String data, OutputStream out) throws IOException {
		return decode(data.getBytes("UTF-8"), 0, data.length(), out);
	}

	public static byte[] encode(byte[] data) {
		return encode(data, 0, data.length);
	}

	public static byte[] encode(byte[] data, int off, int length) {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		try {
			encode(data, off, length, bOut);
		} catch (IOException e) {
			throw new RuntimeException("exception encoding Hex string: " + e);
		}
		return bOut.toByteArray();
	}

	public static byte[] decode(byte[] data) {
		if (data == null || data.length % 2 != 0) {
			throw new IllegalArgumentException(
					"Hex length should not be odd number");
		}
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		try {
			decode(data, 0, data.length, bOut);
		} catch (IOException e) {
			throw new RuntimeException("exception decoding Hex string: " + e);
		}
		return bOut.toByteArray();
	}

	public static byte[] decode(String data) {
		if (data == null || data.length() % 2 != 0) {
			throw new IllegalArgumentException(
					"Hex length should not be odd number");
		}
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		try {
			decode(data, bOut);
		} catch (IOException e) {
			throw new RuntimeException("exception decoding Hex string: " + e);
		}
		return bOut.toByteArray();
	}
}
