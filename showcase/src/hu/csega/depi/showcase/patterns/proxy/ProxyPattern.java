package hu.csega.depi.showcase.patterns.proxy;

public class ProxyPattern {

	public interface Image {
		public void draw();
	}

	public static class ImageOnRemoteServer implements Image {

		public ImageOnRemoteServer(String url) {
			// starts loading image from url in another thread
		}

		public boolean isLoaded() {
			// true, if finished; false, if not
			return loaded;
		}

		public void draw() {
			// ...
		}

		private boolean loaded;
	}

	public static class ImageProxy implements Image {

		public ImageProxy(ImageOnRemoteServer imageOnRemoteServer) {
			this.imageOnRemoteServer = imageOnRemoteServer;
		}

		@Override
		public void draw() {
			if(!imageOnRemoteServer.isLoaded()) {
				// write text on screen "image not loaded yet"
			} else {
				imageOnRemoteServer.draw();
			}
		}

		private ImageOnRemoteServer imageOnRemoteServer;
	}

	public static Image getImage(String url) {
		return new ImageProxy(new ImageOnRemoteServer(url));
	}
}
