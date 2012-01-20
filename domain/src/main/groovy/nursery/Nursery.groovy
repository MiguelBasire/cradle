package nursery

import nursery.Cradle

class Nursery {

	String name;	
	
	final List<Cradle> cradles = new ArrayList<Cradle>();
	
	Nursery(capacity){
		capacity.times { cradles.add(new Cradle()) }
	}
	
	Nursery(){}
	
	
	def makeThemSleep(){
		cradles.findAll { ! it.isEmpty() }.each { it.shake() }
	}
		
	def getUpEveryone(){
		cradles.findAll { ! it.isEmpty() }.each { it.shakeHarder() }
	}
	
	def welcome(Baby baby){
		cradles.find{ it.isEmpty() }?.putIn(baby)
	}
	
	def release(Baby baby){
		cradles.find{ it.baby == baby}.baby = null
		baby
	}
	
	List<Baby> babies(){
		List<Baby> babies = []
		cradles.each{ cradle -> if(cradle.baby!= null) { babies << cradle.baby} }
		babies
	}
	
}
