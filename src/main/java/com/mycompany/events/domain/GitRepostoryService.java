package com.mycompany.events.domain;

import com.mycompany.events.domain.command.CreateGitRepositoryCommand;

import java.util.Optional;

/**
 * @author Juliano Silva
 */
public interface GitRepostoryService {

    GitRepository create(final CreateGitRepositoryCommand command);

    Optional<GitRepository> findById(final Long id);
}
