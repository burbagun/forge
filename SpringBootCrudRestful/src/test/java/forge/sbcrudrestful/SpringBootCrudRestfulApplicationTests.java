package forge.sbcrudrestful;

import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCrudRestfulApplicationTests {

	public static final String USER_NAME = "forge";
	public static final String PASSWORD = "forge";
	static final String URL_EMPLOYEES = "http://localhost:8080/employees";
	Logger logger = LoggerFactory.getLogger(SpringBootCrudRestfulApplicationTests.class);

	@Test
	public void GetWithBasicAuth() {

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
		assertTrue (response.getStatusCode().equals(HttpStatus.OK));

		logger.debug(result);
	}

	@Test
	public void contextLoads() {
	}

}
