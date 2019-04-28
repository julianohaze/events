package com.mycompany.events.domain;

import com.mycompany.events.domain.command.CreateGitRepositoryCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Juliano Silva
 */
@Service
public class DefaultGitRepositoryService implements GitRepostoryService {
    @Override
    public GitRepository create(final CreateGitRepositoryCommand command) {
        return GitRepository.create(command);
    }

    @Override
    public Optional<GitRepository> findById(final Long id) {
        return GitRepository.findById(id);
    }
}
