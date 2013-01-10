package goosExperiment;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.Matchers.containsString;

public class HamcrestExercise {
	private String s = " I have a banana";

	@Test
	public void hamcrestTest() throws Exception {
		assertThat(s , not(containsString("mango")));
	}	
}
