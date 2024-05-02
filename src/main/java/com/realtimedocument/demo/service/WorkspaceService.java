package com.realtimedocument.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.realtimedocument.demo.model.TextBlock;
import com.realtimedocument.demo.model.User;
import com.realtimedocument.demo.model.Workspace;
import com.realtimedocument.demo.repository.WorkspaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

	private final WorkspaceRepository workspaceRepository;
	
	// 전체 워크스페이스 리스트 조회하여 리턴
	public List<Workspace> getWorkspaceList() {
		return this.workspaceRepository.findAll();
	}
	
	// 특정 워크스페이스 리턴
	public Workspace getWorkspace(String name) {
		return this.workspaceRepository.findByName(name);
	}
	
	// 워크스페이스 생성
	public void createWorkspace(String wName, User user) {
	    List<TextBlock> initialTextBlocks = new ArrayList<>();
	    for (int i = 1; i <= 5; i++) {
	        TextBlock textBlock = TextBlock.builder()
	        		.id("block" + i)
	        		.contentEditable(true)
	        		.build();
	        
	        initialTextBlocks.add(textBlock);
	    }

	    Workspace entity = Workspace.builder()
	            .name(wName)
	            .generator(user)
	            .member(new ArrayList<>())
	            .onlineMembers(new ArrayList<>())
	            .textBlocks(initialTextBlocks) // 초기값 설정
	            .build();
	    workspaceRepository.save(entity);
	}
	
	// 변경 내용 저장
	public void addMember(Workspace workspace, User user) {
		// MongoDB에서 해당 workspace를 가져옴
        Workspace existingWorkspace = workspaceRepository.findByName(workspace.getName());
       
	     // 가져온 workspace에 새로운 사용자 추가
	    existingWorkspace.getMember().add(user);
	    // 변경된 workspace를 저장 (업데이트)
	    workspaceRepository.save(existingWorkspace);
	}
	
	// 나간 멤버 삭제
	public void deleteMember(Workspace workspace, String userId) {
		Iterator<User> iterator = workspace.getMember().iterator();
		while (iterator.hasNext()) {
		    User member = iterator.next();
		    if (member.getId().equals(userId)) {
		        iterator.remove();
		        break;
		    }
		}
		workspaceRepository.save(workspace);
	}
	
	// 온라인 멤버 변경
	public void changeOnlineMember(Workspace workspace, String userId, Boolean add) {		
		if (add == true) {
			// 접속하는 요청이면 온라인 멤버에 추가
			if (!workspace.getOnlineMembers().contains(userId)) {
				workspace.getOnlineMembers().add(userId);
			}
		} else {
			// 나가는 요청이면 온라인 멤버에서 제거
			if (workspace.getOnlineMembers().contains(userId)) {
				workspace.getOnlineMembers().remove(userId);
			}
		}
		
		// DB에 반영
		workspaceRepository.save(workspace);
	}
	
	
	// TextBlock의 text값 수정
	public String editTextBlock(Workspace workspace, String editorId, String blockId, String text, String contentEditable) {
		if (contentEditable.equals("reset")) {
			for (TextBlock textBlock : workspace.getTextBlocks()) {
				if (textBlock.getEditorId() != null) {
					if (textBlock.getEditorId().equals(editorId)) {
						textBlock.setEditorId("");
						textBlock.setContentEditable(true);
						workspaceRepository.save(workspace);
						return textBlock.getId();
					}
				}
			}
		}
		
		for (TextBlock textBlock : workspace.getTextBlocks()) {
			if (textBlock.getId().equals(blockId)) {
				
				// contentEditable == 'true' : 편집 종료, contentEditable == 'false' : 편집 시작
				if (contentEditable.equals("true")) {
					textBlock.setText(text);
					textBlock.setEditorId("");
					textBlock.setContentEditable(true);
				} else {
					textBlock.setEditorId(editorId);
					textBlock.setContentEditable(false);
				}
				
				break;
			}
		}
		
		// DB에 반영
		workspaceRepository.save(workspace);
		return "";
	}
}
