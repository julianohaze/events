package com.mycompany.events.domain;

import com.mycompany.events.domain.command.CreateUserCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Juliano Silva
 */
@Service
public class DefaultUserService implements UserService {

    @Override
    public User create(final CreateUserCommand command) {
        return User.create(command);
    }

    @Override
    public Optional<User> findById(final Long id) {
        return User.findById(id);
    }
}
