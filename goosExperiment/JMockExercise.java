package goosExperiment;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class JMockExercise {
	Mockery context = new JUnit4Mockery();
	@Test
	public void testSayGreeting() throws Exception {
		final GreetingTime gt = context.mock(GreetingTime.class); 
		Greeting g = new Greeting();
		g.setGreetingTime(gt);
		
		context.checking(new Expectations() {{
			one(gt).getGreeting(); will(returnValue("Good Afternoon"));
		}});
		
//		GreetingTime gt = new GreetingTime();
//		o.setGreetingTime(gt);
		
		String out = g.sayGreeting("Eom");
		assertEquals("Good Afternoon, Eom", out);
	}
}
