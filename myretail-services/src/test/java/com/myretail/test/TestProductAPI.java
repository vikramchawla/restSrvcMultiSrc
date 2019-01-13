package com.myretail.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.myretail.ProductAPI;

@RunWith(value = Parameterized.class)
public class TestProductAPI {

	
	
	
	

  //  ProductAPI pa= new ProductAPI(); 

	ArrayList<Integer> al = new ArrayList<Integer>();
	
	
	
	@Test
	@Parameters

	public static void getProductInfo(String id) throws JSONException
	{
		assertFalse((id.isEmpty()));
		
		//
	}
	
    //default value = 0
    @Parameter
    public String id;

	//Single parameter, use Object[]
    @Parameters(name = "{index}: testDomain - {0}")
    public static Object[] data() {
        return new Object[]{
                ""
        };
    }

    //@Test
    //ProductAPI pa= new ProductAPI() 
    //public void test_valid_args() {
      //  assertThat(pa.getProductInfo(id), is(true));
    //}

	
	public static void main(String args[]) throws JSONException
	{
		TestProductAPI testAPI = new TestProductAPI();
	//	testAPI.test_valid_args();
		
		// Create new ArrayList.
        ArrayList<Integer> elements = new ArrayList<Integer>();

        // Add three elements.
        elements.add(10);
        elements.add(15);
        elements.add(20);
        elements.add(2,33);
        elements.add(2,44);

        elements.add(2,55);


        // Get size and display.
        int count = elements.size();
        System.out.println("Count: " + count);

        // Loop through elements.
        for (int i = 0; i < elements.size(); i++) {
            int value = elements.get(i);
            System.out.println("Element: " + value);
		
		
		
	}
	
	
	//@Test(expected = IllegalArgumentException.class)
	//public void testToIntegerForNullParam(){
//	    TestProductAPI.toInteger(null);
	//}
	
}}
