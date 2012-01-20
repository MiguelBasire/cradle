package nursery.service;

import nursery.Baby;
import nursery.Nursery;

public interface NurseryFinder {

	Nursery findNurseryWithMyBaby(Baby baby);
	Nursery findAvailableNursery();	
}
