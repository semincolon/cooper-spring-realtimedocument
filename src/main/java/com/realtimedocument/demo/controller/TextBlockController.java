package com.realtimedocument.demo.controller;

import com.realtimedocument.demo.model.DocTextBlock;
import com.realtimedocument.demo.model.TextBlock;
import com.realtimedocument.demo.service.DocTextBlockService;
import com.realtimedocument.demo.service.TextBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cooper-docs")
@RequiredArgsConstructor
public class TextBlockController {

    private final TextBlockService textBlockService;

    // 워크스페이스 -> 문서 내 블록 리스트를 반환
    @GetMapping("/workspace/{wsId}/document/{docId}")
    public List<TextBlock> getTextBlocks(@PathVariable("docId") String docId) {
        return textBlockService.getTextBlockList(docId);
    }
}
