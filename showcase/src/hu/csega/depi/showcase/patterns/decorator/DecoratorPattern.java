package hu.csega.depi.showcase.patterns.decorator;

public class DecoratorPattern {

	public interface WindowElement {
		public void draw(int leftOffset, int topOffset, int width, int height);
	}

	public static class Image implements WindowElement {

		@Override
		public void draw(int leftOffset, int topOffset, int width, int height) {
			// draws the part of the image
		}

	}

	public static class ScrollBars implements WindowElement {
		public ScrollBars(WindowElement element) {
			this.element = element;
		}

		@Override
		public void draw(int leftOffset, int topOffset, int width, int height) {
			// draw scroll bars

			element.draw(scrollPositionX, scrollPositionY, width - SCROLLBAR_WIDTH, height - SCROLLBAR_WIDTH);
		}

		private int scrollPositionX;
		private int scrollPositionY;
		private WindowElement element;
		private static final int SCROLLBAR_WIDTH = 10;
	}

	public static void main(String[] args) throws Exception {
		WindowElement element = new ScrollBars(new Image());
		element.draw(0, 0, 640, 480);
	}
}
