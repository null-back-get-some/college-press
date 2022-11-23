package kr.inha.technical.college.press.manager.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.inha.technical.college.press.manager.entity.FileEntity;
import kr.inha.technical.college.press.manager.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class FileService {

	@Value("${file.upload.location}")
	private String fileDir;
	
	private final FileRepository fileRepository;
	
	public Long saveFile(MultipartFile files, String title, String member) throws IOException {
		if(files.isEmpty()) {
			return null;
		}
		//원래 파일 이름 추출
		String originalName = files.getOriginalFilename();
		log.info("original name : "+originalName);
		
		//파일 이름으로 쓸 uuid 생성
		String uuid = UUID.randomUUID().toString();
		
		//확장자 추출(ex .png)
		String extension = originalName.substring(originalName.lastIndexOf("."));
		log.info("extension name : "+extension);
		//uuid와 확장자 결합
		String savedName = uuid + extension;
		
		//파일을 불러올 때 사용할 파일 경로
		String savePath = fileDir + savedName;
		log.info("savePath name : "+savePath);
		
		
		
		FileEntity file = FileEntity.builder()
				.title(title)
				.createTime(LocalDateTime.now())
				.member(member)
				.originalFileName(originalName)
				.savedName(savedName)
				.savedPath(savePath)
				.build();
		
		//실제로 로컬에 uuid를 파일명으로 저장
		files.transferTo(new File(savedName));
		
		
		//데이터베이스에 파일 정보 저장
		FileEntity savedFile = fileRepository.save(file);
		
		return savedFile.getId();
	}
	
	public List<FileEntity> findAll(){
		List<FileEntity> board = fileRepository.findAll();
		return board;
	}
	
	public Optional<FileEntity> findbyId(Long id){
		Optional<FileEntity> board = fileRepository.findById(id);
		return board;
	}
}
