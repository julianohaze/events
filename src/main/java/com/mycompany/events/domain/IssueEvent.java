package com.mycompany.events.domain;


import com.mycompany.events.domain.command.CreateIssueEventCommand;
import com.mycompany.events.domain.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 *
 * @author Juliano Silva
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class IssueEvent {
    @Id
    @GeneratedValue
    private Long id;
    private String action;
    @ManyToOne
    private Issue issue;
    @ManyToOne
    private GitRepository repository;
    @ManyToOne
    private User sender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final Supplier<NotFoundException> ISSUE_NOT_FOUND =
            () -> new NotFoundException("Issue not found");

    private static final Supplier<NotFoundException> REPOSITORY_NOT_FOUND =
            () -> new NotFoundException("Repository not found");

    private static final Supplier<NotFoundException> USER_NOT_FOUND =
            () -> new NotFoundException("User not found");

    static IssueEvent create(final CreateIssueEventCommand command) {
        final IssueEvent event = new IssueEvent();
        event.action = command.getAction();
        event.issue = DomainRegistry.issues()
                .findById(command.getIssueId())
                .orElseThrow(ISSUE_NOT_FOUND);
        event.repository = DomainRegistry.repositories()
                .findById(command.getRepositoryId())
                .orElseThrow(REPOSITORY_NOT_FOUND);
        event.sender = DomainServices.users()
                .findById(command.getSenderId())
                .orElseThrow(USER_NOT_FOUND);
        event.createdAt = LocalDateTime.now();
        event.updatedAt = LocalDateTime.now();
        return DomainRegistry.issueEvents().save(event);
    }

    public static Optional<IssueEvent> findById(final Long id) {
        return DomainRegistry.issueEvents().findById(id);
    }

    static List<IssueEvent> findByIssue(final Issue issue) {
        return DomainRegistry.issueEvents().findByIssue(issue);
    }
}
