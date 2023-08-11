package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IntegerChecker extends TypeSafeMatcher<String>{

	@Override
	public void describeTo(Description description) {
		description.appendText("expected to be Integer but got: ");
		
	}

	@Override
	protected boolean matchesSafely(String item) {
		try {
			Integer.parseInt(item);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static Matcher<String> integerOnly(){
		return new IntegerChecker();
	}

}
