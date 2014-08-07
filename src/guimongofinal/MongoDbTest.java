package guimongofinal;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

public class MongoDbTest {

    MongoClient mongoClient;
    DB db;
    String databaseName = "Java";
    String collectionName = "gui";
    DBCollection collection;
    String user = "amit";
    //String pass = "amit";
    char[] pass = new char[]{'a', 'm', 'i', 't'};

    public MongoDbTest() {
        try {
            mongoClient = new MongoClient("localhost", 27017);  //connecting to server
            if(mongoClient==null)
                throw new UnknownHostException();
            db = mongoClient.getDB(databaseName);                     //connecting to database
            System.out.println("Connected to database successfully");
            boolean auth = db.authenticate(user, pass);
            System.out.println("Authentication: " + auth);
        } catch (UnknownHostException ex) {
            System.out.println("Connection failed");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }catch(Exception ex){
            System.out.println("Unable to..");
        }
    }
    
    public void getAllDatabaseNames(){
        List<String> dbNames = mongoClient.getDatabaseNames();
            for(String name:dbNames){
                System.out.println("<----------Database: "+name+"---------->");
                db = mongoClient.getDB(name);
                Set<String> collections = db.getCollectionNames();
                for(String colName:collections)
                    System.out.println("Collection: "+colName);
            }
    }

}
