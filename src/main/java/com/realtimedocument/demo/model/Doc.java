package com.realtimedocument.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_documents")
public class Doc {
    @Id
    @Column(name = "document_id")
    private String id;

    @Column(name = "document_name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "doc", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkspaceDoc> workspaceDocs;

    @JsonIgnore
    @OneToMany(mappedBy = "doc", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocTextBlock> docTextBlocks;

    @Builder
    public Doc(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
