package com.realtimedocument.demo.service;

import com.realtimedocument.demo.model.DocTextBlock;
import com.realtimedocument.demo.model.TextBlock;
import com.realtimedocument.demo.repository.TextBlockRepository;
//import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TextBlockService {
    private final TextBlockRepository textBlockRepository;
    private final DocTextBlockService docTextBlockService;

    // 문서 내 블록 리스트 반환
    public List<TextBlock> getTextBlockList(String docId) {
        // 1차적으로 DocTextBlock 리스트를 조회
        List<DocTextBlock> docTextBlocks = docTextBlockService.getTextBlockList(docId);

        // TextBlock ID 리스트 추출
        List<String> textBlockIds = docTextBlocks.stream()
                .map(dtb -> dtb.getTextBlock().getId())
                .toList();

        // TextBlock ID 리스트를 기준으로 TextBlock 조회 및 정렬
        return this.textBlockRepository.findByIdInOrderByPositionAsc(textBlockIds);
    }

    // 문서 내 특정 블록 반환
    public TextBlock getTextBlock(String docId, String textBlockId) {
        return this.docTextBlockService.getTextBlock(docId, textBlockId);
    }

    // 문서 내 새 블록 생성
    @Transactional(propagation = Propagation.NESTED)
    public void createTextBlock(String id, String text, int position, String docId) {
        TextBlock textBlock = TextBlock.builder()
                .id(id)
                .text(text)
                .position(position)
                .build();
        this.textBlockRepository.save(textBlock);
        this.docTextBlockService.createDocTextBlock(docId, textBlock);
        this.docTextBlockService.increaseTextBlockPosition(docId, position); // 기존 블록의 position을 1씩 증가
    }

    // 기존 블록의 텍스트 갱신
    @Transactional(propagation = Propagation.NESTED)
    public void updateTextBlock(String id, String text, String docId) {
        TextBlock tb = getTextBlock(docId, id);
        tb.setText(text);
        this.textBlockRepository.save(tb);
    }

    // 기존 블록 삭제
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteTextBlock(String id, int position, String docId) {
        this.docTextBlockService.deleteDocTextBlock(docId, id);
        this.docTextBlockService.decreaseTextBlockPosition(docId, position); // 기존 블록의 position을 1씩 감소
    }
}
