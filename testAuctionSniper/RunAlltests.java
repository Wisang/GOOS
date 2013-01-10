package testAuctionSniper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({AuctionSniperEndToEndTest.class, AuctionMessageTranslatorTest.class, AuctionSniperTest.class})
public class RunAlltests {

}
