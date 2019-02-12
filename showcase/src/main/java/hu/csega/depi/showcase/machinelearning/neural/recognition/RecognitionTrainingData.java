package hu.csega.depi.showcase.machinelearning.neural.recognition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RecognitionTrainingData implements Serializable {

	public RecognitionCharacter[] items = new RecognitionCharacter[100];
	public int length;

	public static RecognitionTrainingData load(File f) {
		try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(f))) {
            return (RecognitionTrainingData) stream.readObject();
	    } catch(Exception ex) {
	        throw new RuntimeException("Loading failed!", ex);
	    }
	}

    public static void save(File f, RecognitionTrainingData trainingData) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(f))) {
            stream.writeObject(trainingData);
        } catch(Exception ex) {
            throw new RuntimeException("Saving failed!", ex);
        }
    }

    public static final String FILENAME = "tmp.res";

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
}
