package com.mycompany.events.domain.command;

import lombok.Value;

/**
 * @author Juliano Silva
 */
@Value
public class CreateGitRepositoryCommand {
    private Long id;
    private String nodeId;
    private String name;
    private String fullName;
    private Long ownerId;
    private boolean isPrivate;
    private String description;
}
