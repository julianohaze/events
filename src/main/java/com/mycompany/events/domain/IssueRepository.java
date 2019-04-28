package com.mycompany.events.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Juliano Silva
 */
interface IssueRepository extends JpaRepository<Issue, Long> {
}
