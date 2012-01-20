package nursery

import nursery.exception.TwoManyBabiesException

class Cradle {

	Baby baby;	
	
	def shake(){
		baby?.sleep();
	}
	
	def shakeHarder(){
		baby?.wakeUp();
	}
	
	def free(){
		baby = null;
	}
	
	boolean isEmpty() { baby == null }
	
	def putIn(Baby newBaby){ 
		if(baby!=null) { throw new TwoManyBabiesException(rejectedBaby: newBaby) }
		this.baby = newBaby 
	}
}
