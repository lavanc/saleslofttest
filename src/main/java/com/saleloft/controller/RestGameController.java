package com.saleloft.controller;


import com.saleloft.core.ConnectFour;
import com.saleloft.service.ResultsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class RestGameController {

    @Autowired
    ResultsService resultsService;

    AtomicInteger count = new AtomicInteger(0);
    private final Logger logger = LoggerFactory.getLogger(RestGameController.class);
    private final ConnectFour CONNECT_FOUR = new ConnectFour();
    AtomicReference<String[][]> CONNECT_FOUR_MATRIX = new AtomicReference<>();

    @PostMapping("/salesloft/game/connectFour/start")
    public ResponseEntity<?> start() {

        logger.info("play connect four! start");
        String[][] k = CONNECT_FOUR.start();
        CONNECT_FOUR_MATRIX.set(k);
        String[][] l = k;
        return new ResponseEntity(l, HttpStatus.OK);

    }


    // Multiple file upload
    @PostMapping("/salesloft/game/connectFour/red")
    public ResponseEntity<?> onTurnRed(
            @RequestParam("extraField") String extraField) {

        logger.info("play connect four on turn red {}", extraField);

        if (count.get() % 2 == 0) {
            CONNECT_FOUR.setRedInput(Integer.parseInt(extraField));
            String[][] initialValue = CONNECT_FOUR_MATRIX.get();
            String[][] update = CONNECT_FOUR.applyRedInputToMatrix(initialValue);
            String winnerCheck = CONNECT_FOUR.checkWinner(update);
            Object l = winnerCheck == null ? update : winnerCheck != null && winnerCheck == "R"
                    ? "RED IS WINNER" : null;

            CONNECT_FOUR_MATRIX.compareAndSet(initialValue, update);
            count.getAndIncrement();
            if (winnerCheck != null) {
                resultsService.saveResults(update);
                reset();
            }
            return new ResponseEntity(l, HttpStatus.OK);
        } else {
            return new ResponseEntity("This is not reds turn", HttpStatus.OK);
        }


    }

    private void reset() {
        count.getAndSet(0);
    }

    @PostMapping("/salesloft/game/connectFour/yellow")
    public ResponseEntity<?> onTurnYellow(
            @RequestParam("extraField") String extraField) {

        logger.info("play connect four on turn yellow  {}", extraField);

        if (count.get() % 2 != 0) {
            CONNECT_FOUR.setYellowInput(Integer.parseInt(extraField));
            String[][] initialValue = CONNECT_FOUR_MATRIX.get();
            String[][] update = CONNECT_FOUR.applyYellowInputToMatrix(initialValue);
            String winnerCheck = CONNECT_FOUR.checkWinner(update);
            Object l = winnerCheck == null ? update : winnerCheck != null && winnerCheck == "Y"
                    ? "YELLOW IS WINNER" : null;
            CONNECT_FOUR_MATRIX.compareAndSet(initialValue, update);

            count.getAndIncrement();
            if (winnerCheck != null) {
                resultsService.saveResults(update);
                reset();
            }
            return new ResponseEntity(l, HttpStatus.OK);
        } else {
            return new ResponseEntity("This is not yellows turn", HttpStatus.OK);
        }

    }



}
