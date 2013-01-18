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

	private void showStatus(final String status) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main.ui.showStatusText(status);
			}
		});
	}

	@Override
	public void sniperWinning() {
		showStatus(MainWindow.STATUS_WINNING);
	}

	@Override
	public void sniperWon() {
		showStatus(MainWindow.STATUS_WON);
	}

	@Override
	public void sniperBidding(final SniperState state) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main.ui.sniperStatusChanged(state, MainWindow.STATUS_BIDDING);
			}
		});
	}
}
