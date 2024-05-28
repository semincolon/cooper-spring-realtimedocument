package com.realtimedocument.demo.repository;

import com.realtimedocument.demo.model.DocTextBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocTextBlockRepository extends JpaRepository<DocTextBlock, Long> {
    // 특정 문서 내의 블록 리스트 반환
    List<DocTextBlock> findByDocId(String docId);

    // 특정 문서 내의 블록 하나 반환
    DocTextBlock findByDocIdAndTextBlockId(String docId, String textBlockId);

    // 특정 문서 내의 블록 하나 삭제
    void deleteByDocIdAndTextBlockId(String docId, String textBlockId);
}
