

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

public class WebServiceClient {



	  public final String[] post(String url, String json) {
	     String[] result = new String[2];
	     try {

	         HttpPost httpPost = new HttpPost(new URI(url));
	         httpPost.setHeader("Content-type", "application/json");
	         StringEntity sEntity = new StringEntity(json, "UTF-8");
	         httpPost.setEntity(sEntity);

	         HttpResponse response;
	         response = HttpClientSingleton.getHttpClientInstace().execute(httpPost);
	         HttpEntity entity = response.getEntity();

	         if (entity != null) {
	             result[0] = String.valueOf(response.getStatusLine().getStatusCode());
	             InputStream instream = entity.getContent();
	             result[1] = toString(instream);
	             instream.close();
	             
	         }

	     } catch (Exception e) {
	         result[0] = "0";
	         result[1] = "Falha de rede!" + e.getMessage();
	     }
	     return result;
	    }

	    private String toString(InputStream is) throws IOException {

	     byte[] bytes = new byte[1024];
	     ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     int lidos;
	     while ((lidos = is.read(bytes)) > 0) {
	         baos.write(bytes, 0, lidos);
	     }
	     return new String(baos.toByteArray());
	    }
}
