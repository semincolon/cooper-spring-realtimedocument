package com.realtimedocument.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.realtimedocument.demo.model.TextMessage;
import com.realtimedocument.demo.model.Workspace;
import com.realtimedocument.demo.service.WorkspaceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StompController {
	
	private final SimpMessagingTemplate messageTemplate;
	private final WorkspaceService workspaceService;

	@MessageMapping("/workspace/enter")
	public void enter(TextMessage message) {
		Workspace workspace = workspaceService.getWorkspace(message.getWorkspaceName());
		
		message.setTotalMembers(workspace.getMember());
		message.setOnlineMembers(workspace.getOnlineMembers());
		message.setMessage(message.getUser().getName() + "님이 " + message.getWorkspaceName() + "에 들어왔습니다. \n");
		messageTemplate.convertAndSend("/sub/workspace/" + message.getWorkspaceName(), message);
	}
	
	@MessageMapping("/workspace/out")
	public void out(TextMessage message) {
		Workspace workspace = workspaceService.getWorkspace(message.getWorkspaceName());
		
		workspaceService.changeOnlineMember(workspace, message.getUser().getId(), false);
		String blockId = workspaceService.editTextBlock(workspace, message.getUser().getId(), "", "", "reset");
		
		if (message.getMessage().equals("out")) {
			workspaceService.deleteMember(workspace, message.getUser().getId());	
		}
		
		message.setText("reset");
		message.setBlockId(blockId);
		message.setTotalMembers(workspace.getMember());
		message.setOnlineMembers(workspace.getOnlineMembers());
		message.setMessage(message.getUser().getName() + "님이 " + message.getWorkspaceName() + "에서 나갔습니다. \n");
		messageTemplate.convertAndSend("/sub/workspace/" + message.getWorkspaceName(), message);
	}
	
	
	@MessageMapping("/workspace/editTextBlock")
	public void editTextBlock(TextMessage message) {
		Workspace workspace = workspaceService.getWorkspace(message.getWorkspaceName());
		workspaceService.editTextBlock(workspace, message.getUser().getId(), message.getBlockId(), message.getText(), message.getMessage());
		
		messageTemplate.convertAndSend("/sub/workspace/" + message.getWorkspaceName(), message);
	}
}
