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
@Table(name = "tbl_textblocks")
public class TextBlock {
	@Id
	@Column(name = "textblock_id")
	private String id;

	@Column(name = "textblock_text")
	private String text;

	@Column(name = "position")
	private int position;

	@JsonIgnore
	@OneToMany(mappedBy = "textBlock", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DocTextBlock> docTextBlock;
	
	@Builder
	public TextBlock(String id, String text, int position) {
		this.id = id;
		this.text = text;
		this.position = position;
	}
}
