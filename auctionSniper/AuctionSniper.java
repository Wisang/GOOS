package auctionSniper;


public class AuctionSniper implements AuctionEventListener{
	private final SniperListener sniperListener;
	private final Auction auction;
	private boolean isWinning = false;
	private String itemId;
	
	public AuctionSniper(String itemId, Auction auction, SniperListener sniperListener) {
		this.auction = auction;
		this.sniperListener = sniperListener;
		this.itemId = itemId;
	}
	
	public String getItemId() {
		return itemId;
	}

	public void auctionClosed() {
		if(isWinning)
			sniperListener.sniperWon();
		else
			sniperListener.sniperLost();
	}

	@Override
	public void currentPrice(int price, int increment, PriceSource priceSource) {
		isWinning = priceSource == PriceSource.FromSniper;
	    if (isWinning) {
	    	sniperListener.sniperWinning();
	    } else {
	      int bid = price + increment;
	      auction.bid(bid);
	      sniperListener.sniperBidding(new SniperSnapshot(itemId, price, bid, SniperState.BIDDING));
	    }
	}
}
