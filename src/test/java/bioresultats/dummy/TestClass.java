package bioresultats.dummy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import bioresultats.DummyClass;
import bioresultats.DummyInterface;

public class TestClass {
	
	@Mock
	private DummyInterface dummyInterface;
	
	@InjectMocks
	private DummyClass dummyClass;
	
	//@Test
	public void TestWithError(){
		int expexted=1;
		int real=2;
		assertEquals(expexted, real);
	}
	
	@Test
	public void TestOk(){
		int expexted=1;
		int real=1;
		assertEquals(expexted, real);
	}
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

		
	@Test
	public void MockitoTestDummyfunction() {
		when(dummyInterface.dummyFunction()).thenReturn(20);
		 
		dummyClass.dummyFunction();
		
		assertEquals("OK",dummyClass.getWarningMessage());
	}

}
