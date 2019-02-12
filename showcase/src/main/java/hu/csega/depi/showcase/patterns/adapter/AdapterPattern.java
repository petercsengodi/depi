package hu.csega.depi.showcase.patterns.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of applying the Adapter pattern here is that we would like to use
 * an already fully implemented List object as a Stack (an interface we identified).
 */
public class AdapterPattern {

	public interface Stack {
		void push(String string);
		String pop();
	}

	public static class ListToStackAdapter implements Stack {

		public ListToStackAdapter(List<String> list) {
			this.list = list;
		}

		@Override
		public void push(String string) {
			list.add(string);
		}

		@Override
		public String pop() {
			if(list.isEmpty())
				return null;
			else
				return list.remove(list.size() - 1);
		}

		private List<String> list;
	}

	public static void main(String[] args) throws Exception {
		List<String> list = new ArrayList<>();
		Stack stack = new ListToStackAdapter(list);

		stack.push("Hello World!");
		System.out.println(stack.pop());
	}

}
