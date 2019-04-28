package com.mycompany.events.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/**
 * @author Juliano Silva
 */
@Value
public class Label {
    private Long id;
    @JsonProperty("node_id")
    private String nodeId;
    private String url;
    private String name;
    private String color;
    @JsonProperty("default")
    private boolean isDefault;

    static com.mycompany.events.domain.command.dto.Label toLabelCommand(final Label lbl) {
        return new com.mycompany.events.domain.command.dto.Label(
                lbl.id,
                lbl.nodeId,
                lbl.url,
                lbl.name,
                lbl.color,
                lbl.isDefault
        );
    }
}
