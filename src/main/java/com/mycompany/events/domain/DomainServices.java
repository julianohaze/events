package com.mycompany.events.domain;

import org.springframework.stereotype.Component;

/**
 * @author Juliano Silva
 */
@Component
public class DomainServices {

    private static UserService USER_SERVICE;
    private static GitRepostoryService GITREPOSITORY_SERVICE;
    private static IssueService ISSUE_SERVICE;
    private static IssueEventService ISSUE_EVENT_SERVICE;

    public DomainServices(final UserService userService,
                          final GitRepostoryService gitRepostoryService,
                          final IssueService issueService,
                          final IssueEventService issueEventService) {
        USER_SERVICE = userService;
        GITREPOSITORY_SERVICE = gitRepostoryService;
        ISSUE_SERVICE = issueService;
        ISSUE_EVENT_SERVICE = issueEventService;
    }

    public static UserService users() {
        return USER_SERVICE;
    }

    public static GitRepostoryService repositories() {
        return GITREPOSITORY_SERVICE;
    }

    public static IssueService issues() {
        return ISSUE_SERVICE;
    }

    public static IssueEventService issueEvents() {
        return ISSUE_EVENT_SERVICE;
    }
}
