package com.mycompany.events.domain.command.dto;

import lombok.Value;

/**
 * @author Juliano Silva
 */
@Value
public class Label {
    private Long id;
    private String nodeId;
    private String url;
    private String name;
    private String color;
    private boolean isDefault;
}
