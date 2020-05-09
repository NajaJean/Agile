package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.ArraySearch;
import core.Content;
import core.DatabaseData;
import core.Environment;

public class ContentTest {
	
	Content[] Contents = DatabaseData.getContents();
	ArraySearch search;

	@Before 
	public void setUp() {
		search = new ArraySearch(new Content());
		Content.resetCount();
	}

	@Test
	public void testContent() {
		Content con1 = Contents[0];
		assertEquals(1, con1.getContentID()); 
	}
	
	@Test
	public void testFindContent() {
		Content con1 = Contents[1];
		Content[] emptyContents = new Content[0];
		
		assertEquals(con1, Contents[search.findIDX(con1.getContentID(), Contents)]);
		assertEquals(con1, Contents[search.findIDX("Bananas", Contents)]);
		
		assertEquals(-1, search.findIDX(con1.getContentID(), emptyContents));
		assertEquals(-1, search.findIDX("Bananas", emptyContents));
		assertEquals(-1, search.findIDX("Bana", Contents));
			
		String expected = "'2', 'Bananas', '2', '0.22'";
		assertEquals(expected, con1.toString());
	}
}
