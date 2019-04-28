package com.mycompany.events.domain;

import com.mycompany.events.domain.command.CreateIssueEventCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Juliano Silva
 */
@Service
public class DefaultIssueEventService implements IssueEventService {

    @Override
    public IssueEvent create(final CreateIssueEventCommand command) {
        return IssueEvent.create(command);
    }

    @Override
    public Optional<IssueEvent> findById(final Long id) {
        return IssueEvent.findById(id);
    }
}
