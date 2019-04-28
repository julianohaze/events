package com.mycompany.events.domain;

import com.mycompany.events.domain.command.CreateGitRepositoryCommand;
import com.mycompany.events.domain.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Optional;
import java.util.function.Supplier;

import static com.mycompany.events.domain.DomainRegistry.repositories;
import static com.mycompany.events.domain.DomainServices.users;

/**
 * @author Juliano Silva
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class GitRepository {
    @Id
    private Long id;
    private String nodeId;
    private String name;
    private String fullName;
    @ManyToOne
    private User owner;
    private boolean isPrivate;
    private String description;

    private static final Supplier<NotFoundException> USER_NOT_FOUND =
            () -> new NotFoundException("User not found");

    static GitRepository create(final CreateGitRepositoryCommand command) {
        final GitRepository repo = new GitRepository();
        repo.id = command.getId();
        repo.nodeId = command.getNodeId();
        repo.name = command.getName();
        repo.fullName = command.getFullName();
        repo.owner = users()
                .findById(command.getOwnerId())
                .orElseThrow(USER_NOT_FOUND);
        repo.isPrivate = command.isPrivate();
        repo.description = command.getDescription();
        return repositories().save(repo);
    }

    static Optional<GitRepository> findById(final Long id) {
        return repositories().findById(id);
    }
}
