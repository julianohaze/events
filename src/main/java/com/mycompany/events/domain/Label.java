package com.mycompany.events.domain;

import com.mycompany.events.domain.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.function.Supplier;

import static com.mycompany.events.domain.DomainRegistry.issues;
import static com.mycompany.events.domain.DomainRegistry.labels;

/**
 * @author Juliano Silva
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
class Label {
    @Id
    private Long id;
    private String nodeId;
    private String url;
    private String name;
    private String color;
    private boolean isDefault;
    @ManyToOne
    private Issue issue;

    private static final Supplier<NotFoundException> ISSUE_NOT_FOUND =
            () -> new NotFoundException("Issue not found");

    static Label create(final Long issueId,
                        final com.mycompany.events.domain.command.dto.Label label) {
        final Label lbl = new Label();
        lbl.id = label.getId();
        lbl.nodeId = label.getNodeId();
        lbl.url = label.getUrl();
        lbl.name = label.getName();
        lbl.color = label.getColor();
        lbl.isDefault = label.isDefault();
        lbl.issue = issues()
                .findById(issueId)
                .orElseThrow(ISSUE_NOT_FOUND);
        return labels().save(lbl);
    }
}
