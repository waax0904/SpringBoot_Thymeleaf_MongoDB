package example.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import org.bson.Document;
import org.bson.types.ObjectId;


import java.util.Arrays;

public class MongoUpdate {
	
	public static void main(String[] args) {
		MongoClient mc = new MongoClient("localhost", 27017);
		MongoDatabase db = mc.getDatabase("MyDB");
		MongoCollection<Document> col = db.getCollection("restaurants");
		
		col.updateOne(
                eq("_id", new ObjectId("57506d62f57802807471dd41")),
                combine(set("stars", 1), set("contact.phone", "228-555-9999"), currentDate("lastModified")));
		
		col.updateMany(
	              eq("stars", 2),
	              combine(set("stars", 0), currentDate("lastModified")));
		
		col.replaceOne(
                eq("_id", new ObjectId("57506d62f57802807471dd41")),
                new Document("name", "Green Salads Buffet")
                        .append("contact", "TBD")
                        .append("categories", Arrays.asList("Salads", "Health Foods", "Buffet")));
		
		col.deleteOne(eq("_id", new ObjectId("57506d62f57802807471dd41")));
		
		col.deleteMany(eq("stars", 4));
		
		mc.close();
	}
}
