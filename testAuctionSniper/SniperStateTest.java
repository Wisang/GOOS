package testAuctionSniper;

import static org.junit.Assert.*;

import org.junit.Test;

import auctionSniper.SniperState;

public class SniperStateTest {
	@Test
	public void SniperStateToStringTest() throws Exception {
		SniperState sniperState = new SniperState("abc", 1, 2);
		assertEquals("abc-1-2", sniperState.toString());
	}
	
	@Test
	public void equalsOverride() throws Exception {
		SniperState sniperState1 = new SniperState("abc", 1, 2);
		SniperState sniperState2 = new SniperState("abc", 1, 2);
		assertEquals(sniperState1, sniperState2);
	}
}
