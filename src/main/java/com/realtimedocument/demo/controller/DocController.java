package com.realtimedocument.demo.controller;

import com.realtimedocument.demo.model.Doc;
import com.realtimedocument.demo.model.WorkspaceDoc;
import com.realtimedocument.demo.service.DocService;
import com.realtimedocument.demo.service.WorkspaceDocService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cooper-docs")
@RequiredArgsConstructor
public class DocController {

    private final WorkspaceDocService workspaceDocService;
    private final DocService docService;

    // 특정 워크스페이스의 문서 전체 리스트
    @GetMapping("/workspace/{workspaceId}/document")
    public List<WorkspaceDoc> getDocList(@PathVariable("workspaceId") String workspaceId) {
        return docService.getDocList(workspaceId);
    }

    // 문서 생성
    @PostMapping("/workspace/{workspaceId}/document/{docId}")
    public List<WorkspaceDoc> createDocument(HttpServletRequest request , @PathVariable("workspaceId") String workspaceId, @PathVariable("docId") String docId) throws JSONException {
        docService.createDocument(request, workspaceId, docId);
		return workspaceDocService.getDocList(workspaceId);
    }

    // 문서 삭제
	@DeleteMapping("/workspace/{workspaceId}/document/{documentId}")
	public List<WorkspaceDoc> deleteDocument(@PathVariable("workspaceId") String workspaceId, @PathVariable("documentId") String documentId) {
		workspaceDocService.deleteDocument(workspaceId, documentId);
        return workspaceDocService.getDocList(workspaceId);
	}
}
