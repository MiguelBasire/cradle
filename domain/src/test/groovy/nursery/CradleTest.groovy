package nursery;

import nursery.exception.TwoManyBabiesException;
import groovy.util.GroovyTestCase;

class CradleTest extends GroovyTestCase {
	
	Cradle cradleUnderTest;
	
	void setUp(){
		cradleUnderTest = new Cradle();
	}
	
	void testPutIn_shouldNotPutTwoBabiesInTheCradle(){
		
		cradleUnderTest.putIn(new Baby())
		
		shouldFail(TwoManyBabiesException) {
			cradleUnderTest.putIn(new Baby())
		}		
	}
	
	
	
}
