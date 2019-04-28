package com.mycompany.events.api.rest.webhook;

import com.mycompany.events.api.rest.webhook.processor.EventManager;
import com.mycompany.events.api.rest.webhook.processor.command.ProcessEventCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * @author Juliano Silva
 */
@RestController
@RequiredArgsConstructor
public class WebhookController {

    private final EventManager eventManager;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void receive(@RequestHeader("X-GitHub-Event")
                        @NotEmpty String eventType,
                        @RequestBody String payload) {
        eventManager.process(new ProcessEventCommand(eventType, payload));
    }
}