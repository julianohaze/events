package com.mycompany.events.api.rest.webhook.processor;

import com.mycompany.events.api.rest.dto.IssueEventRequest;
import com.mycompany.events.api.rest.dto.Repository;
import com.mycompany.events.api.rest.webhook.EventType;
import com.mycompany.events.domain.GitRepository;
import com.mycompany.events.domain.Issue;
import com.mycompany.events.domain.User;
import com.mycompany.events.domain.DomainServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Juliano Silva
 */
@Component
@RequiredArgsConstructor
public class IssuesEventProcessor implements EventProcessor {

    private final ObjectMapper objectMapper;

    @Override
    public void process(final String payload) {
        try {
            final IssueEventRequest event = objectMapper.readValue(payload, IssueEventRequest.class);

            createUserIfNotPresent(event.getSender());
            createRepositoryIfNotPresent(event.getRepository());
            createIssueIfNotPresent(event.getIssue());
            createEvent(event);


        } catch (IOException e) {
            //TODO throw a real exception
            throw new RuntimeException(e.getMessage());
        }
    }

    private void createEvent(final IssueEventRequest event) {
        DomainServices.issueEvents().create(event.toCommand());
    }

    private Issue createIssueIfNotPresent(final com.mycompany.events.api.rest.dto.Issue issue) {
        return DomainServices.issues()
                .findById(issue.getId())
                .orElse(createIssue(issue));
    }

    private Issue createIssue(final com.mycompany.events.api.rest.dto.Issue issue) {
        return DomainServices.issues().create(issue.toCommand());
    }

    private GitRepository createRepositoryIfNotPresent(final Repository repository) {
        return DomainServices.repositories()
                .findById(repository.getId())
                .orElse(createRepository(repository));
    }

    private GitRepository createRepository(final Repository repository) {
        createOwner(repository);

        return DomainServices.repositories().create(repository.toCommand());
    }

    private User createOwner(final Repository repository) {
        return DomainServices.users()
                .findById(repository.getOwner().getId())
                .orElse(createUser(repository.getOwner()));
    }

    private User createUserIfNotPresent(final com.mycompany.events.api.rest.dto.User sender) {
        return DomainServices.users()
                .findById(sender.getId())
                .orElse(createUser(sender));
    }

    private User createUser(final com.mycompany.events.api.rest.dto.User sender) {
        return DomainServices.users().create(sender.toCommand());
    }

    @Override
    public boolean supports(final String eventType) {
        return EventType.ISSUES.isSameAs(eventType);
    }
}
