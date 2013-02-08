package goosExperiment;

import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.Matchers.not;

public class HamcrestTest {
	@Test
	public void matchersTest() throws Exception {
		String s = "yes we have no bananas today";

		Matcher<String> containsBananas = new StringContains("bananas");
		Matcher<String> containsMangoes = new StringContains("mangoes");
		Matcher<String> containsToday = new StringContains("today");

		assertTrue(containsBananas.matches(s));
		assertFalse(containsMangoes.matches(s));
		assertTrue(containsToday.matches(s));
		
		assertTrue(containsString("bananas").matches(s));
		assertFalse(containsString("mangoes").matches(s));
		
		assertThat(s, containsString("bananas"));
		assertThat(s, not(containsString("mangoes")));
	}
}
