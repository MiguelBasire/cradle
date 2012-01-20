package nursery;

import groovy.util.GroovyTestCase;

class NurseryRepositotyTest extends GroovyTestCase {

	NurseryRepository nurseryRepositoryUnderTest;

	void setUp(){
		nurseryRepositoryUnderTest = new NurseryRepository()
	}


	void testAdd(){

		Nursery nursery = new Nursery(10)
		nursery.name = 'le bazar'
		nursery.welcome(new Baby(name: 'toto'))

		println nurseryRepositoryUnderTest.add(nursery)
	}

	void testStore(){

		Nursery nursery = new Nursery(10)
		nursery.name = 'le bazar'
		nursery.welcome(new Baby(name: 'toto'))

		nurseryRepositoryUnderTest.add(nursery)

		def writer = new StringWriter()
		nurseryRepositoryUnderTest.store(writer)
		println writer.toString()

	}

	void testRestore(){

		def xmlData = '''
			<nurseries xmlns='http://soat.fr/nursery'>
  <nursery name='le bazar'>
    <cradles>
      <cradle>
        <baby name='toto' />
      </cradle>
      <cradle />
      <cradle />
      <cradle />
      <cradle />
      <cradle />
      <cradle>
        <baby />
      </cradle>
      <cradle />
      <cradle />
      <cradle />
    </cradles>
  </nursery>
</nurseries>
		'''

		nurseryRepositoryUnderTest.restore(xmlData);

		assertEquals 1,nurseryRepositoryUnderTest.nurseries.size()
		assertEquals 'le bazar',nurseryRepositoryUnderTest.nurseries[0].name
		assertEquals 10,nurseryRepositoryUnderTest.nurseries[0].cradles.size()
		assertEquals 'toto',nurseryRepositoryUnderTest.nurseries[0].cradles[0].baby.name
		assertEquals null,nurseryRepositoryUnderTest.nurseries[0].cradles[6].baby.name
	}

}
