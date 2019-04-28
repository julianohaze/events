package com.mycompany.events.domain.command;

import lombok.Value;

/**
 * @author Juliano Silva
 */
@Value
public class CreateIssueEventCommand {
    private String action;
    private Long issueId;
    private Long repositoryId;
    private Long senderId;
}
