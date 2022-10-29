package kr.inha.technical.college.press.manager.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import kr.inha.technical.college.press.manager.entity.Board;
import kr.inha.technical.college.press.manager.entity.Category;
import kr.inha.technical.college.press.manager.entity.SubCategory;
import kr.inha.technical.college.press.manager.repository.CategoryRepository;
import kr.inha.technical.college.press.manager.repository.SubCategoryRepository;
import kr.inha.technical.college.press.manager.service.BoardService;
import kr.inha.technical.college.press.member.constant.Role;
import kr.inha.technical.college.press.member.entity.Member;
import kr.inha.technical.college.press.member.service.MemberService;

@Controller
public class ManagerController {

	@Autowired
	BoardService service;

	@Autowired
	MemberService memberService;
	@Autowired
	CategoryRepository category;

	@Autowired
	SubCategoryRepository subCategory;

	// 관리자 게시판
	@GetMapping("/manager/manager")
	public String manager(Model model) {
		
		//관리자 권한을 갖고 있는 유저만 불러와서 관리자 리스트 작성
		List<Member> member = memberService.findByRole(Role.ADMIN);
		model.addAttribute("member", member);
		return "manager/manager";
	}
	
	@PatchMapping("/manager/manager")
	public @ResponseBody ResponseEntity deleteAdmin(@RequestBody Map<String, String> email, Model model) {

		String user_email = email.get("email");
		System.out.println("===========>"+user_email);
		memberService.deleteAdmin(user_email);
		
		return new ResponseEntity<Map>(email, HttpStatus.OK);
	}

	@PostMapping("/manager/manager")
	public @ResponseBody ResponseEntity InsertAdmin(@RequestBody Map<String, String> email, Model model) {

		System.out.println(email.get("email"));

		String emails = email.get("email");
		Member member = memberService.findByEmail(emails);
		member.setRole(Role.ADMIN);
		memberService.addAdmin(member);

		return new ResponseEntity<Map>(email, HttpStatus.OK);
	}
	
	// 게시판 작성
	@RequestMapping("/manager/boardWrite")
	public String boardWrite(Model model, Principal principal) {
		System.out.println("===========>username : " + principal.getName());

		String username = memberService.findByEmail(principal.getName()).getName();

		List<Category> mainCategory = category.findAll();
		List<SubCategory> sub_category = subCategory.findAll();

		model.addAttribute("username", username);
		model.addAttribute("mainCateList", mainCategory);
		model.addAttribute("subCateList", sub_category);
		System.out.println(mainCategory);
		System.out.println(sub_category);
		return "manager/boardWrite";
	}

	@GetMapping("/manager/pictureWrite")
	public String pictureWrite() {
		return "manager/pictureWrite";
	}

	@PostMapping("/manager/boardInsert")
	public String boardInsert(Board board, Principal principal, HttpServletRequest httpServletRequest) {
		board.setMember(memberService.findByEmail(principal.getName()).getName());
		board.setRegdate(LocalDateTime.now());
		service.boardInsert(board);
		System.out.println("boardInsert 실행 : " + board.getContents());
		return "/manager/manager";
	}

	@RequestMapping(value = "/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request) {
		JsonObject jsonObject = new JsonObject();

		/*
		 * String fileRoot = "C:\\summernote_image\\"; // 외부경로로 저장을 희망할때.
		 */

		// 내부경로로 저장
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		String fileRoot = contextRoot + "resources/fileupload/";

		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자
		String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명

		File targetFile = new File(fileRoot + savedFileName);
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
			jsonObject.addProperty("url", "/summernote/resources/fileupload/" + savedFileName); // contextroot +
																								// resources + 저장할 내부
																								// 폴더명
			jsonObject.addProperty("responseCode", "success");

		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		System.out.println("===================>image upload" + a);
		return a;
	}

}
