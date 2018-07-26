package forge;

import forge.sbcrudrestful.entity.Employee;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
 
public class Post_postForEntity {
 
    static final String URL_CREATE_EMPLOYEE = "http://localhost:8080/employee";
    
    public static final String USER_NAME = "forge";
    public static final String PASSWORD = "forge";
    
    public static void main(String[] args) {
    	
    	Logger logger = LoggerFactory.getLogger(Post_postForEntity.class);
 
        Employee newEmployee = new Employee((long) 10, "Tom", "Cleck");
        
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
 
        RestTemplate restTemplate = new RestTemplate();
 
        // Data attached to the request.
        HttpEntity<Employee> requestBody = new HttpEntity<>(newEmployee);
 
        // Send request with POST method.
        ResponseEntity<Employee> result 
             = restTemplate.postForEntity(URL_CREATE_EMPLOYEE, requestBody, Employee.class);
 
        logger.debug("Status code:" + result.getStatusCode());
 
        // Code = 200.
        if (result.getStatusCode() == HttpStatus.OK) {
            Employee e = result.getBody();
            logger.debug("(Client Side) Employee Created: "+ e.getId());
        }
 
    }
 
}