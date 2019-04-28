package com.mycompany.events.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Juliano Silva
 */
@Repository
interface IssueEventRepository extends JpaRepository<IssueEvent, Long> {

    List<IssueEvent> findByIssue(final Issue issue);
}
