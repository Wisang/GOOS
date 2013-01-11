package auctionSniper;

public class AuctionSniper implements AuctionEventListener{
	private final SniperListener sniperListener;
	private final Auction auction;
	
	public AuctionSniper(Auction auction, SniperListener sniperListener) {
		this.auction = auction;
		this.sniperListener = sniperListener;
	}

	public void auctionClosed() {
		sniperListener.sniperLost();
	}

	@Override
	public void currentPrice(int price, int increment, PriceSource priceSource) throws Exception {
		switch(priceSource) {
		case FromSniper:
			sniperListener.sniperWinning();
			break;
		case FromOtherBidder:
			auction.bid(price + increment);
			sniperListener.sniperBidding();
			break;
		default:
			throw new Exception("unknown bidder");
		}
	}

}
