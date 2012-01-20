package nursery;

import groovy.util.GroovyTestCase;

class BabyTest extends GroovyTestCase {
	
	def Baby babyUnderTest;
	
	void setUp(){
		babyUnderTest = new Baby()
	}
	
	void testAge(){
		
		babyUnderTest.dateOfBirth = (new Date()) - (365*2+50) // un peu plus de 2 ans quoi		
		assertEquals "The baby should be 2 years old", 2, babyUnderTest.age() 
	}
	
	

}
