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
	
	void testMakeThemSleep_AllBabiesShouldBeAsleep(){
		
		3.times { nurseryUnderTest.cradles[it].baby = new Baby(state: Baby.State.CRYING)}
		
		nurseryUnderTest.makeThemSleep()
		
		assertEquals 3, nurseryUnderTest.babies().size()
		nurseryUnderTest.babies().each  { assertEquals "A baby is not sleeping", Baby.State.ASLEEP, it.state }
	}
	
		
	
	void testGetUpEveryone_NoBabyShouldRemainAsleep(){
		
		def babies = [ new Baby(state: Baby.State.CRYING), new Baby(state: Baby.State.ASLEEP) ]
				
		babies.each { 
			nurseryUnderTest.welcome(it)
		}
		
		nurseryUnderTest.getUpEveryone();
		
		babies.each { assertNotSame "the baby is asleep !",it.state, Baby.State.ASLEEP }
	}
	
	void testRelease_TheBabyShouldNotRemainInTheNursery(){
		
		def baby = new Baby()
		nurseryUnderTest.cradles.add(new Cradle(baby: baby))
		
		nurseryUnderTest.release(baby)
		
		assertFalse "The baby should not be in the nursery anymore", 
			nurseryUnderTest.cradles.any{ it.baby == baby }		
	}
}
