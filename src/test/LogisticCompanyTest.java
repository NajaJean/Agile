package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.LogisticCompany;

public class LogisticCompanyTest {

	@Test
	public void test() {
		LogisticCompany L = new LogisticCompany();
		
		assertEquals("LongTimeNoSea", L.getName());
		assertEquals(1, L.getID());
	}

}
