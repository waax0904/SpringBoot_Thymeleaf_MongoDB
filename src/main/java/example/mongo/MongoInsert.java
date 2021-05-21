package example.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoInsert {
	
	public static void main(String[] args) {
		MongoClient mc = new MongoClient("localhost", 27017);
		MongoDatabase db = mc.getDatabase("MyDB");
		MongoCollection<Document> col = db.getCollection("restaurants");
		
		Document document = new Document("name", "Café Con Leche")
	               .append("contact", new Document("phone", "228-555-0149")
	               .append("email", "cafeconleche@example.com")
	               .append("location",Arrays.asList(-73.92502, 40.8279556)))
	               .append("stars", 3)
	               .append("categories", Arrays.asList("Bakery", "Coffee", "Pastries"));

		col.insertOne(document);
		
		
		///////////////// Insert Many ////////////////
		
		Document doc1 = new Document("name", "Amarcord Pizzeria")
	               .append("contact", new Document("phone", "264-555-0193")
	                                       .append("email", "amarcord.pizzeria@example.net")
	                                       .append("location",Arrays.asList(-73.88502, 40.749556)))
	               .append("stars", 2)
	               .append("categories", Arrays.asList("Pizzeria", "Italian", "Pasta"));


		Document doc2 = new Document("name", "Blue Coffee Bar")
	               .append("contact", new Document("phone", "604-555-0102")
	                                       .append("email", "bluecoffeebar@example.com")
	                                       .append("location",Arrays.asList(-73.97902, 40.8479556)))
	               .append("stars", 5)
	               .append("categories", Arrays.asList("Coffee", "Pastries"));

		List<Document> documents = new ArrayList<>();
		documents.add(doc1);
		documents.add(doc2);

		col.insertMany(documents);
		
		mc.close();
	}
}
