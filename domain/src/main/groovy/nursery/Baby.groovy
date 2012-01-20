package nursery

import java.lang.Thread.State

class Baby {

	enum State {ASLEEP,CRYING,HUNGRY}
	
	static Baby NONE = new Baby()
	
	State state = State.CRYING
	String name
	Date dateOfBirth
	
	def age(){
		(int) new Date().minus(dateOfBirth) / 365;
	}
	
	def wakeUp(){ state = State.HUNGRY }
	def sleep(){ state = State.ASLEEP }
	def eat(def somefood){ state = State.CRYING }
}
