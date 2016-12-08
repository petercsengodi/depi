package hu.csega.depi.showcase.machinelearning.common;

import java.nio.ByteBuffer;

public class MachineUtil {

	public static float bytesToFloat(byte[] bytes, int offset) {
		byte[] b = new byte[] { bytes[offset], bytes[offset + 1], bytes[offset + 2], bytes[offset + 3]};
		float f = ByteBuffer.wrap(b).getFloat();
		return f;
	}

	public static void floatToBytes(float f, byte[] bytes, int offset) {
		int bits = Float.floatToIntBits(f);
		bytes[offset] = (byte)(bits & 0xff);
		bytes[offset + 1] = (byte)((bits >> 8) & 0xff);
		bytes[offset + 2] = (byte)((bits >> 16) & 0xff);
		bytes[offset + 3] = (byte)((bits >> 24) & 0xff);
	}

	public static final int FLOAT_SIZE = (Float.SIZE / Byte.SIZE);

}
