package kr.inha.technical.college.press.manager.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;

import kr.inha.technical.college.press.calendar.entity.entity;
import kr.inha.technical.college.press.manager.entity.Board;
import kr.inha.technical.college.press.manager.entity.Category;
import kr.inha.technical.college.press.manager.entity.SubCategory;
import kr.inha.technical.college.press.manager.repository.CategoryRepository;
import kr.inha.technical.college.press.manager.repository.SubCategoryRepository;
import kr.inha.technical.college.press.manager.service.CategorySevice;
import kr.inha.technical.college.press.manager.service.ManagerService;
import kr.inha.technical.college.press.member.constant.Role;
import kr.inha.technical.college.press.member.entity.Member;
import kr.inha.technical.college.press.member.service.MemberService;

@Controller
public class ManagerController {

	@Autowired
	ManagerService service;

	@Autowired
	MemberService memberService;

	@Autowired
	CategorySevice categorySevice;

	@Autowired
	SubCategoryRepository subCategory;

	// 관리자 게시판
	@GetMapping("/manager/manager")
	public String manager(Model model) {

		// 관리자 권한을 갖고 있는 유저만 불러와서 관리자 리스트 작성
		List<Member> member = memberService.findByRole(Role.ADMIN);
		model.addAttribute("member", member);
		return "manager/manager";
	}

	@PatchMapping("/manager/manager")
	public @ResponseBody ResponseEntity deleteAdmin(@RequestBody Map<String, String> email, Model model) {

		String user_email = email.get("email");
		System.out.println("===========>" + user_email);
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

		List<Category> mainCategory = categorySevice.findAll();
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
	@ResponseBody
	public ResponseEntity boardInsert(Board board, Principal principal) {
		String img = board.getContents();
		String data = img.replaceAll("data:image/png;base64,","");
		String i = img.split("src=")[1];
		System.out.println(img);
		String myimg = i.substring(1,i.indexOf("style=")-2);
		System.out.println(myimg);
		
		int idx = img.indexOf("base64,");
		String newImg = img.substring(idx+7);
		String newImgs = newImg.substring(0,newImg.indexOf("style="));
		String myImg = newImgs.substring(0,newImgs.length()-2);
		System.out.println("new img : "+myImg);
		
		
		byte[] testToByte = myImg.getBytes();

		Encoder encode = Base64.getEncoder();
		Decoder decode = Base64.getDecoder();

		// Base64 인코딩
		byte[] encodeByte = encode.encode(testToByte);

		// Base64 디코딩
		byte[] decodeByte = decode.decode(encodeByte);

		System.out.println("인코딩 전: " + myImg);
		System.out.println("인코딩: " + new String(encodeByte));
		System.out.println("디코딩: " + new String(decodeByte));

		board.setMember(memberService.findByEmail(principal.getName()).getName());
		board.setRegdate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
		board.setPhoto(myimg);
		service.boardInsert(board);
		System.out.println("boardInsert 실행 : " + board.getContents());
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	@RequestMapping(value = "/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request, Board board) {
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
		board.setPhoto(a);

		System.out.println("===================>image upload" + a);
		return a;
	}

}
