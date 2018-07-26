package forge;

import forge.sbcrudrestful.entity.Employee;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
 
public class PutSimple {
	
	public static final String USER_NAME = "forge";
    public static final String PASSWORD = "forge";
 
    static final String URL_UPDATE_EMPLOYEE = "http://localhost:8080/employee";
    static final String URL_EMPLOYEE_PREFIX = "http://localhost:8080/employee";
 
    public static void main(String[] args) {
    	
    	Logger logger = LoggerFactory.getLogger(PutSimple.class);
 
        Long empNo = (long) 1;
 
        Employee updateInfo = new Employee(empNo, "Tom", "Cleck");
 
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        String auth = USER_NAME + ":" + PASSWORD;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
 
        RestTemplate restTemplate = new RestTemplate();
 
        // Data attached to the request.
        HttpEntity<Employee> requestBody = new HttpEntity<>(updateInfo, headers);
 
        // Send request with PUT method.
        restTemplate.put(URL_UPDATE_EMPLOYEE, requestBody, new Object[] {});
 
        String resourceUrl = URL_EMPLOYEE_PREFIX + "/" + empNo;
 
        Employee e = restTemplate.getForObject(resourceUrl, Employee.class);
 
        if (e != null) {
            logger.debug("(Client side) Employee after update: ");
            logger.debug("Employee: " + e.getId() + " - " + e.getName());
        }
    }
 
}
