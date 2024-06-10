package com.realtimedocument.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_doc_textblock")
public class DocTextBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doc_id")
    private Doc doc;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "textblock_id")
    private TextBlock textBlock;

    @Builder
    public DocTextBlock(Doc doc, TextBlock textBlock) {
        this.doc = doc;
        this.textBlock = textBlock;
    }
}
