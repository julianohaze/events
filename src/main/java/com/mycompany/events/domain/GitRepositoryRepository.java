package com.mycompany.events.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Juliano Silva
 */
// what a nice name?
@Repository
public interface GitRepositoryRepository extends JpaRepository<GitRepository, Long> {
}
