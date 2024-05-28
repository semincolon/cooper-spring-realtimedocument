package com.realtimedocument.demo.controller;

import java.util.List;

import com.realtimedocument.demo.model.Doc;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.realtimedocument.demo.model.User;
import com.realtimedocument.demo.model.Workspace;
import com.realtimedocument.demo.service.UserService;
import com.realtimedocument.demo.service.WorkspaceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cooper-docs")
@RequiredArgsConstructor
public class WorkspaceController {

	private final WorkspaceService workspaceService;
	private final UserService userService;

	// 워크스페이스 전체 목록 반환
	@GetMapping("/workspace")
	public List<Workspace> getWorkspace() {
		return workspaceService.getWorkspaceList();
	}

	// 워크스페이스 생성
	@PostMapping("/workspace/{id}")
	public List<Workspace> createWorkspace(HttpServletRequest request, @PathVariable("id") String id) {
		workspaceService.createWorkspace(request, id);
		return getWorkspace();
	}

	// 워크스페이스 삭제
	@DeleteMapping("/workspace/{id}")
	public List<Workspace> deleteWorkspace(@PathVariable("id") String id) {
		workspaceService.deleteWorkspace(id);
		return workspaceService.getWorkspaceList();
	}


	//////////////////

	@RequestMapping("/list")
	public String list(@ModelAttribute User user, Model model) {
		userService.checkUser(user); // 유저가 있는지 없는지 확인
		
		List<Workspace> workspaceList = workspaceService.getWorkspaceList();
		model.addAttribute("user", user);
		model.addAttribute("workspaceList", workspaceList);		
		return "workspace/list";
	}
	
	@PostMapping("/create")
	public String create(@RequestParam("userId") String userId, Model model) {
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		return "workspace/create";
	}
	
//	@PostMapping("/create-complete")
//	public String createComplete(@RequestParam("wName") String wName, @RequestParam("userId") String userId, RedirectAttributes rttr) {
//		User user = userService.getUser(userId);
//		workspaceService.createWorkspace(wName, user);
//		rttr.addFlashAttribute("user", user);
//		return "redirect:/workspace/list";
//	}
	
//	@PostMapping("/list/{wName}")
//	public String detail(@PathVariable("wName") String wName, @RequestParam("userId") String userId, Model model) {
//		Workspace workspace = workspaceService.getWorkspace(wName);
//		User user = userService.getUser(userId);
//
//		workspaceService.changeOnlineMember(workspace, userId, true);
//
//		// 워크스페이스에 들어가려는 멤버가 구성원에 추가되어 있지 않다면 추가
//		boolean present = false;
//		for (User temp : workspace.getMember()) {
//			if (user.getName().equals(temp.getName())) {
//				present = true;
//				break;
//			}
//		}
//		if (!present) {
//			workspaceService.addMember(workspace, user);
//			// 새 맴버 추가했으니 workspace 갱신
//			workspace = workspaceService.getWorkspace(wName);
//		}
//		System.out.println(String.format("%s 입장!", user.getName()));
//
//		model.addAttribute(workspace);
//		model.addAttribute(user);
//		return "workspace/detail";
//	}
}
