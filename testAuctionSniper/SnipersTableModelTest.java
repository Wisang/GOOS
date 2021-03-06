package testAuctionSniper;

import static org.junit.Assert.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import auctionSniper.Column;
import auctionSniper.MainWindow;
import auctionSniper.SniperSnapshot;
import auctionSniper.SniperState;
import auctionSniper.SnipersTableModel;

@RunWith(JMock.class)
public class SnipersTableModelTest {
	private final Mockery context = new Mockery();
	private TableModelListener listener = context
			.mock(TableModelListener.class);
	private final SnipersTableModel model = new SnipersTableModel();
	
	@Before
	public void attachModelListener() {
		model.addTableModelListener(listener);
	}
	
	@Test
	public void hasEnoughColumns() throws Exception {
		assertThat(model.getColumnCount(), equalTo(Column.values().length));
	}
	
	@Test
	public void setsSniperValuesInColumns() throws Exception {
		context.checking(new Expectations() {{
		      one(listener).tableChanged(with(aRowChangedEvent())); 
		    }});

		    model.sniperStatusChanged(new SniperSnapshot("item id", 555, 666, SniperState.BIDDING), 
		                              MainWindow.STATUS_BIDDING);

		    assertColumnEquals(Column.ITEM_IDENTIFIER, "item id"); 
		    assertColumnEquals(Column.LAST_PRICE, 555);
		    assertColumnEquals(Column.LAST_BID, 666);
		    assertColumnEquals(Column.SNIPER_STATE, MainWindow.STATUS_BIDDING);
	}

	private Matcher<TableModelEvent> aRowChangedEvent() {
		return samePropertyValuesAs(new TableModelEvent(model,0));
	}

	private void assertColumnEquals(Column column, Object expected) {
		final int rowIndex = 0;
		final int columnIndex = column.ordinal();
		assertEquals(expected, model.getValueAt(rowIndex, columnIndex));
	}
}
