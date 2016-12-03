package hu.csega.depi.showcase.framework;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class ShowcaseExplanation extends JDialog {

	public static void showDesignPattern(ShowcaseWindow window, DesignPatternInfo info) {
		BufferedImage img;
		try (InputStream stream = info.resourceClass.getResourceAsStream(info.resourceName)) {
			img = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		ShowcaseExplanationCanvas canvas = new ShowcaseExplanationCanvas(img);

		ShowcaseExplanation explanation = new ShowcaseExplanation(window, info.title);
		Container container = explanation.getContentPane();;
		container.setLayout(new FlowLayout());
		container.add(canvas);

		explanation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		explanation.pack();
		explanation.setVisible(true);
	}

	public ShowcaseExplanation(ShowcaseWindow window, String title) {
		super(window, title, Dialog.ModalityType.DOCUMENT_MODAL);
	}

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;

}
