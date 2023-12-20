package main;

import java.util.ArrayList;

import iterator.FIFOIterator;
import iterator.Iterator;
import model.User;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
		ArrayList<User> userlist = new ArrayList<>();
		
		User us1 = new User("john");
		User us2 = new User("Jay");
		
		Iterator<User> iter = new FIFOIterator(userlist);
		
		while(iter.hasNext()) {
			System.out.println(iter.getNext().username);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
