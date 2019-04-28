package com.mycompany.events.api.rest.webhook;

import com.mycompany.events.domain.GitRepository;
import com.mycompany.events.domain.Issue;
import com.mycompany.events.domain.IssueEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static com.mycompany.events.domain.DomainServices.issues;
import static com.mycompany.events.domain.DomainServices.repositories;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Juliano Silva
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebhookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private byte[] payload;

    @Before
    public void setUp() throws Exception {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("payload-event.json").toURI());
         payload = Files.readAllBytes(path);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldImportEvent() throws Exception {
        mockMvc.perform(
                post("/")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .header("X-GitHub-Event", "issues")
                        .content(payload))
                .andExpect(status().isOk());

        final Optional<Issue> issue = issues().findById(437933882L);

        assertThat("The issue should have been created",
                issue.get(), is(notNullValue()));
        assertThat("The issue id should have been present",
                issue.get().getId(), is(equalTo(437933882L)));

        final Optional<GitRepository> repository = repositories().findById(183736395L);
        assertThat("The repository should have been created",
                repository.get(), is(notNullValue()));
        assertThat("The repository id should have been present",
                repository.get().getId(), is(equalTo(183736395L)));

        final List<IssueEvent> events = issue.get().getEvents();
        assertThat("The issue should have events",
                events, is(not(empty())));

        final IssueEvent issueEvent = events.get(0);
        assertThat("The issue event should have been created",
                issueEvent.getId(), is(notNullValue()));
        assertThat("The issue event should have been created",
                issueEvent.getAction(), is(equalTo("opened")));
    }
}
