package auctionSniper;

import javax.swing.table.AbstractTableModel;

public class SnipersTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private SniperSnapshot STARTING_UP = new SniperSnapshot("", 0, 0, SniperState.JOINING); // to be cor
	private String statusText = MainWindow.STATUS_JOINING;
	private SniperSnapshot sniperState = STARTING_UP;

	private String state;
	private static String[] STATUS_TEXT = { MainWindow.STATUS_JOINING,
			MainWindow.STATUS_BIDDING, MainWindow.STATUS_WINNING };

	@Override
	public int getRowCount() {
		return 1;
	}

	@Override
	public int getColumnCount() {
		return Column.values().length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (Column.at(columnIndex)) {
		case ITEM_IDENTIFIER:
			return sniperState.itemId;
		case LAST_PRICE:
			return sniperState.lastPrice;
		case LAST_BID:
			return sniperState.lastBid;
		case SNIPER_STATE:
			return statusText;
		default:
			throw new IllegalArgumentException("No column at " + columnIndex);
		}
	}

	public void setStatusText(String newStatusText) {
		statusText = newStatusText;
		fireTableRowsUpdated(0, 0);
	}

	public void sniperStatusChanged(SniperSnapshot newSniperState, 
            String newStatusText) {
		sniperState = newSniperState;
	    statusText = newStatusText;
	    fireTableRowsUpdated(0, 0);
	}

//	@Override
//	public Object getValueAt(int rowIndex, int columnIndex) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
