package forge;

import forge.sbcrudrestful.entity.Employee;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
 
public class Post_postForObject {
 
   static final String URL_CREATE_EMPLOYEE = "http://localhost:8080/employee";
 
   public static final String USER_NAME = "forge";
   public static final String PASSWORD = "forge";
   
   public static void main(String[] args) {
	   
	   Logger logger = LoggerFactory.getLogger(Post_postForObject.class);
 
      Long id = (long) 1;
 
      Employee newEmployee = new Employee(id, "Tom", "Cleck");
      
   // HttpHeaders
      HttpHeaders headers = new HttpHeaders();
  
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
 
      RestTemplate restTemplate = new RestTemplate();
 
      // Data attached to the request.
      HttpEntity<Employee> requestBody = new HttpEntity<>(newEmployee, headers);
 
      // Send request with POST method.
      Employee e = restTemplate.postForObject(URL_CREATE_EMPLOYEE, requestBody, Employee.class);
 
      if (e != null && e.getId() != null) {
 
         logger.debug("Employee created: " + e.getId());
      } else {
         logger.debug("Something error!");
      }
 
   }
 
}
