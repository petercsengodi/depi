package hu.csega.depi.showcase.patterns.strategy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Random;

import hu.csega.depi.showcase.framework.DesignPatternInfo;
import hu.csega.depi.showcase.framework.ShowcaseWindow;
import hu.csega.depi.showcase.patterns.strategy.impl.DataItem;
import hu.csega.depi.showcase.patterns.strategy.impl.DataSorter;
import hu.csega.depi.showcase.patterns.strategy.impl.SortingByBrightness;
import hu.csega.depi.showcase.patterns.strategy.impl.SortingByLength;
import hu.csega.depi.showcase.patterns.strategy.impl.SortingByRedness;
import hu.csega.depi.showcase.patterns.strategy.impl.SortingStrategy;

public class ShowStrategyPattern extends ShowcaseWindow {

	protected ShowStrategyPattern() {
		super(TITLE);
	}

	@Override
	protected void init() {
		if(items == null) {
			items = new DataItem[50];

			for(int i = 0; i < items.length; i++) {
				if(items[i] == null)
					items[i] = new DataItem();

				items[i].length = rnd.nextInt(300) + 1;
				items[i].r = rnd.nextInt(256);
				items[i].g = rnd.nextInt(256);
				items[i].b = 255 - items[i].r;
			}
		}

		clear();
	}

	@Override
	protected void clear() {
		sorter = new DataSorter(items);
		sorter.setStrategy(selectedStrategy);
	}

	@Override
	protected void doOneRound() {
		synchronized (sorter) {
			sorter.switchFirstItemsInIncorrectOrder();
		}
	}

	@Override
	protected long waitingTime() {
		return 10l;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(inBox(e, 600, 20, 750, 100)) {
			synchronized (sorter) {
				selectedStrategy = lengthStrategy;
				sorter.setStrategy(selectedStrategy);
			}

			repaintCanvas();
		}

		if(inBox(e, 600, 120, 750, 200)) {
			synchronized (sorter) {
				selectedStrategy = brightnessStrategy;
				sorter.setStrategy(selectedStrategy);
			}

			repaintCanvas();
		}

		if(inBox(e, 600, 220, 750, 300)) {
			synchronized (sorter) {
				selectedStrategy = redStrategy;
				sorter.setStrategy(selectedStrategy);
			}

			repaintCanvas();
		}
	}

	@Override
	protected void paint2d(Graphics2D g) {
		int height = 550 / sorter.length();
		int y = 20;

		for(int i = 0; i < sorter.length(); i++) {
			DataItem item = sorter.get(i);
			g.setColor(Color.black);
			g.drawRect(10, y, 302, height);
			Color color = new Color(item.r, item.g, item.b);
			g.setColor(color);
			g.fillRect(11, y+1, item.length, height-2);
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
		return DesignPatternInfo.title(TITLE).resourceClass(this.getClass()).resourceName("strategy.png");
	}

	public static void main(String[] args) throws Exception {
		new ShowStrategyPattern();
	}

	private SortingStrategy lengthStrategy = new SortingByLength();
	private SortingStrategy brightnessStrategy = new SortingByBrightness();
	private SortingStrategy redStrategy = new SortingByRedness();

	private DataSorter sorter;
	private SortingStrategy selectedStrategy = lengthStrategy;

	private DataItem[] items;
	private Random rnd = new Random(System.currentTimeMillis());

	private static final String TITLE = "Strategy Pattern";

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
}
