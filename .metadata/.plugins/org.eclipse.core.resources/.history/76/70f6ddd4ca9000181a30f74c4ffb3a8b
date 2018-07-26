package forge;

import forge.sbcrudrestful.entity.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
 
public class Delete {
 
   public static void main(String[] args) {
	   
	   Logger logger = LoggerFactory.getLogger(Delete.class);
 
      RestTemplate restTemplate = new RestTemplate();
 
      // URL with URI-variable
      String resourceUrl = "http://localhost:8080/employee/{empNo}";
 
      Object[] uriValues = new Object[] { "1" };
 
      // Send request with DELETE method.
      restTemplate.delete(resourceUrl, uriValues);
 
      Employee e = restTemplate.getForObject(resourceUrl, Employee.class);

      if (e != null) {
         logger.debug("(Client side) Employee after delete: ");
         logger.debug("Employee: " + e.getId() + " - " + e.getName());
      } else {
    	  logger.debug("Employee not found!");
      }
   } 
}
