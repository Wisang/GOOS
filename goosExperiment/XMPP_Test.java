package goosExperiment;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testAuctionSniper.ApplicationRunner;
import testAuctionSniper.FakeAuctionServer;

import auctionSniper.Main;
import auctionSniper.SingleMessageListener;

public class XMPP_Test {
	private XMPPConnection connection = null;
	private String itemId = "item-54321";
	protected Chat currentChat;
	private MessageListener messageListener = new SingleMessageListener();
	
	public static final String ITEM_ID_AS_LOGIN = "auction-%s";
	public static final String AUCTION_RESOURCE = "Auction";
	public static final String XMPP_HOSTNAME = "localhost";
	private static final String AUCTION_PASSWORD = "auction";
	
	private final ApplicationRunner application = new ApplicationRunner();
	private final FakeAuctionServer auction = new FakeAuctionServer(
			"item-54321");
	
	@Before
	public void setup() throws XMPPException
	{
		connection = new XMPPConnection("localhost");
		connection.connect();
		
	}
	
	@Test
	public void justLogin() throws Exception {
		connection.login(format(ITEM_ID_AS_LOGIN, itemId ), AUCTION_PASSWORD,
				AUCTION_RESOURCE);
		connection.getChatManager().addChatListener(new ChatManagerListener() {
			public void chatCreated(Chat chat, boolean createdLocally) {
				currentChat = chat;
				chat.addMessageListener(messageListener);
			}
		});
		
		application.startBiddingIn(auction); 
		
		((SingleMessageListener) messageListener).receivesAMessage(equalTo(Main.JOIN_COMMAND_FORMAT));
		
		System.out.println(connection.getHost());
		System.out.println(connection.getUser());
		currentChat.sendMessage("Hello");
		Message message = new Message();
		messageListener.processMessage(currentChat, message);
		System.out.println(((SingleMessageListener) messageListener).getMessage());
	}
	
	@After
	public void teardown() {
		connection.disconnect();
	}
}
