package com.metelsos.common.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
/**
 * 
* <pre>
* com.metelsos.common.util
*   |_ MetelSOSUtil.java
* </pre>
* 
* Desc : 공통 유틸 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 3:05:39
* @Version :
 */
public class MetelSOSUtil {
	
	private Log log = LogFactory.getLog(MetelSOSUtil.class);
	
	//파일이 저장될 경로
	private static final String noticeFilePath = "C:\\dev\\file\\notice\\";
	
	/**
	 * 
	 * Desc : 비밀번호 분실 시 임시 비밀번호 생성하는 메서드
	 * @Method Name : generateTempPasswd
	 * @param type - 임시 비밀번호 타입
	 * @param cnt - 임시 비밀번호 사이즈
	 * @return 임시 비밀번호
	 */
	public String generateTempPasswd(String type, int cnt){
		StringBuffer strPwd = new StringBuffer();
		char str[] = new char[1];
		
		// 특수기호 포함
		if (type.equals("P")) {
			for (int i = 0; i < cnt; i++) {
				str[0] = (char) ((Math.random() * 94) + 33);
				strPwd.append(str);
			}
		// 대문자로만
		} else if (type.equals("A")) {
			for (int i = 0; i < cnt; i++) {
				str[0] = (char) ((Math.random() * 26) + 65);
				strPwd.append(str);
			}
		// 소문자로만
		} else if (type.equals("S")) {
			for (int i = 0; i < cnt; i++) {
				str[0] = (char) ((Math.random() * 26) + 97);
				strPwd.append(str);
			}
		// 숫자형으로
		} else if (type.equals("I")) {
			int strs[] = new int[1];
			for (int i = 0; i < cnt; i++) {
				strs[0] = (int) (Math.random() * 9);
				strPwd.append(strs[0]);
			}
		// 소문자, 숫자형
		} else if (type.equals("C")) {
			Random rnd = new Random();
			for (int i = 0; i < cnt; i++) {
				if (rnd.nextBoolean()) {
					strPwd.append((char) ((int) (rnd.nextInt(26)) + 97));
				} else {
					strPwd.append((rnd.nextInt(10)));
				}
			}
		}
		
		return strPwd.toString();
	}
	
	/**
	 * 
	 * Desc : 메일 전송 메서드
	 * @Method Name : sendEmail
	 * @param receiver - 수신자 메일 주소
	 * @param title - 메일 제목
	 * @param content - 메일 내용
	 */
	public void sendEmail(String receiver, String title, String content) {
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true");     // gmail은 무조건 true 고정
        p.put("mail.smtp.host", "smtp.gmail.com");      // smtp 서버 주소
        p.put("mail.smtp.auth","true");                 // gmail은 무조건 true 고정
        p.put("mail.smtp.port", "587");                 // gmail 포트
        
        Authenticator auth = new MyAuthentication();
        
        //session 생성 및  MimeMessage생성
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);
         
        try{
            //편지보낸시간
            msg.setSentDate(new Date());
             
            InternetAddress from = new InternetAddress() ;
             
             
            from = new InternetAddress("admin<mymetelsos@gmail.com>");
             
            // 이메일 발신자
            msg.setFrom(from);
             
             
            // 이메일 수신자
            InternetAddress to = new InternetAddress(receiver);
            msg.setRecipient(Message.RecipientType.TO, to);
             
            // 이메일 제목
            msg.setSubject(title, "UTF-8");
             
            // 이메일 내용 
            msg.setText(content, "UTF-8");
             
            // 이메일 헤더 
            msg.setHeader("content-Type", "text/html");
             
            //메일보내기
            javax.mail.Transport.send(msg);
             
        }catch (AddressException addr_e) {
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) {
            msg_e.printStackTrace();
        }
	}
	
	/**
	 * 현재 시간 String으로 리턴
	 * @param pattern - date 패턴 
	 * @return
	 */
	public String currDatetoString(String pattern){
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat(pattern);

		return transFormat.format(date);
	}
	
	/**
	 * String으로 되어있는 특정 시간을 패턴 변경해서 리턴
	 * @param objectDate - 대상 String
	 * @param beforePattern - 기존의 date 패턴
	 * @param afterPattern - 변경할 date 패턴
	 * @return
	 */
	public String changeDatePattern(String objectDate, String beforePattern, String afterPattern){
		SimpleDateFormat transFormat = new SimpleDateFormat(beforePattern);
		Date date = null;
		
		try {
			date = transFormat.parse(objectDate);
		} catch (ParseException e) {
			log.error(e,e);
		}
		transFormat.applyPattern(afterPattern);
		
		return transFormat.format(date);
	}
	
	/**
	 * 32글자의 랜덤한 문자열(숫자포함)을 만들어서 반환
	 * @return
	 */
	public String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
	
	/**
	 * 파일을 로컬 경로에 저장하고 파일 정보를 Map에 담아서 Map List를 반환함
	 * @param map - 파라미터를 담고있는 Map 객체
	 * @param request - 파일 데이터를 담고있는 HttpServletRequest 객체
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,Object>> parseInsertFileInfo(HashMap<String,Object> paramMap, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
         
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        HashMap<String, Object> listMap = null; 
         
        String boardIdx = (String)paramMap.get("boardNum");
         
        File file = new File(noticeFilePath);
        if(file.exists() == false){
            file.mkdirs();
        }
         
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            if(multipartFile.isEmpty() == false){
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = getRandomString() + originalFileExtension;
                 
                file = new File(noticeFilePath + storedFileName);
                multipartFile.transferTo(file);
                 
                listMap = new HashMap<String,Object>();
                listMap.put("BOARD_IDX", boardIdx);
                listMap.put("ORIGINAL_FILE_NAME", originalFileName);
                listMap.put("STORED_FILE_NAME", storedFileName);
                listMap.put("FILE_SIZE", multipartFile.getSize());
                listMap.put("CREA_ID", paramMap.get("userName"));
                list.add(listMap);
            }
        }
        return list;
    }
}