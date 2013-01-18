package testAuctionSniper;

import static org.junit.Assert.*;

import org.junit.Test;

import auctionSniper.SniperSnapshot;
import auctionSniper.SniperState;

public class SniperStateTest {
	@Test
	public void SniperStateToStringTest() throws Exception {
		SniperSnapshot sniperState = new SniperSnapshot("abc", 1, 2, SniperState.BIDDING);
		assertEquals("abc-1-2", sniperState.toString());
	}
	
	@Test
	public void equalsOverride() throws Exception {
		SniperSnapshot sniperState1 = new SniperSnapshot("abc", 1, 2, SniperState.BIDDING);
		SniperSnapshot sniperState2 = new SniperSnapshot("abc", 1, 2, SniperState.BIDDING);
		assertEquals(sniperState1, sniperState2);
	}
}
