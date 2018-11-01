package com.snakeeyes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snakeeyes.model.GameResult;
import com.snakeeyes.service.GameService;
import com.snakeeyes.service.RequestValidationService;

@RestController
@RequestMapping("/snakeeyes")
public class GameController {	
	private static final Logger LOG = LoggerFactory.getLogger(GameController.class);
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private RequestValidationService requestValidator;
	
	@RequestMapping("/play")
	public GameResult play(@RequestParam("stake") String stake) {
			LOG.debug("Game started - validating request");
			requestValidator.validateRequest(stake);
			LOG.debug("Request validated");												
			return gameService.play(stake);
	}
}
