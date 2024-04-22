package com.realtimedocument.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.realtimedocument.demo.model.TextMessage;
import com.realtimedocument.demo.model.User;
import com.realtimedocument.demo.model.Workspace;
import com.realtimedocument.demo.service.UserService;
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
	
	@MessageMapping("/workspace/quit")
	public void quit(TextMessage message) {
		workspaceService.changeOnlineMember(message.getWorkspaceName(), message.getUser().getId(), false);
		
		Workspace workspace = workspaceService.getWorkspace(message.getWorkspaceName());
		
		message.setTotalMembers(workspace.getMember());
		message.setOnlineMembers(workspace.getOnlineMembers());
		message.setMessage(message.getUser().getName() + "님이 " + message.getWorkspaceName() + " - 오프라인 \n");
		messageTemplate.convertAndSend("/sub/workspace/" + message.getWorkspaceName(), message);
	}
	
	@MessageMapping("/workspace/out")
	public void out(TextMessage message) {
		workspaceService.changeOnlineMember(message.getWorkspaceName(), message.getUser().getId(), false);
		
		Workspace workspace = workspaceService.getWorkspace(message.getWorkspaceName());
		workspaceService.deleteMember(workspace, message.getUser());
		
		message.setTotalMembers(workspace.getMember());
		message.setOnlineMembers(workspace.getOnlineMembers());
		message.setMessage(message.getUser().getName() + "님이 " + message.getWorkspaceName() + "에서 나갔습니다. \n");
		messageTemplate.convertAndSend("/sub/workspace/" + message.getWorkspaceName(), message);
	}
}
