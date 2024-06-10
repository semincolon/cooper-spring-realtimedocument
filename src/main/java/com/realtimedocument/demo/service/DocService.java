package com.realtimedocument.demo.service;

import com.realtimedocument.demo.model.Doc;
import com.realtimedocument.demo.model.Workspace;
import com.realtimedocument.demo.model.WorkspaceDoc;
import com.realtimedocument.demo.repository.DocRepository;
import com.realtimedocument.demo.repository.WorkspaceDocRepository;
import com.realtimedocument.demo.repository.WorkspaceRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocService {
    private final WorkspaceService workspaceService;
    private final WorkspaceDocRepository workspaceDocRepository;
    private final WorkspaceDocService workspaceDocService;
    private final DocRepository docRepository;

    // 특정 워크스페이스의 문서 목록 반환
    public List<WorkspaceDoc> getDocList(String wsId) {
        return workspaceDocService.getDocList(wsId);
    }

    // 특정 문서 리턴
    public Doc getDoc(String docId) {
        Optional<Doc> doc = this.docRepository.findById(docId);
        return doc.orElse(null);
    }

    // 문서 생성
    public void createDocument(HttpServletRequest request, String wsId, String docId) {
        Doc entity = Doc.builder()
                .id(docId)
                .name(request.getParameter("doc_name"))
                .build();

        docRepository.save(entity);
        workspaceDocRepository.save(new WorkspaceDoc(workspaceService.getWorkspace(wsId), entity));
    }
}
