package com.mycompany.events.api.rest;

import com.mycompany.events.api.rest.dto.IssueEventResponse;
import com.mycompany.events.domain.Issue;
import com.mycompany.events.domain.exception.NotFoundException;
import com.mycompany.events.domain.DomainServices;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

/**
 * @author Juliano Silva
 */
@RestController
@RequestMapping("api/v1/issues/{issue}/events")
@Validated
public class EventsController {

    private static final String ISSUE = "issue";

    private static final Supplier<NotFoundException> ISSUE_NOT_FOUND =
            () -> new NotFoundException("Issue not found");

    @GetMapping
    public List<IssueEventResponse> getEvents(
            @PathVariable(ISSUE) final Long issueId) {

        final Issue issue = DomainServices.issues()
                .findById(issueId)
                .orElseThrow(ISSUE_NOT_FOUND);

        return issue.getEvents()
                .stream()
                .map(IssueEventResponse::fromEvent)
                .collect(toList());
    }

}
