package com.realtimedocument.demo.repository;

import com.realtimedocument.demo.model.WorkspaceDoc;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceDocRepository extends JpaRepository<WorkspaceDoc, Long> {
    // 특정 워크스페이스에 포함된 문서 목록 반환
    List<WorkspaceDoc> findByWorkspaceId(String workspaceId);

    // 워크스페이스 내 문서를 삭제하는 쿼리. 삭제는 트랜잭션이 필요하므로 어노테이션 선언
    @Transactional
    void deleteByWorkspaceIdAndDocId(String workspaceId, String docId);
}
