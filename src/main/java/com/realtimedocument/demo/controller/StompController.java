package com.realtimedocument.demo.controller;

import com.realtimedocument.demo.model.WorkspaceDoc;
import com.realtimedocument.demo.service.TextBlockService;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.realtimedocument.demo.model.TextMessage;
import com.realtimedocument.demo.model.Workspace;
import com.realtimedocument.demo.service.WorkspaceService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StompController {
	
	private final SimpMessagingTemplate messageTemplate;
	private final WorkspaceService workspaceService;
	private final TextBlockService textBlockService;

	// 워크스페이스별 웹소켓 연결 :: 워크스페이스 삭제 + 문서 생성, 삭제
	@MessageMapping("/workspace")
	@SendTo("/sub/workspace")
	public String docCreateOrDelete(String message) {
		return message;
	}


	// 워크스페이스 -> 문서별 웹소켓 연결 :: 텍스트 블록 생성, 수정, 삭제
	@MessageMapping("/workspace/{wsId}/document/{docId}")
	@SendTo("/sub/workspace/{wsId}/document/{docId}")
	public String createOrUpdate(@DestinationVariable("docId") String docId ,String message) {
		JSONObject object = new JSONObject(message);
		String type = object.getString("type");
		String id = object.getJSONObject("block").getString("id");
		String text = object.getJSONObject("block").getString("text");
		int position = object.getInt("position");

		// 새로운 블럭이 추가되는 경우
		if (type.equals("new")) {
			textBlockService.createTextBlock(id, text, position, docId);
		}
		// 기존 블락의 값이 갱신되는 경우
		else if (type.equals("old")) {
			textBlockService.updateTextBlock(id, text, docId);
		}
		// 기존 블락을 삭제하는 경우
		else if (type.equals("del")) {
			textBlockService.deleteTextBlock(id, position, docId);
		}
		return message;
	}

}
