package com.myretail;

import javax.ws.rs.Consumes;
//import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

import com.myretail.repository.ProductPrice;

/**
 * Root resource (exposed at "id" path)
 */
@Path("/id") // http://localhost:8080/myretail-services/products/id/
public class ProductAPI {

	private ProductPrice productPrice = new ProductPrice();
	private static final int BEGOFFSET = 22;

	/**
	 * Method handling HTTP POST requests. The returned object will be sent to the
	 * client as TEXT_PLAIN media type.
	 *
	 * @return String that will be returned as a JSON response.
	 * @throws JSONException
	 */
	@POST
	@Path("")// http://localhost:8080/myretail-services/products/id/
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String updatePaymentForID(String id) {
		System.out.println("b4>>" + productPrice.getProductPrice(id));
		productPrice.updateProductPrice(id); // Example of updating price in MongoDB
		System.out.println("after>>" + productPrice.getProductPrice(id));
		return productPrice.getProductPrice(id);
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as TEXT_PLAIN media type.
	 *
	 * @return String that will be returned as a JSON response.
	 * @throws JSONException
	 */
	@GET
	@Path("{id}") // http://localhost:8080/myretail-services/products/id/16696652
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductInfo(@PathParam("id") String id) throws JSONException {
		
		WebTarget target = ClientBuilder.newClient().target("http://redsky.target.com/v2/pdp/tcin/" + id
				+ "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics/");
		Response response = target.request().get();
		String pName = response.readEntity(String.class).toString();

		int b = pName.indexOf("general_description") + BEGOFFSET;
		int e = pName.indexOf("}", b) - 1;

		JSONObject json = new JSONObject();

		json.put("name", pName.substring(b, e));
		json.put("id", Integer.parseInt(id));
		json.put("current_price", new JSONObject(productPrice.getProductPrice(id)));
		return json.toString();
	}

}
