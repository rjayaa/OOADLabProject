package iterator;

import java.util.ArrayList;

import model.User;

public class FIFOIterator implements Iterator {

	private ArrayList<User> list;
	private int currIndex;
	public FIFOIterator(ArrayList<User> list) {
		this.list = list;
		// FIFO
		currIndex = 0;
		// LIFO
//		currIndex = list.size() - 1;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return currIndex < list.size(); // FIFO
//		return currIndex >= 0; //LIFO
	}

	@Override
	public Object getNext() {
		// TODO Auto-generated method stub
		// FIFO
		
			return list.get(currIndex ++);
		
		
//		return null;
		
		// LIFO
//		return list.get(currIndex --);
	}

}
