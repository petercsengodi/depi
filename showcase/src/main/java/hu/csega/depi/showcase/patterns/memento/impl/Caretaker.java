package hu.csega.depi.showcase.patterns.memento.impl;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayDeque;
import java.util.Deque;

public class Caretaker {

	public Graphics getGraphics() {
		return originator.getGraphics();
	}

	public Image getCurrentImage() {
		return originator.getCurrentImage();
	}

	public void undo() {
		if(undoList.isEmpty() )
			return;

		redoList.push(originator.createMemento());
		Memento m = undoList.pop();
		originator.restoreFrom(m);
	}

	public void redo() {
		if(redoList.isEmpty())
			return;

		undoList.push(originator.createMemento());
		Memento m = redoList.pop();
		originator.restoreFrom(m);
	}

	public void save() {
		undoList.push(originator.createMemento());
		redoList.clear();
	}

	public int undoLength() {
		return undoList.size();
	}

	public int redoLength() {
		return redoList.size();
	}

	private Originator originator = new Originator();
	private Deque<Memento> undoList = new ArrayDeque<>();
	private Deque<Memento> redoList = new ArrayDeque<>();
}
