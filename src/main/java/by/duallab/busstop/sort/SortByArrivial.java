package by.duallab.busstop.sort;

import java.util.Comparator;

import by.duallab.busstop.bus.Bus;

public class SortByArrivial implements Comparator<Bus> {

	public int compare(Bus obj1, Bus obj2) {
		return obj1.getEnd().compareTo(obj2.getEnd());
	}

}
