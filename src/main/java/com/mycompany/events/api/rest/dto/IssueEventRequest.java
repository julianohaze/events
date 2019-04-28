package com.mycompany.events.api.rest.dto;

import com.mycompany.events.domain.command.CreateIssueEventCommand;
import lombok.Value;

/**
 * @author Juliano Silva
 */
@Value
public class IssueEventRequest {
    private Long id;
    private String action;
    private Issue issue;
    private Repository repository;
    private User sender;

    public CreateIssueEventCommand toCommand() {
        return new CreateIssueEventCommand(
                this.action,
                this.issue.getId(),
                this.repository.getId(),
                this.sender.getId()
        );
    }
}
