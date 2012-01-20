package nursery.service;

import java.util.Arrays;

import nursery.Baby;
import nursery.Nursery;
import nursery.NurseryRepository;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class NurseryFinderTest {

	NurseryFinder serviceUnderTest;
	
	NurseryRepository mockedRepository;
	
	@Before
	public void initServiceUnderTest(){
		mockedRepository = mock(NurseryRepository.class);
		serviceUnderTest = new NurseryFinderImpl(mockedRepository);
	}
	
	@Test
	public void shouldReturnTheNurseryContainingTheBaby(){
		
		Nursery[] nurseries = new Nursery[] { new Nursery(2), new Nursery(1), new Nursery(0) };
		when(mockedRepository.list()).thenReturn(Arrays.asList(nurseries));
				
		Baby lostBaby = new Baby();
		lostBaby.setName("charlie");
		nurseries[1].welcome(lostBaby);
	
		Nursery foundNursery = serviceUnderTest.findNurseryWithMyBaby(lostBaby);
		
		assertEquals(nurseries[1], foundNursery);
	}
	
	@Test
	public void shouldNotFindTheNursery(){
		
		Nursery[] nurseries = new Nursery[] { new Nursery(2)};
		when(mockedRepository.list()).thenReturn(Arrays.asList(nurseries));
		
		Baby lostBaby = new Baby();
		lostBaby.setName("charlie");
		
		Nursery foundNursery = serviceUnderTest.findNurseryWithMyBaby(lostBaby);
		
		assertNull(foundNursery);
	}
	
	@Test
	public void shouldReturnAnAvailableNursery(){
		
		Nursery[] nurseries = new Nursery[] { new Nursery(2), new Nursery(1)};
		when(mockedRepository.list()).thenReturn(Arrays.asList(nurseries));
		
		nurseries[0].welcome(new Baby()); // remains 1 cradle
		nurseries[1].welcome(new Baby());
		
		Nursery foundNursery = serviceUnderTest.findAvailableNursery();
		
		assertNotNull(foundNursery);
		assertEquals(nurseries[0],foundNursery);
	}
	
	
	
}
