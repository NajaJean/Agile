package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.NotifyObject;

public class NotifyObjectTest {

	@Test
	public void test() {
		NotifyObject N = new NotifyObject(17, "Notify-message");
		N.setNotifyCode(18);
		N.setNotifyMessage("NEW notify-message");
		
		assertEquals(18, N.getNotifyCode());
		assertEquals("NEW notify-message", N.getNotifyMessage());
	}

}
