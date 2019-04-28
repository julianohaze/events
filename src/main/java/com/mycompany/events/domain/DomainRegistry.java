package com.mycompany.events.domain;

import org.springframework.stereotype.Component;

/**
 * @author Juliano Silva
 */
@Component
class DomainRegistry {

    private static UserRepository USER_REPOSITORY;
    private static GitRepositoryRepository GITREPOSTORY;
    private static LabelRepository LABEL_REPOSITORY;
    private static IssueRepository ISSUE_REPOSITORY;
    private static IssueEventRepository ISSUE_EVENT_REPOSITORY;

    DomainRegistry(final UserRepository userRepository,
                   final GitRepositoryRepository gitRepository,
                   final IssueRepository issueRepository,
                   final IssueEventRepository issueEventRepository) {
        USER_REPOSITORY = userRepository;
        GITREPOSTORY = gitRepository;
        ISSUE_REPOSITORY = issueRepository;
        ISSUE_EVENT_REPOSITORY = issueEventRepository;
    }

    static UserRepository users() {
        return USER_REPOSITORY;
    }

    static GitRepositoryRepository repositories() {
        return GITREPOSTORY;
    }

    static LabelRepository labels() {
        return LABEL_REPOSITORY;
    }

    static IssueRepository issues() {
        return ISSUE_REPOSITORY;
    }

    static IssueEventRepository issueEvents() {
        return ISSUE_EVENT_REPOSITORY;
    }
}
