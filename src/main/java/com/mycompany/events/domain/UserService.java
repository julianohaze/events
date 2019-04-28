package com.mycompany.events.domain;

import com.mycompany.events.domain.command.CreateUserCommand;

import java.util.Optional;

/**
 * @author Juliano Silva
 */
public interface UserService {

    User create(final CreateUserCommand command);

    Optional<User> findById(final Long id);
}
