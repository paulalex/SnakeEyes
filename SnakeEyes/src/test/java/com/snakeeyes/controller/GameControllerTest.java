package com.snakeeyes.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.snakeeyes.model.GameResult;
import com.snakeeyes.service.GameService;
import com.snakeeyes.service.RequestValidationService;

import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
@AutoConfigureMockMvc
public class GameControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private GameService gameService;
	
	@MockBean
	private RequestValidationService requestValidationService;
	
	@Before
	public void setUp() {
		when(gameService.play(anyString())).thenReturn(getGameResult());
		doNothing().when(requestValidationService).validateRequest(anyString());
	}
	
    @Test
    public void testController() throws Exception {
        this.mockMvc.perform(get("/snakeeyes/play?stake=10.00").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
        		.andExpect(content().string(containsString("{\"stake\":3.0,\"winnings\":90.0,\"dice1\":1,\"dice2\":1,\"payout_name\":\"snake_eyes\"}")));
    }
    
    private GameResult getGameResult() {
    	return GameResult.builder().withDiceOne(1)
    			.withDiceTwo(1)
    			.withStake(3.00)
    			.withWinnings(90.00)
    			.withPayoutName("snake_eyes")
    			.build();
    }
}