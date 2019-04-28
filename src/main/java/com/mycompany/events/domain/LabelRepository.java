package com.mycompany.events.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Juliano Silva
 */
@Repository
interface LabelRepository extends JpaRepository<Label, Long> {
}
