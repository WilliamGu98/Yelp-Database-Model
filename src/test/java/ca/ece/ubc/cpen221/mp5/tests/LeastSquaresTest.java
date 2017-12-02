package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.*;

import java.io.*;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

public class LeastSquaresTest {

	@Test
	public void test1() throws IOException {
		YelpDB myDatabase = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
	//	System.out.println(myDatabase.getPredictorFunction("hhUQtt5Lksz5aZsvqa4Dbg")); throws an exception
	//	System.out.println(myDatabase.getPredictorFunction("_n9N41zBLY8uFLyTdynJ1A")); throws an exception
	//	System.out.println(myDatabase.getPredictorFunction("fL8ujZ89qTyhbjr1Qz5aSg")); does not throw exception
		//2m6a-NgqiTrIoxwe2YuEag user id should throw exception
		ToDoubleBiFunction myFunction = myDatabase.getPredictorFunction("fL8ujZ89qTyhbjr1Qz5aSg");
		double result = myFunction.applyAsDouble(myDatabase, "loBOs5ruFXSNL-ZM29cTrA"); //price is 2
		double result1 = myFunction.applyAsDouble(myDatabase, "fcdjnsgO8Z5LthXUx3y-lA"); //price is 2
		double result2 = myFunction.applyAsDouble(myDatabase, "7x0i0seshwLXBVAam16wuw"); //price is 2
		
		double result3 = myFunction.applyAsDouble(myDatabase, "1E2MQLWfwpsId185Fs2gWw"); //price is 1
		double result4 = myFunction.applyAsDouble(myDatabase, "gclB3ED6uk6viWlolSb_uA"); //price is 1
		double result5 = myFunction.applyAsDouble(myDatabase, "ZMqhKMjtdqVZLw11ja3ANg"); //price is 1

		double result6 = myFunction.applyAsDouble(myDatabase, "h_we4E3zofRTf4G0JTEF0A"); //price is 3
		double result7 = myFunction.applyAsDouble(myDatabase, "bDfcpkyVlSQUvA57HjDTKQ"); //price is 3
		double result8 = myFunction.applyAsDouble(myDatabase, "g5S6Q9W0IwN5AEK3fNWzbw"); //price is 3

		double result9 = myFunction.applyAsDouble(myDatabase, "HXni0_SFPT1jAoH-Sm78Jg"); //price is 4
		double result10 = myFunction.applyAsDouble(myDatabase, "6QZR4ToHKlse0yhqpU5ijg"); //price is 4
		double result11 = myFunction.applyAsDouble(myDatabase, "BiZAzf2SAFCE0gJrX-PGFw"); //price is 4


		System.out.println(result9);
		System.out.println(result10);
		System.out.println(result11);
	}
}
