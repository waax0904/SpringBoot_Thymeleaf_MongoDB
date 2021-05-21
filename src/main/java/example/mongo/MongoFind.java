package example.mongo;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

import static com.mongodb.client.model.Filters.*;

public class MongoFind {

	public static void main(String[] args) {
		Block<Document> printBlock = new Block<Document>() {
			@Override
			public void apply(final Document document) {
				System.out.println(document.toJson());
			}
		};

		MongoClient mc = new MongoClient("localhost", 27017);
		MongoDatabase db = mc.getDatabase("MyDB");
		MongoCollection<Document> col = db.getCollection("restaurants");

		// Get all
		col.find().forEach(printBlock);

		// with criteria
		col.find(eq("name", "456 Cookies Shop")).forEach(printBlock);

		col.find(new Document("stars", new Document("$gte", 2).append("$lt", 5)).append("categories", "Bakery"))
				.forEach(printBlock);

		col.find(and(gte("stars", 2), lt("stars", 5), eq("categories", "Bakery"))).forEach(printBlock);

		col.find(and(gte("stars", 2), lt("stars", 5), eq("categories", "Bakery")))
				// limit the fields to return for all matching documents.
				// 1 is true
				// 0 is false
				.projection(new Document("name", 1).append("stars", 1).append("categories", 1).append("_id", 0))
				.forEach(printBlock);

		// Sort
		col.find(and(gte("stars", 2), lt("stars", 5), eq("categories", "Bakery"))).sort(Sorts.ascending("name"))
				.forEach(printBlock);

		mc.close();
	}
}
