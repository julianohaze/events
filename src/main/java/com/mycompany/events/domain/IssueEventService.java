package com.mycompany.events.domain;

import com.mycompany.events.domain.command.CreateIssueEventCommand;

import java.util.Optional;

/**
 * @author Juliano Silva
 */
public interface IssueEventService {

    IssueEvent create(final CreateIssueEventCommand command);

    Optional<IssueEvent> findById(final Long id);
}
