package com.realtimedocument.demo.service;

import com.realtimedocument.demo.model.Doc;
import com.realtimedocument.demo.model.DocTextBlock;
import com.realtimedocument.demo.model.TextBlock;
import com.realtimedocument.demo.repository.DocTextBlockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocTextBlockService {

    private final DocTextBlockRepository docTextBlockRepository;
    private final DocService docService;

    // 특정 워크스페이스 -> 문서 내 블록 리스트를 반환
    public List<DocTextBlock> getTextBlockList(String docId) {
        return this.docTextBlockRepository.findByDocId(docId);
    }

    // 특정 워크스페이스 -> 문서 내 블록 하나 반환
    public TextBlock getTextBlock(String docId, String textBlockId) {
        DocTextBlock doc = this.docTextBlockRepository.findByDocIdAndTextBlockId(docId, textBlockId);
        return doc.getTextBlock();
    }

    // 문서 - 블록 행 생성
    public void createDocTextBlock(String docId, TextBlock textBlock) {
        this.docTextBlockRepository.save(new DocTextBlock(docService.getDoc(docId), textBlock));
    }

    // 문서 - 블록 행 삭제
    public void deleteDocTextBlock(String docId, String textBlockId) {
        this.docTextBlockRepository.deleteByDocIdAndTextBlockId(docId, textBlockId);
    }

    // 문서 - 블록 position 1씩 증가
    public void increaseTextBlockPosition(String docId, int position) {
        // 특정 문서에 해당하는 블록들 조회
        List<DocTextBlock> docTextBlocks = docTextBlockRepository.findByDocId(docId);

        // DB상의 position이 매개변수 position보다 크거나 같은 블록들의 position을 1씩 증가
        for (DocTextBlock docTextBlock : docTextBlocks) {
            if (docTextBlock.getTextBlock().getPosition() >= position) {
                docTextBlock.getTextBlock().setPosition(docTextBlock.getTextBlock().getPosition() + 1);
            }
        }

        // 변경된 블록들 저장
        docTextBlockRepository.saveAll(docTextBlocks);
    }

    // 문서 - 블록 position 1씩 감소
    public void decreaseTextBlockPosition(String docId, int position) {
        // 특정 문서에 해당하는 블록들 조회
        List<DocTextBlock> docTextBlocks = docTextBlockRepository.findByDocId(docId);

        // DB상의 position이 매개변수 position보다 크거나 같은 블록들의 position을 1씩 감소
        for (DocTextBlock docTextBlock : docTextBlocks) {
            if (docTextBlock.getTextBlock().getPosition() >= position) {
                docTextBlock.getTextBlock().setPosition(docTextBlock.getTextBlock().getPosition() - 1);
            }
        }
    }

}