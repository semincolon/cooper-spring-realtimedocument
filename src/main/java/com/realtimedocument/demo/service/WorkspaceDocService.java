package com.realtimedocument.demo.service;

import com.realtimedocument.demo.model.Doc;
import com.realtimedocument.demo.model.WorkspaceDoc;
import com.realtimedocument.demo.repository.WorkspaceDocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceDocService {

    private final WorkspaceDocRepository workspaceDocRepository;

    // 특정 워크스페이스의 문서 리스트 조회
    public List<WorkspaceDoc> getDocList(String workspaceId) {
        // 특정 워크스페이스에 있는 WorkspaceDoc 목록을 가져옴
        List<WorkspaceDoc> workspaceDocs = workspaceDocRepository.findByWorkspaceId(workspaceId);

        // TextBlock ID 리스트 추출
        List<String> textBlockIds = workspaceDocs.stream()
                .map(dtb -> dtb.getDoc().getId())
                .toList();

        return workspaceDocRepository.findByWorkspaceId(workspaceId);
    }

    // 특정 워크스페이스 내의 문서 삭제
    public void deleteDocument(String workspaceId, String documentId) {
        workspaceDocRepository.deleteByWorkspaceIdAndDocId(workspaceId, documentId);
    }
}
