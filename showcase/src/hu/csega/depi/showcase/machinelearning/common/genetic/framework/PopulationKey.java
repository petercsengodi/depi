package hu.csega.depi.showcase.machinelearning.common.genetic.framework;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PopulationKey implements Comparable<PopulationKey>, Serializable {

	public PopulationKey(double distance, byte[] gene) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(gene);
			this.uuid = new String(thedigest);
			this.distance = distance;
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public int compareTo(PopulationKey otherKey) {
		return Double.compare(this.distance, otherKey.distance);
	}

	public double getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return String.format("%10.4f", distance);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PopulationKey other = (PopulationKey) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	private double distance;
	private String uuid;

	private static final long serialVersionUID = 1L;
}
