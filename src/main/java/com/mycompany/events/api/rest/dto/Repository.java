package com.mycompany.events.api.rest.dto;

import com.mycompany.events.domain.command.CreateGitRepositoryCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/**
 * @author Juliano Silva
 */
@Value
public class Repository {
    private Long id;
    @JsonProperty("node_id")
    private String nodeId;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private User owner;
    @JsonProperty("private")
    private boolean isPrivate;
    private String description;

    public CreateGitRepositoryCommand toCommand() {
        return new CreateGitRepositoryCommand(
                this.id,
                this.nodeId,
                this.name,
                this.fullName,
                this.owner.getId(),
                this.isPrivate,
                this.description
        );
    }
}
