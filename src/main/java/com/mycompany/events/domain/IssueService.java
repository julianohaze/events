package com.mycompany.events.domain;

import com.mycompany.events.domain.command.CreateIssueCommand;

import java.util.Optional;

/**
 * @author Juliano Silva
 */
public interface IssueService {


    Issue create(final CreateIssueCommand command);

    Optional<Issue> findById(final Long id);
}
