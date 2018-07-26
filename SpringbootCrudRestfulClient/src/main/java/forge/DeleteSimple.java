package forge;

import forge.sbcrudrestful.entity.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
 
public class DeleteSimple {
 
    public static void main(String[] args) {
    	
    	Logger logger = LoggerFactory.getLogger(DeleteSimple.class);
 
        RestTemplate restTemplate = new RestTemplate();
 
        // empNo="E01"
        String resourceUrl = "http://localhost:8080/employee/1";
 
        // Send request with DELETE method.
        restTemplate.delete(resourceUrl);
 
        // Get 
        Employee e = restTemplate.getForObject(resourceUrl, Employee.class);
 
        if (e != null) {
        	logger.debug("(Client side) Employee after delete: ");
        	logger.debug("Employee: " + e.getId() + " - " + e.getName());
        } else {
        	logger.debug("Employee not found!");
        }
    }
}
