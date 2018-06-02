package hu.csega.depi.showcase.machinelearning.common.genetic.framework;

import java.io.Serializable;
import java.util.Random;

public class Chromosome implements Serializable {

	public static final Random RND = new Random(System.currentTimeMillis());

	public static Chromosome createEmptyGene(int length) {
		return new Chromosome(length);
	}

	public static Chromosome createRandomGene(int length) {
		Chromosome chromosome = new Chromosome(length);
		for(int i = 0; i < length; i++)
			chromosome.genes[i] = getRandomByte();
		return chromosome;
	}

	public Chromosome(int length) {
		this.genes = new byte[length];
	}

	public Chromosome(byte[] genes) {
		this.genes = new byte[genes.length];
		System.arraycopy(genes, 0, this.genes, 0, genes.length);
	}

	public static ChromosomePair crossOverSameLength(Chromosome chromosome1, Chromosome chromosome2) {
		int length = chromosome1.genes.length;
		int prefix = RND.nextInt(length - 1) + 1;
		int suffix = length - prefix;

		ChromosomePair children = new ChromosomePair();

		children.chromosome1 = new Chromosome(length);
		System.arraycopy(chromosome1.genes, 0, children.chromosome1.genes, 0, prefix);
		System.arraycopy(chromosome2.genes, prefix, children.chromosome1.genes, prefix, suffix);

		children.chromosome2 = new Chromosome(length);
		System.arraycopy(chromosome2.genes, 0, children.chromosome2.genes, 0, prefix);
		System.arraycopy(chromosome1.genes, prefix, children.chromosome2.genes, prefix, suffix);

		return children;
	}

	public static ChromosomePair crossOverVariateLength(Chromosome chromosome1, Chromosome chromosome2) {
		int length1 = chromosome1.genes.length;
		int prefix1 = RND.nextInt(length1 - 2) + 1;
		int suffix1 = length1 - prefix1;
		int length2 = chromosome2.genes.length;
		int prefix2 = RND.nextInt(length2 - 2) + 1;
		int suffix2 = length2 - prefix2;

		ChromosomePair children = new ChromosomePair();

		children.chromosome1 = new Chromosome(prefix1 + suffix2);
		System.arraycopy(chromosome1.genes, 0, children.chromosome1.genes, 0, prefix1);
		System.arraycopy(chromosome2.genes, prefix2, children.chromosome1.genes, prefix1, suffix2);

		children.chromosome2 = new Chromosome(prefix2 + suffix1);
		System.arraycopy(chromosome2.genes, 0, children.chromosome2.genes, 0, prefix2);
		System.arraycopy(chromosome1.genes, prefix1, children.chromosome2.genes, prefix2, suffix1);

		return children;
	}

	public static Chromosome mutate(Chromosome chromosome, int maxMutatedBytes) {
		int numberOfMutations;
		if(maxMutatedBytes < 2)
			numberOfMutations = 1;
		else
			numberOfMutations = RND.nextInt(maxMutatedBytes - 1) + 1;

		Chromosome result = new Chromosome(chromosome.genes);

		int length = chromosome.genes.length;
		for(int i = 0; i < numberOfMutations; i++) {
			int modify = RND.nextInt(length);
			byte newValue = getRandomByte();;
			result.genes[modify] = newValue;
		}

		return result;
	}

	public static byte getRandomByte() {
		return ((byte)(RND.nextInt(256) - 128));
	}

	public static double getRandomDouble() {
		return RND.nextDouble();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");

		for(int i = 0; i < genes.length; i++) {
			builder.append(' ').append((int)(genes[i]));
		}

		return builder.append(" ]").toString();
	}

	public byte[] getGenes() {
		return genes;
	}

	private byte[] genes;

	private static final long serialVersionUID = 1L;
}
