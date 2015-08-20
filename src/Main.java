import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		
		
		System.out.println( "Sending POST to GCM" );

        PushRequest c = new PushRequest();
        c.addRegId("APA91bE3....");
        c.createData("Test Title", "Funcionou!");
        
        Gson gson = new GsonBuilder().create();
        String request = gson.toJson(c);
        System.out.println("Request: " + request);
        
        String[] resposta = new WebServiceClient().post("http://localhost:8080/Unipam.EnviaPushMobile/PushMobile/postGcm", request);           	        
    	
        System.out.println("Retorno : " + Arrays.toString(resposta));
        		
	}

}
