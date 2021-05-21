package example.mongo;

import com.mongodb.MongoClient;

public class MongoConn {

	public static void main(String[] args) {
		MongoClient mc = new MongoClient("localhost", 27017);
//		MongoDatabase db = mc.getDatabase("WayneDB");
		
		// 含權限設定與加密的連線
//		MongoCredential credential = MongoCredential.createCredential("Wayne", "WayneDB", "123456".toCharArray());
//		MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();
//		MongoClient mc2 = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential), options);

		mc.close();
//		mc2.close();
	}

}
