package goosExperiment;

public class Greeting {
	private GreetingTime gt;
	public void setGreetingTime(GreetingTime gt) {
		this.gt = gt;
	}
	
	public String sayGreeting(String name) {
		String greeting = gt.getGreeting();
		return greeting + ", " + name;
	}
}
