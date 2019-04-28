package com.mycompany.events.api.rest.dto;

import com.mycompany.events.domain.command.CreateIssueCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Juliano Silva
 */
@Value
public class Issue {
    private Long id;
    private String url;
    @JsonProperty("repository_url")
    private String repositoryUrl;
    @JsonProperty("labels_url")
    private String labelsUrl;
    @JsonProperty("comments_url")
    private String commentsUrl;
    @JsonProperty("events_url")
    private String eventsUrl;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("node_id")
    private String nodeId;
    private Integer number;
    private String title;
    private User user;
    private List<Label> labels;
    private String state;
    private boolean locked;
    private Long comments;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("closed_at")
    private LocalDateTime closedAt;
    @JsonProperty("author_association")
    private String authorAssociation;
    private String body;

    public CreateIssueCommand toCommand() {
        return new CreateIssueCommand(
                this.id,
                this.url,
                this.repositoryUrl,
                this.labelsUrl,
                this.commentsUrl,
                this.eventsUrl,
                this.htmlUrl,
                this.nodeId,
                this.number,
                this.title,
                this.user.getId(),
                this.labels.stream().map(Label::toLabelCommand).collect(toList()),
                this.state,
                this.locked,
                this.comments,
                this.createdAt,
                this.updatedAt,
                this.closedAt,
                this.authorAssociation,
                this.body
        );
    }


}
