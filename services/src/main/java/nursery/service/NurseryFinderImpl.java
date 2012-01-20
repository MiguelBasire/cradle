package nursery.service;

import nursery.Baby;
import nursery.Nursery;
import nursery.NurseryRepository;

public class NurseryFinderImpl implements NurseryFinder {

	private NurseryRepository repository;

	public NurseryFinderImpl(NurseryRepository repository) {
		this.repository = repository;
	}



	public Nursery findNurseryWithMyBaby(Baby baby) {

		for(Nursery nursery:repository.list()){

			if(nursery.babies().contains(baby)){
				return nursery;
			}
		}
		return null;
	}

	public Nursery findAvailableNursery() {

		for(Nursery nursery: repository.list()){

			if(nursery.babies().size() < nursery.getCradles().size()){
				return nursery;
			}
		}
		return null;
	}

}
