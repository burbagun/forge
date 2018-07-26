package forge;

import forge.sbcrudrestful.entity.Employee;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
 
public class PutWithExchange {
 
    static final String URL_UPDATE_EMPLOYEE = "http://localhost:8080/employee";
    static final String URL_EMPLOYEE_PREFIX = "http://localhost:8080/employee";
 
    public static final String USER_NAME = "forge";
    public static final String PASSWORD = "forge";
    
    public static void main(String[] args) {
    	
    	Logger logger = LoggerFactory.getLogger(PutWithExchange.class);
 
        Long empNo = (long) 1;
 
        Employee updateInfo = new Employee(empNo, "Tom", "Cleck");
 
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
     // 
        // Authentication
        // 
        String auth = USER_NAME + ":" + PASSWORD;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
 
        RestTemplate restTemplate = new RestTemplate();
 
        // Data attached to the request.
        HttpEntity<Employee> requestBody = new HttpEntity<>(updateInfo, headers);
 
        // Send request with PUT method.
        restTemplate.exchange(URL_UPDATE_EMPLOYEE, HttpMethod.PUT, requestBody, Void.class);
 
        String resourceUrl = URL_EMPLOYEE_PREFIX + "/" + empNo;
 
        Employee e = restTemplate.getForObject(resourceUrl, Employee.class);
 
        if (e != null) {
            logger.debug("(Client side) Employee after update: ");
            logger.debug("Employee: " + e.getId() + " - " + e.getName());
        }
    }
 
}
