package auctionSniper;

//public class SniperState {
//	public final String itemId;
//	public final int lastPrice;
//	public final int lastBid;
//	
//	public SniperState(String itemId, int lastPrice, int lastBid) {
//		this.itemId = itemId;
//		this.lastPrice = lastPrice;
//		this.lastBid = lastBid;
//	}
//}

public class SniperState {
	public final String itemId;
	public final int lastPrice;
	public final int lastBid;

	public SniperState(String itemId, int lastPrice, int lastBid) {
		this.itemId = itemId;
		this.lastPrice = lastPrice;
		this.lastBid = lastBid;
	}
	
	@Override
	public String toString() {
		return itemId + "-" + lastPrice + "-" + lastBid;
	}
	
	@Override
	public boolean equals(Object other) {
		if(null == other)
			return false;
		
		return this.itemId.equals(((SniperState)other).itemId)
				&& this.lastBid == ((SniperState)other).lastBid
				&& this.lastPrice == ((SniperState)other).lastPrice;
	}
}
