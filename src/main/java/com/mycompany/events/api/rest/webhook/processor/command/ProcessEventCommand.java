package com.mycompany.events.api.rest.webhook.processor.command;

import lombok.Value;

/**
 * @author Juliano Silva
 */
@Value
public class ProcessEventCommand {
    private String eventType;
    private String payload;
}
