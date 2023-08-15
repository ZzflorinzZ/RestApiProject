package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CargoCapacityCheck extends TypeSafeMatcher<String>{

	@Override
	public void describeTo(Description description) {
		description.appendText("Cargo capacity is less than: ");
		
	}

	@Override
	protected boolean matchesSafely(String item) {
		try {
			if(Integer.parseInt(item) > 25000000) {	
				return true;
			} else {
				return false;
			}
		} catch(NumberFormatException e) {
			return false;
		}		
	}
	
	public static Matcher<String> hasCargoCapacity() {
		return new CargoCapacityCheck();
	}

}
