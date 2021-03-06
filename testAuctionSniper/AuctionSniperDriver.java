package testAuctionSniper;

import auctionSniper.Main;
import static org.hamcrest.Matchers.equalTo;

import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.driver.JFrameDriver;
import com.objogate.wl.swing.driver.JTableDriver;
import com.objogate.wl.swing.gesture.GesturePerformer;
import com.objogate.wl.swing.matcher.IterableComponentsMatcher;

import static com.objogate.wl.swing.matcher.JLabelTextMatcher.withLabelText;

public class AuctionSniperDriver extends JFrameDriver {

	@SuppressWarnings("unchecked")
	public AuctionSniperDriver(int timeoutMillis) {
		super(new GesturePerformer(), JFrameDriver.topLevelFrame(
				named(Main.MAIN_WINDOW_NAME), showingOnScreen()),
				new AWTEventQueueProber(timeoutMillis, 100));
	}

	@SuppressWarnings("unchecked")
	public void showsSniperStatus(String itemId, int lastPrice, int lastBid,
			String statusText) {
		// new JTableDriver(this).hasCell(withLabelText(equalTo(statusText)));
		JTableDriver table = new JTableDriver(this);
		table.hasRow(IterableComponentsMatcher.matching(withLabelText(itemId),
				withLabelText(String.valueOf(lastPrice)),
				withLabelText(String.valueOf(lastBid)), withLabelText(statusText)));
	}
}
