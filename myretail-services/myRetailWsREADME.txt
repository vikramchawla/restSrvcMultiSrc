<<<< myRetail RESTful service Product API >>>> 

end-to-end Proof-of-Concept which aggregates product data from multiple sources and return it as JSON to the caller. 

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

>>> An application that performs the following actions: 

1. Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
  * Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 
  * Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
2. Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from api.target.com, but let’s just pretend this is an internal resource hosted by myRetail)    * Example: https://api.target.com/products/v3/13860428?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz
3. Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.  
4. BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 

~~~~ Technologies ~~~~
 1. Jersey (Java implementation of jax-rs) archetypeArtifactId=jersey-quickstart-webapp
 2. MongoDB (no sql DB)
 3. Maven (build and dependency management tool)
 4. Java
 5. Apache tomcat

~~~~ Setup ~~~~
. Have Mongo DB community instance installed on your box.Additionally follow https://docs.mongodb.com/master/tutorial/install-mongodb-on-windows/#install-mongodb-community-edition
. Configure the service to use the database
. Run mvn clean package

 
~~~~~~ Running MongoDB ~~~~~~
command: “mongod” on console/terminal to get server started
followed by mongo in another terminal window to get the shell and…

~~~~~~~~ Create documents in product_prices collection ~~~~~~~~
. use myRetail
.db.createCollection('product_prices');
.db.product_prices.insert({product_id:"15117729”,value:12, Currency:"USD" });

….
…..
…

~~~~ How to Configure tomcat ~~~~
install version 7 and configure in eclipse under servers for our application
refer apache.org documentation on installing and configuring in eclipse.

~~~~ How to Compile ~~~~
mvn clean package / Eclipse tomcat server start hosting jersey war

~~~~ How to Run ~~~~
mvn clean deploy
server is running on http://127.0.0.1:8080/myretail-services/products/id/16696652
