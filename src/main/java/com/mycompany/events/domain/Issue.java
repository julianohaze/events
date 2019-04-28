package com.mycompany.events.domain;

import com.mycompany.events.domain.command.CreateIssueCommand;
import com.mycompany.events.domain.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Juliano Silva
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Setter(AccessLevel.PRIVATE)
public class Issue {
    @Id
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
    @ManyToOne
    private User user;
    @OneToMany
    private List<Label> labels;
    private String state;
    private boolean locked;
    private Long comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime closedAt;
    private String authorAssociation;
    private String body;

    private static final Supplier<NotFoundException> USER_NOT_FOUND =
            () -> new NotFoundException("User not found");

    static Issue create(final CreateIssueCommand command) {
        final Issue issue = new Issue();
        issue.id = command.getId();
        issue.url = command.getUrl();
        issue.repositoryUrl = command.getRepositoryUrl();
        issue.labelsUrl = command.getLabelsUrl();
        issue.commentsUrl = command.getCommentsUrl();
        issue.eventsUrl = command.getCommentsUrl();
        issue.htmlUrl = command.getHtmlUrl();
        issue.nodeId = command.getNodeId();
        issue.number = command.getNumber();
        issue.title = command.getTitle();
        issue.user = DomainServices.users()
                .findById(command.getUserId())
                .orElseThrow(USER_NOT_FOUND);
        issue.state = command.getState();
        issue.locked = command.isLocked();
        issue.comments = command.getComments();
        issue.createdAt = command.getCreatedAt();
        issue.updatedAt = command.getUpdatedAt();
        issue.closedAt = command.getClosedAt();
        issue.authorAssociation = command.getAuthorAssociation();
        issue.body = command.getBody();
        DomainRegistry.issues().save(issue);

        command.getLabels()
                .forEach(lbl -> Label.create(issue.id, lbl));
        return issue;
    }

    public static Optional<Issue> findById(final Long id) {
        return DomainRegistry.issues().findById(id);
    }

    public List<IssueEvent> getEvents() {
        return IssueEvent.findByIssue(this);
    }
}
