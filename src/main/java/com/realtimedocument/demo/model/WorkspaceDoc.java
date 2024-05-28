package com.realtimedocument.demo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_workspace_docs")
public class WorkspaceDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "doc_id")
    private Doc doc;

    @Builder
    public WorkspaceDoc(Workspace workspace, Doc doc) {
        this.workspace = workspace;
        this.doc = doc;
    }
}
