package nursery;

import groovy.util.GroovyTestCase;

class NurseryTest extends GroovyTestCase {

	Nursery nurseryUnderTest;
	
	void setUp(){
		nurseryUnderTest = new Nursery(10);	
	}
	
	void testMakeThemSleep_EmptyCradleShouldNotHaveToBeShaked(){
		
		def emptyCradle = new Expando();
		emptyCradle.shake =  { fail "You should not shake an empty cradle" }
		emptyCradle.isEmpty = { true }
		nurseryUnderTest.cradles.add(emptyCradle);
		
		nurseryUnderTest.makeThemSleep();
	}
		
	
	void testGetUpEveryone_NoBabyShouldRemainAsleep(){
		
		def babies = [ new Baby(state: Baby.State.CRYING), new Baby(state: Baby.State.ASLEEP) ]
				
		babies.each { 
			nurseryUnderTest.welcome(it)
		}
		
		nurseryUnderTest.getUpEveryone();
		
		babies.each { assertNotSame "the baby is asleep !",it.state, Baby.State.ASLEEP }
	}
	
	void testRelease_TheBabySouldNotRemainInTheNursery(){
		
		def baby = new Baby()
		nurseryUnderTest.cradles.add(new Cradle(baby: baby))
		
		nurseryUnderTest.release(baby)
		
		assertFalse "The baby should not be in the nursery anymore", 
			nurseryUnderTest.cradles.any{ it.baby == baby }		
	}
}
