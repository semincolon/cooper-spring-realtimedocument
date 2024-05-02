package com.realtimedocument.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.realtimedocument.demo.model.User;
import com.realtimedocument.demo.model.Workspace;
import com.realtimedocument.demo.repository.WorkspaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

	private final WorkspaceRepository workspaceRepository;
	private final UserService userService;
	
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
		Workspace entity = Workspace.builder()
				.name(wName)
				.generator(user)
				.member(new ArrayList<>())
				.onlineMembers(new ArrayList<>())
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
	public void changeOnlineMember(String workspaceName, String userId, Boolean add) {
		Workspace existingWorkspace = workspaceRepository.findByName(workspaceName);
		
		if (add == true) {
			// 접속하는 요청이면 온라인 멤버에 추가
			if (!existingWorkspace.getOnlineMembers().contains(userId)) {
				existingWorkspace.getOnlineMembers().add(userId);
			}
		} else {
			// 나가는 요청이면 온라인 멤버에서 제거
			if (existingWorkspace.getOnlineMembers().contains(userId)) {
				existingWorkspace.getOnlineMembers().remove(userId);
			}
		}
		
		// DB에 반영
		workspaceRepository.save(existingWorkspace);
	}
}
