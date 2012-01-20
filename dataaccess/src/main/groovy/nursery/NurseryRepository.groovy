package nursery


import groovy.xml.MarkupBuilder
import java.util.Collections

class NurseryRepository {
	
	def nurseryNS = 'http://soat.fr/nursery'

	private final List<Nursery> nurseries = new ArrayList<Nursery>()
		
	def add(Nursery nursery){
		nurseries.add(nursery)
	}

	Nursery findNurseryNamed(String name){
		nurseries.find{ nursery -> nursery.name == name }
	}
	
	List<Nursery> list() {
		return Collections.unmodifiableList(nurseries)
	}
	

	def store(Writer writer){
		
		def xml = new MarkupBuilder()

		xml.nurseries(xmlns: nurseryNS) {
			nurseries.each { nrsr ->
				nursery(name: nrsr.name){
					cradles {
						nrsr.cradles.each { crdl ->
							cradle {
								if(crdl.baby != null){ baby(name: crdl.baby.name) }
							}
						}
					}
				}
			}
		}
	}

	def restore(data){
		
		def ns = new groovy.xml.Namespace(nurseryNS)
		def nurseriesRoot = new groovy.util.XmlParser().parseText(data)
		nurseriesRoot[ns.nursery].each {
			
			Nursery nursery = new Nursery()
			nursery.name =  it.@name
			it[ns.cradles][ns.cradle].each { cradleNode ->
				def cradle = new Cradle()
				cradle.baby = cradleNode[ns.baby][0] != null ? new Baby(name: cradleNode[ns.baby][0].@name) : null 
				nursery.cradles.add(cradle)
			}
			nurseries.add(nursery)
		}
				
	}


}
