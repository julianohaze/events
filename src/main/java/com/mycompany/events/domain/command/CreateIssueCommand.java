package com.mycompany.events.domain.command;

import com.mycompany.events.domain.command.dto.Label;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Juliano Silva
 */
@Value
public class CreateIssueCommand {
    private Long id;
    private String url;
    private String repositoryUrl;
    private String labelsUrl;
    private String commentsUrl;
    private String eventsUrl;
    private String htmlUrl;
    private String nodeId;
    private Integer number;
    private String title;
    private Long userId;
    private List<Label> labels;
    private String state;
    private boolean locked;
    private Long comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime closedAt;
    private String authorAssociation;
    private String body;

    public List<Label> getLabels() {
        return Optional
                .ofNullable(labels)
                .orElse(new ArrayList<>());
    }
}
