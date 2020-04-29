package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Content;
import core.DatabaseData;
import core.Environment;

public class ContentTest {
	Content[] Contents;
	public ContentTest() {
		this.Contents = DatabaseData.getContents();
	}

	@Test
	public void testContent() {
		Content con1 = Contents[0];
		assertEquals(1, con1.getContentID()); 
	}
	
	@Test
	public void testFindContent() {
		Content con1 = Contents[1];
		Content[] consEmpty = null;
		
		assertEquals(con1, Content.findContent(con1.getContentID(), Contents));
		assertEquals(con1, Content.findContent("Bananas", Contents));
		assertEquals(null, Content.findContent(con1.getContentID(), consEmpty));
		assertEquals(null, Content.findContent("Bananas", consEmpty));
		assertEquals(null, Content.findContent("Bana", Contents));
		assertEquals("'2', 'Bananas', '2', '0.05'", con1.toString());
		
		
	}
}
