package com.mycompany.events.domain;

import com.mycompany.events.domain.command.CreateUserCommand;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;

import static com.mycompany.events.domain.DomainRegistry.users;

/**
 * @author Juliano Silva
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class User {
    @Id
    private Long id;
    private String login;
    private String nodeId;
    private String avatarUrl;
    private String gravatarId;
    private String url;
    private String htmlUrl;
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private String reposUrl;
    private String eventsUrl;
    private String receivedEventsUrl;
    private String type;
    private boolean siteAdmin;

    static User create(final CreateUserCommand command) {
        final User user = new User();
        user.id = command.getId();
        user.login = command.getLogin();
        user.nodeId = command.getNodeId();
        user.avatarUrl = command.getAvatarUrl();
        user.gravatarId = command.getGravatarId();
        user.url = command.getUrl();
        user.htmlUrl = command.getHtmlUrl();
        user.followersUrl = command.getFollowersUrl();
        user.followingUrl = command.getFollowingUrl();
        user.gistsUrl = command.getGistsUrl();
        user.starredUrl = command.getStarredUrl();
        user.subscriptionsUrl = command.getSubscriptionsUrl();
        user.organizationsUrl = command.getOrganizationsUrl();
        user.reposUrl = command.getReposUrl();
        user.eventsUrl = command.getEventsUrl();
        user.receivedEventsUrl = command.getReceivedEventsUrl();
        user.type = command.getType();
        return users().save(user);
    }

    static Optional<User> findById(final Long id) {
        return users().findById(id);
    }
}
