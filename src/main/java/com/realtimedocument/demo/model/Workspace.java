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
@Table(name = "tbl_workspaces")
public class Workspace {

	@Id
	@Column(name = "workspace_id")
	private String id;

	@Column(name = "workspace_name")
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WorkspaceDoc> workspaceDocs;

	@Builder
	public Workspace(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
