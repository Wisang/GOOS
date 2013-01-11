package testAuctionSniper;

import org.jmock.Expectations;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import auctionSniper.Auction;
import auctionSniper.AuctionSniper;
import auctionSniper.SniperListener;

import static auctionSniper.AuctionEventListener.PriceSource;

@RunWith(JMock.class)
public class AuctionSniperTest {
	private final Mockery context = new Mockery();
	private final SniperListener sniperListener = context
			.mock(SniperListener.class);
	private Auction auction = context.mock(Auction.class);

	private final AuctionSniper sniper = new AuctionSniper(auction,
			sniperListener);

	@Test
	public void reportsIsWinningWhenCurrentPriceComesFromSniper() throws Exception {
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
		context.checking(new Expectations() {
			{
				one(auction).bid(price + increment);
				atLeast(1).of(sniperListener).sniperBidding();
			}
		});

		sniper.currentPrice(price, increment, PriceSource.FromOtherBidder ); //wseom
	}

	@SuppressWarnings("deprecation")
	@Test
	public void reportLostWhenAuctionCloses() throws Exception {
		context.checking(new Expectations() {
			{
				one(sniperListener).sniperLost();
			}
		});

		sniper.auctionClosed();
	}
}
