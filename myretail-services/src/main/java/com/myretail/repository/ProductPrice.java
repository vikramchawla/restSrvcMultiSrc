package com.myretail.repository;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class ProductPrice {

	MongoClient mongoClient = new MongoClient();

	/**
	 * Method updating price of matched product_id in product_prices collection
	 * input - String representing product_id whose price needs update
	 * 
	 * @return None
	 */
	public void updateProductPrice(String id) {
		MongoCollection<Document> collection = mongoClient.getDatabase("myRetail").getCollection("product_prices");
		//System.out.println("before updating >>>" + id);
		Bson filter = Filters.eq("product_id", id);
		Bson updates = Updates.set("value", 99);
		collection.findOneAndUpdate(filter, updates);
	}

	/**
	 * Method handling getting Product price from Mongo DB The returned object will
	 * be sent to the client as "JSON" formated string.
	 *
	 * @return String that will be returned as a JSON response.
	 */
	public String getProductPrice(String id) {

		// Establishing connection uri
		// MongoClientURI uri = new
		// MongoClientURI("mongodb://guest:g121@localhost:27017/?authSource=myRetail");

		// get handle to "myRetail" DB
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("myRetail");

		// Getting handle to Collection
		DBCollection dbcollection = db.getCollection("product_prices");

		BasicDBObject searchObject = new BasicDBObject();
		BasicDBObject fieldObject = new BasicDBObject();
		fieldObject.put("_id", 0);
		fieldObject.put("currency", 1);
		fieldObject.put("value", 1);
		searchObject.put("product_id", id);

		// Cursor to pull matched document's particular field
		DBCursor resultSubset = dbcollection.find(searchObject, fieldObject);
		// DBCursor resultSet = dbcollection.find();

		// printing all documents in collection
		/*
		 * while(resultSet.hasNext()) System.out.println(resultSet.next().toString());
		 */

		// returning matched collections records in myRetailB
		return resultSubset.next().toString();

	}

}
