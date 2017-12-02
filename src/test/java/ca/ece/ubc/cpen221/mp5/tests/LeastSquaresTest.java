package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.*;

import java.io.*;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

public class LeastSquaresTest {

	@Test
	public void equalityTest1() throws IOException {
		double delta = 0.0001;
		YelpDB myDatabase = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
		ToDoubleBiFunction myFunction = myDatabase.getPredictorFunction("fL8ujZ89qTyhbjr1Qz5aSg");
		
		double result0 = myFunction.applyAsDouble(myDatabase, "1E2MQLWfwpsId185Fs2gWw"); //price is 1
		double result1 = myFunction.applyAsDouble(myDatabase, "gclB3ED6uk6viWlolSb_uA"); //price is 1
		double result2 = myFunction.applyAsDouble(myDatabase, "ZMqhKMjtdqVZLw11ja3ANg"); //price is 1
		assertEquals(result0, result1, delta);
		assertEquals(result1, result2, delta);
		assertEquals(result2, result0, delta);
		
		double result3 = myFunction.applyAsDouble(myDatabase, "loBOs5ruFXSNL-ZM29cTrA"); //price is 2
		double result4 = myFunction.applyAsDouble(myDatabase, "fcdjnsgO8Z5LthXUx3y-lA"); //price is 2
		double result5 = myFunction.applyAsDouble(myDatabase, "7x0i0seshwLXBVAam16wuw"); //price is 2
		assertEquals(result3, result4, delta);
		assertEquals(result4, result5, delta);
		assertEquals(result5, result3, delta);
		
		double result6 = myFunction.applyAsDouble(myDatabase, "h_we4E3zofRTf4G0JTEF0A"); //price is 3
		double result7 = myFunction.applyAsDouble(myDatabase, "bDfcpkyVlSQUvA57HjDTKQ"); //price is 3
		double result8 = myFunction.applyAsDouble(myDatabase, "g5S6Q9W0IwN5AEK3fNWzbw"); //price is 3
		assertEquals(result6, result7, delta);
		assertEquals(result7, result8, delta);
		assertEquals(result8, result6, delta);
		
		double result9 = myFunction.applyAsDouble(myDatabase, "HXni0_SFPT1jAoH-Sm78Jg"); //price is 4
		double result10 = myFunction.applyAsDouble(myDatabase, "6QZR4ToHKlse0yhqpU5ijg"); //price is 4
		double result11 = myFunction.applyAsDouble(myDatabase, "BiZAzf2SAFCE0gJrX-PGFw"); //price is 4
		assertEquals(result9, result10, delta);
		assertEquals(result10, result11, delta);
		assertEquals(result11, result9, delta);
	}
	
	@Test
	public void equalityTest2() throws IOException {
		double delta = 0.0001;
		YelpDB myDatabase = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
		ToDoubleBiFunction myFunction = myDatabase.getPredictorFunction("Rto4xWr5gXA2IbrfyAn-Xg");
		
		double result0 = myFunction.applyAsDouble(myDatabase, "1E2MQLWfwpsId185Fs2gWw"); //price is 1
		double result1 = myFunction.applyAsDouble(myDatabase, "gclB3ED6uk6viWlolSb_uA"); //price is 1
		double result2 = myFunction.applyAsDouble(myDatabase, "ZMqhKMjtdqVZLw11ja3ANg"); //price is 1
		assertEquals(result0, result1, delta);
		assertEquals(result1, result2, delta);
		assertEquals(result2, result0, delta);
		
		double result3 = myFunction.applyAsDouble(myDatabase, "loBOs5ruFXSNL-ZM29cTrA"); //price is 2
		double result4 = myFunction.applyAsDouble(myDatabase, "fcdjnsgO8Z5LthXUx3y-lA"); //price is 2
		double result5 = myFunction.applyAsDouble(myDatabase, "7x0i0seshwLXBVAam16wuw"); //price is 2
		assertEquals(result3, result4, delta);
		assertEquals(result4, result5, delta);
		assertEquals(result5, result3, delta);
		
		double result6 = myFunction.applyAsDouble(myDatabase, "h_we4E3zofRTf4G0JTEF0A"); //price is 3
		double result7 = myFunction.applyAsDouble(myDatabase, "bDfcpkyVlSQUvA57HjDTKQ"); //price is 3
		double result8 = myFunction.applyAsDouble(myDatabase, "g5S6Q9W0IwN5AEK3fNWzbw"); //price is 3
		assertEquals(result6, result7, delta);
		assertEquals(result7, result8, delta);
		assertEquals(result8, result6, delta);
		
		double result9 = myFunction.applyAsDouble(myDatabase, "HXni0_SFPT1jAoH-Sm78Jg"); //price is 4
		double result10 = myFunction.applyAsDouble(myDatabase, "6QZR4ToHKlse0yhqpU5ijg"); //price is 4
		double result11 = myFunction.applyAsDouble(myDatabase, "BiZAzf2SAFCE0gJrX-PGFw"); //price is 4
		assertEquals(result9, result10, delta);
		assertEquals(result10, result11, delta);
		assertEquals(result11, result9, delta);
	}
	
	@Test
	public void userThrowsExceptionTest1() throws IOException {
		double delta = 0.0001;
		YelpDB myDatabase = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		try { 
			myDatabase.getPredictorFunction("hhUQtt5Lksz5aZsvqa4Dbg"); 
			fail("should have seen an exception");
		} catch (IllegalArgumentException e) {
		}
	}
	@Test
	public void userThrowsExceptionTest2() throws IOException {
		double delta = 0.0001;
		YelpDB myDatabase = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		try { 
			myDatabase.getPredictorFunction("_n9N41zBLY8uFLyTdynJ1A"); 
			fail("should have seen an exception");
		} catch (IllegalArgumentException e) {
		}
	}
	@Test
	public void userThrowsExceptionTest3() throws IOException {
		double delta = 0.0001;
		YelpDB myDatabase = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		try { 
			myDatabase.getPredictorFunction("2m6a-NgqiTrIoxwe2YuEag"); 
			fail("should have seen an exception");
		} catch (IllegalArgumentException e) {
		}
	}
}
