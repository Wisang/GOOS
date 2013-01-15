package testAuctionSniper;

import org.jmock.Expectations;
import org.jmock.States;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import auctionSniper.Auction;
import auctionSniper.AuctionSniper;
import auctionSniper.SniperListener;
import auctionSniper.SniperState;

import static auctionSniper.AuctionEventListener.PriceSource;

@RunWith(JMock.class)
public class AuctionSniperTest {
	private final Mockery context = new Mockery();
	private final SniperListener sniperListener = context
			.mock(SniperListener.class);
	private Auction auction = context.mock(Auction.class);

	final String ITEM_ID = "item-54321"; //wseom
	private final AuctionSniper sniper = new AuctionSniper(ITEM_ID, auction,
			sniperListener); //wseom
	private final States sniperState = context.states("sniper");
	
	@Test 
	public void reportsWonIfAuctionClosesWhenWinning() {
	  context.checking(new Expectations() {{
	    ignoring(auction);
	    allowing(sniperListener).sniperWinning();  then(sniperState.is("winning"));

	    atLeast(1).of(sniperListener).sniperWon(); when(sniperState.is("winning"));
	  }});
	  sniper.currentPrice(123, 45, PriceSource.FromSniper);
	  sniper.auctionClosed();
	}
	
	@Test 
	public void reportsLostIfAuctionClosesWhenBidding() {
	    context.checking(new Expectations() {{
	      ignoring(auction); 
	      allowing(sniperListener).sniperBidding(with(any(SniperState.class))); //wseom
	                              then(sniperState.is("bidding")); 

	      atLeast(1).of(sniperListener).sniperLost();
	                              when(sniperState.is("bidding")); 
	    }});

	    sniper.currentPrice(123, 45, PriceSource.FromOtherBidder); 
	    sniper.auctionClosed();
	  }

	@Test
	public void reportsIsWinningWhenCurrentPriceComesFromSniper() {
		context.checking(new Expectations() {
			{
				atLeast(1).of(sniperListener).sniperWinning();
			}
		});

		sniper.currentPrice(123, 45, PriceSource.FromSniper);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void bidsHigherAndReportBiddingWhenNewPriceArrives()
			throws Exception {
		final int price = 1001;
		final int increment = 25;
		final int bid = price + increment;
		System.out.println(sniper.getItemId());
		
		context.checking(new Expectations() {
			{
				one(auction).bid(bid);
				atLeast(1).of(sniperListener).sniperBidding(new SniperState(sniper.getItemId(), price, bid));
			}
		});

		sniper.currentPrice(price, increment, PriceSource.FromOtherBidder );
	}

	@SuppressWarnings("deprecation")
	@Test
	public void reportLostWhenAuctionClosesImmediately() throws Exception {
		context.checking(new Expectations() {
			{
				one(sniperListener).sniperLost();
			}
		});
		sniper.auctionClosed();
	}
}
