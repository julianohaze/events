package com.mycompany.events.api.rest.dto;

import com.mycompany.events.domain.IssueEvent;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * @author Juliano Silva
 */
@Value
public class IssueEventResponse {

    private String action;
    private Long issueId;
    private String issueTitle;
    private Long repositoryId;
    private String repositoryName;
    private Long senderId;
    private String senderLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static IssueEventResponse fromEvent(final IssueEvent issueEvent) {
        return new IssueEventResponse(
                issueEvent.getAction(),
                issueEvent.getIssue().getId(),
                issueEvent.getIssue().getTitle(),
                issueEvent.getRepository().getId(),
                issueEvent.getRepository().getName(),
                issueEvent.getSender().getId(),
                issueEvent.getSender().getLogin(),
                issueEvent.getCreatedAt(),
                issueEvent.getUpdatedAt()
        );
    }
}
