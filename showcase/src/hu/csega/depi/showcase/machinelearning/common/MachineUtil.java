package hu.csega.depi.showcase.machinelearning.common;

import java.nio.ByteBuffer;

public class MachineUtil {

	public static float bytesToFloat(byte[] bytes, int offset) {
		byte[] b = new byte[VALUE_SIZE];
		for(int i = 0; i < VALUE_SIZE; i++)
			b[i] = bytes[offset + i];
		float f = ByteBuffer.wrap(b).getInt() / 1000f;
		return f;
	}

	public static final int VALUE_SIZE = (Integer.SIZE / Byte.SIZE);

}
