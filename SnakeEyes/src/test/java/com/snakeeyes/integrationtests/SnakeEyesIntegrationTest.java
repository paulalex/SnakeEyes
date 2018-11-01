package com.snakeeyes.integrationtests;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.jayway.restassured.RestAssured;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

/**
 * Dirty the context because even with a low balance the chance of a win would 
 * cause an intermittent failure of the inadequate balance test. This causes
 * some additional overhead but ensures no intermittent failures occur.
 * 
 * @author paoc
 *
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:integration-test.properties")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class SnakeEyesIntegrationTest {
    @Value("${local.server.port}") 
    int port;

    @Before
    public void setUp() {       
        RestAssured.port = port;
    }
   
    @Test
    public void success() {       
        when().get("snakeeyes/play?stake=1.00")
        	.then().statusCode(HttpStatus.SC_OK);        	            
    }
    
    @Test
    public void invalidNumericStake() {       
        when().get("snakeeyes/play?stake=stake")
        	.then().statusCode(HttpStatus.SC_BAD_REQUEST)
        	.and().body(containsString("{\"field\":\"stake\",\"message\":\"Invalid Numeric Value\"}"));             
    }
    
    @Test
    public void invalidStakeAmount() {       
        when().get("snakeeyes/play?stake=77.00")
        	.then().statusCode(HttpStatus.SC_BAD_REQUEST)
        	.and().body(containsString("{\"field\":\"stake\",\"message\":\"Invalid Value\"}"));             
    }
    
    @Test
    public void inadequateFunds() {                        
        when().get("snakeeyes/play?stake=10.00")
    	.then().statusCode(HttpStatus.SC_BAD_REQUEST)
    	.and().body(containsString("{\"field\":\"funds\",\"message\":\"Funds Too Low\"}"));   
    }
}