package auctionSniper;

import javax.swing.SwingUtilities;
//import static auctionSniper.Main.ui;

public class SniperStateDisplayer implements SniperListener {
	
	public SniperStateDisplayer() throws Exception {
		
	}
	
	@Override
	public void sniperLost() {
		showStatus(MainWindow.STATUS_LOST);
	}

	@Override
	public void sniperBidding() {
		showStatus(MainWindow.STATUS_BIDDING);
	}
	
	private void showStatus(final String status) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { Main.ui.showStatus(status); }
		});		
	}

	public void sniperWinning() {
		showStatus(MainWindow.STATUS_WINNING);
	}

}
