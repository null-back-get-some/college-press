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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import kr.inha.technical.college.press.manager.service.FileService;
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

	@Autowired
	FileService fileService;
	
	// ????????? ?????????
	@GetMapping("/manager/manager")
	public String manager(Model model) {

		// ????????? ????????? ?????? ?????? ????????? ???????????? ????????? ????????? ??????
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

	// ????????? ??????
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

	@GetMapping("/manager/newspaperWrite")
	public String pictureWrite(Model model ,Principal principal) {
		String username = memberService.findByEmail(principal.getName()).getName();
		model.addAttribute("username", username);
		return "manager/newspaperWrite";
	}
	
	@PostMapping("/manager/fileUpload")
	public String fileUpload(@RequestParam("file") List<MultipartFile> files, @RequestParam("title") String title,
			Principal principal) throws IOException {
		String username = memberService.findByEmail(principal.getName()).getName();
        for (MultipartFile multipartFile : files) {
            fileService.saveFile(multipartFile, title, username);
        }

        return "redirect:/";
		//return "manager/fileUpload";
	}

	
	@PostMapping("/manager/boardInsert")
	@ResponseBody
	public ResponseEntity boardInsert(Board board, Principal principal) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>board:"+board);
		String img = board.getContents();
		String i = "";
		try {
			i = img.split("src=")[1]; 
			if(i.length()==0) {
				System.out.println("????????????????????????");
			}else {
				 String myimg = i.substring(1,i.indexOf("style=")-2);
				 System.out.println("=========>myimg : "+myimg);
				 board.setPhoto(myimg);
			}
		}catch (Exception e) {
			System.out.println("??????");
		}
		 //String myimg = "";
		/*
		 * Pattern pattern =
		 * Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); Matcher match =
		 * pattern.matcher(img);
		 */
	
		/*
		 * Pattern pattern =
		 * Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img ?????? src ??????
		 * ??????????????? Matcher matcher = pattern.matcher(img);
		 */
        
		/*
		 * while(matcher.find()){
		 * System.out.println("===============>matcher : "+matcher.group(1)); myimg =
		 * matcher.group(1); }
		 */
        
		board.setMember(memberService.findByEmail(principal.getName()).getName());
		board.setRegdate(LocalDateTime.now().toString());
		
		service.boardInsert(board);
		System.out.println("boardInsert ?????? : " + board.getContents());
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	@PatchMapping("/manager/boardModify")
	@ResponseBody
	public ResponseEntity boardModify(Board board, Principal principal) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>board:"+board);
		System.out.println("?????? ?????????");
		String img = board.getContents();
		String i = "";
		try {
			i = img.split("src=")[1]; 
			if(i.length()==0) {
				System.out.println("????????????????????????");
			}else {
				String myimg = i.substring(1,i.indexOf("style=")-2);
				System.out.println("=========>myimg : "+myimg);
				board.setPhoto(myimg);
			}
		}catch (Exception e) {
			System.out.println("??????");
		}
		
		board.setMember(memberService.findByEmail(principal.getName()).getName());
		board.setRegdate(LocalDateTime.now().toString());
		
		service.boardInsert(board);
		System.out.println("boardmodify ?????? : " + board.getContents());
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	@RequestMapping(value = "/manager/boardDelete", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity deleteEvent(@RequestBody Board board) {
		System.out.println("?????? ??????");
		System.out.println(board);
		Long id = board.getNews();
		
		service.boardDelete(id);
		
		return new ResponseEntity<List<Board>>(HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request, Board board) {
		JsonObject jsonObject = new JsonObject();

		/*
		 * String fileRoot = "C:\\summernote_image\\"; // ??????????????? ????????? ????????????.
		 */

		// ??????????????? ??????
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		String fileRoot = contextRoot + "resources/fileupload/";

		String originalFileName = multipartFile.getOriginalFilename(); // ???????????? ?????????
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // ?????? ?????????
		String savedFileName = UUID.randomUUID() + extension; // ????????? ?????? ???

		File targetFile = new File(fileRoot + savedFileName);
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile); // ?????? ??????
			jsonObject.addProperty("url", "/summernote/resources/fileupload/" + savedFileName); // contextroot +
																								// resources + ????????? ??????
																								// ?????????
			jsonObject.addProperty("responseCode", "success");

		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile); // ????????? ?????? ??????
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		board.setPhoto(a);

		System.out.println("===================>image upload" + a);
		return a;
	}

}
