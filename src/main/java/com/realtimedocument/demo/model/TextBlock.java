package com.realtimedocument.demo.model;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TextBlock {
	@Id
	private String id;
	private String text;
	private String editorId;
	private Boolean contentEditable;
	
	@Builder
	public TextBlock(String id, String text, String editorId, Boolean contentEditable) {
		this.id = id;
		this.text = text;
		this.editorId = editorId;
		this.contentEditable = contentEditable;
	}
}
