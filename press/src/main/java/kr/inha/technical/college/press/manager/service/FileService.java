package kr.inha.technical.college.press.manager.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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
		
		
		//파일이 업로드될 폴더 생성
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "files/"+current.format(format);
		
		File myfile = new File(path);
		if (myfile.exists()==false) {
			myfile.mkdirs();
		}
		
		String originalFileExtension = null;
		if(files.isEmpty()==false) {
			String contentType = files.getContentType();
			if(ObjectUtils.isEmpty(contentType)) {
				break;
				
			}else {
				if(contentType.contains("image/jpeg")) {
					originalFileExtension = ".jpg";
				}else if(contentType.contains("image/png")){
					originalFileExtension = ".png";
				}
				else if(contentType.contains("image/gif")){
					originalFileExtension = ".gif";
				}else {
					break;
				}
			}
			
			String newFileName = Long.toString(System.nanoTime())+originalFileExtension;
			
			myfile = new File(path+"/"+newFileName);
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	public FileEntity findbyEntity(Long id) {
		return fileRepository.findById(id).orElse(null);
	}
}
