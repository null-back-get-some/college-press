package kr.inha.technical.college.press.calendar.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.inha.technical.college.press.calendar.entity.entity;
import kr.inha.technical.college.press.calendar.repository.CalendarRepository;
import kr.inha.technical.college.press.calendar.service.CalendarService;


@Controller
public class CalendarController {
	
	
	@Autowired
	CalendarService service;
	
	//db load
	@RequestMapping("/manager/calendar")
	public String calendar(Model model) {
		List<entity> calList = service.findAll();
		System.out.println("===================>"+calList);
		model.addAttribute("list", calList);
		return "manager/calendar";
	}
	
	
	////db insert
	@RequestMapping(value = "/manager/calendar", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity InsertEvent(@RequestBody List<entity> calDTOList) {
		System.out.println("calList insert data : "+ calDTOList);
		List<entity> calList = service.findAll();
		
		//중복 삭제
		for (int i = 0; i < calList.size(); i++) { //디비에 저장되어있는 리스트만큼
			System.out.println("==============>중복 검사 시작");
			for (int j = 0; j < calDTOList.size(); j++) { //뷰에서 가져온 리스트만큼
				if((calDTOList.get(j).getTitle().equals(calList.get(i).getTitle()))
						&&(calDTOList.get(j).getStart().equals(calList.get(i).getStart())
								&&(calDTOList.get(j).getEnd().equals(calList.get(i).getEnd())))) {
					System.out.println("중복된 이벤트 : "+calDTOList.get(j).getTitle());
					calDTOList.remove(j);
				}
			}
		}
		
		for (entity calendarDTO : calDTOList) { //뷰에서 가져온 리스트만큼
			//insert, db에 집어넣음
			service.calendarInsert(calendarDTO); // 저장
			System.out.println("========================>db insert 성공 : "+calendarDTO.getTitle());
		}
		return new ResponseEntity<List<entity>>(calDTOList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/manager/calendar", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity deleteEvent(@RequestBody List<entity> calDTOList) {
		System.out.println("삭제 시작");
		
		for (int i = 0; i < calDTOList.size(); i++) {
			System.out.println("삭제할 데이터 : "+calDTOList.get(i).getTitle()+", "+calDTOList.get(i).getId());
			service.calendarDelete(calDTOList.get(i).getId());
		}
		return new ResponseEntity<List<entity>>(calDTOList, HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/manager/calendar", method = RequestMethod.PATCH)
    public @ResponseBody ResponseEntity updateEvent(@RequestBody List<entity> calDTOList) {
		System.out.println("수정 시작");
		
		entity dto = new entity();
		
		for (int i = 0; i < calDTOList.size(); i++) {
			System.out.println("수정할 데이터 : "+calDTOList.get(i).getTitle()+", "+calDTOList.get(i).getId());
			dto.setId(calDTOList.get(i).getId());
			dto.setWriter(calDTOList.get(i).getWriter());
			dto.setTitle(calDTOList.get(i).getTitle());
			
			dto.setStart(calDTOList.get(i).getStart());
			dto.setEnd(calDTOList.get(i).getEnd());
			service.calendarUpdate(dto);
		}
		
		return new ResponseEntity<List<entity>>(calDTOList, HttpStatus.OK);
    }
	
}
