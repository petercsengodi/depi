package hu.csega.depi.showcase.machinelearning.common;

import java.nio.ByteBuffer;

public class MachineUtil {

	public static float bytesToFloat(byte[] bytes, int offset) {
		byte[] b = new byte[] { bytes[offset], bytes[offset + 1], bytes[offset + 2], bytes[offset + 3]};
		float f = ByteBuffer.wrap(b).getInt() / 1000f;
		return f;
	}

	public static final int VALUE_SIZE = (Integer.SIZE / Byte.SIZE);

}
