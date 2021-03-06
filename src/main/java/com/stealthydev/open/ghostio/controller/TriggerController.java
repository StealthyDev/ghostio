package com.stealthydev.open.ghostio.controller;

import com.stealthydev.open.ghostio.model.TriggerRequest;
import com.stealthydev.open.ghostio.service.TriggerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/trigger")
public class TriggerController {
    Logger log = Logger.getLogger(TriggerController.class);

    @Autowired
    TriggerService triggerService;

    @RequestMapping("/{layout}/{input}")
    public ResponseEntity<?> trigger(@PathVariable String layout, @PathVariable String input) {
        TriggerRequest triggerRequest = new TriggerRequest(layout, input);
        log.info("Triggering: " + triggerRequest.toString());
        try {
            triggerService.trigger(triggerRequest);
        } catch (AWTException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to execute command", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
