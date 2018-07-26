package forge;

import java.nio.charset.Charset;
import java.util.Arrays;
 
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class GetWithBasicAuth {
 
    public static final String USER_NAME = "forge";
    public static final String PASSWORD = "forge";
    
 
    static final String URL_EMPLOYEES = "http://localhost:8080/employees";
 
    public static void main(String[] args) {
    	
    	Logger logger = LoggerFactory.getLogger(GetWithBasicAuth.class);
 
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
 
        // 
        // Authentication
        // 
        String auth = USER_NAME + ":" + PASSWORD;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        // 
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");
 
        // HttpEntity<String>: To get result as String.
        HttpEntity<String> entity = new HttpEntity<String>(headers);
 
        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();
 
        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES, //
                HttpMethod.GET, entity, String.class);
 
        String result = response.getBody();
 
        logger.debug(result);
    }
 
}
