package hu.csega.depi.showcase.patterns.strategy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Random;

import hu.csega.depi.showcase.framework.DesignPatternInfo;
import hu.csega.depi.showcase.framework.ShowcaseWindow;

public class ShowStrategyPattern extends ShowcaseWindow {

	protected ShowStrategyPattern() {
		super(TITLE);
	}

	@Override
	protected void init() {
		for(int i = 0; i < items.length; i++) {
			if(items[i] == null)
				items[i] = new DataItem();

			items[i].length = rnd.nextInt(300);
			items[i].r = rnd.nextInt(256);
			items[i].g = rnd.nextInt(256);
			items[i].b = 255 - items[i].r;
		}
	}

	@Override
	protected void clear() {
		cursor = 0;
	}

	@Override
	protected void doOneRound() {
		synchronized (this) {
			if(selectedStrategy.needsSwitching(items[cursor], items[cursor + 1])) {
				DataItem tmp = items[cursor];
				items[cursor] = items[cursor + 1];
				items[cursor + 1] = tmp;
			}

			cursor++;
			if(cursor >= items.length - 1)
				cursor = 0;
		}
	}

	@Override
	protected long waitingTime() {
		return 10l;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(inBox(e, 600, 20, 750, 100)) {
			synchronized (this) {
				selectedStrategy = lengthStrategy;
			}

			repaintCanvas();
		}

		if(inBox(e, 600, 120, 750, 200)) {
			synchronized (this) {
				selectedStrategy = brightnessStrategy;
			}

			repaintCanvas();
		}

		if(inBox(e, 600, 220, 750, 300)) {
			synchronized (this) {
				selectedStrategy = redStrategy;
			}

			repaintCanvas();
		}
	}

	@Override
	protected void paint2d(Graphics2D g) {
		int height = 550 / items.length;
		int y = 20;

		for(int i = 0; i < items.length; i++) {
			if(i == cursor) {
				g.setColor(Color.black);
				g.fillRect(2, y + 2, 6, height-4);
			}

			DataItem item = items[i];
			Color color = new Color(item.r, item.g, item.b);
			g.setColor(color);
			g.fillRect(10, y, item.length, height-2);
			y += height;
		}

		Color c = (selectedStrategy == lengthStrategy ? Color.green : Color.red);
		g.setColor(c);
		g.drawRect(600, 20, 150, 80);
		g.drawString("Press to use", 620, 40);
		g.drawString("length strategy!", 620, 60);

		c = (selectedStrategy == brightnessStrategy ? Color.green : Color.red);
		g.setColor(c);
		g.drawRect(600, 120, 150, 80);
		g.drawString("Press to use", 620, 140);
		g.drawString("brightness strategy!", 620, 160);

		c = (selectedStrategy == redStrategy ? Color.green : Color.red);
		g.setColor(c);
		g.drawRect(600, 220, 150, 80);
		g.drawString("Press to use", 620, 240);
		g.drawString("redness strategy!", 620, 260);
	}

	@Override
	protected DesignPatternInfo info() {
		return DesignPatternInfo.title(TITLE).resourceClass(this.getClass()).resourceName("hello.png");
	}

	public static void main(String[] args) throws Exception {
		new ShowStrategyPattern();
	}

	private Strategy lengthStrategy = new Strategy() {

		@Override
		public boolean needsSwitching(DataItem first, DataItem second) {
			return first.length > second.length;
		}
	};

	private Strategy brightnessStrategy = new Strategy() {

		@Override
		public boolean needsSwitching(DataItem first, DataItem second) {
			int lightFirst = first.r + first.g + first.b;
			int lightSecond = second.r + second.g + second.b;
			return lightFirst > lightSecond;
		}
	};

	private Strategy redStrategy = new Strategy() {

		@Override
		public boolean needsSwitching(DataItem first, DataItem second) {
			return first.r > second.r;
		}
	};

	private Strategy selectedStrategy = lengthStrategy;

	private DataItem[] items = new DataItem[50];
	private Random rnd = new Random(System.currentTimeMillis());
	private int cursor = 0;

	private static final String TITLE = "Strategy Pattern";

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
}
