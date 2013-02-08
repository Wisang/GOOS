package auctionSniper;

public class SniperSnapshot {
	public final String itemId;
	public final int lastPrice;
	public final int lastBid;
	public final SniperState status;

	public SniperSnapshot(String itemId, int lastPrice, int lastBid,
			SniperState status) {
		this.itemId = itemId;
		this.lastPrice = lastPrice;
		this.lastBid = lastBid;
		this.status = status;
	}

	@Override
	public String toString() {
		return itemId + "-" + lastPrice + "-" + lastBid;
	}

	@Override
	public boolean equals(Object other) {
		if (null == other)
			return false;

		return this.itemId.equals(((SniperSnapshot) other).itemId)
				&& this.lastBid == ((SniperSnapshot) other).lastBid
				&& this.lastPrice == ((SniperSnapshot) other).lastPrice;
	}

	public SniperSnapshot bidding(int newLastPrice, int newLastBid) {
		return new SniperSnapshot(itemId, newLastPrice, newLastBid,
				SniperState.BIDDING);
	}

	public SniperSnapshot winning(int newLastPrice) {
		return new SniperSnapshot(itemId, newLastPrice, lastBid,
				SniperState.WINNING);
	}

	public static SniperSnapshot joining(String itemId) {
		return new SniperSnapshot(itemId, 0, 0, SniperState.JOINING);
	}
}
