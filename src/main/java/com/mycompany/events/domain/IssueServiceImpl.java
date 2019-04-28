package com.mycompany.events.domain;

import com.mycompany.events.domain.command.CreateIssueCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Juliano Silva
 */
@Service
public class IssueServiceImpl implements IssueService {
    @Override
    public Issue create(final CreateIssueCommand command) {
        return Issue.create(command);
    }

    @Override
    public Optional<Issue> findById(final Long id) {
        return Issue.findById(id);
    }
}
