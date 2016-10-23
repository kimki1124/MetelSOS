package com.metelsos.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter{
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 전처리기. 컨트롤러가 호출되기 전에 실행됨
		log.info("====================== START ======================");
		log.info(" Request URI \t:     "+ request.getRequestURI());
		if("/metelSOS/login.do".equals(request.getRequestURI()) || "/metelSOS/engineerRegister.do".equals(request.getRequestURI())
				|| "/metelSOS/engineer/validateEngineerId.do".equals(request.getRequestURI()) || "/metelSOS/setItemForRegisterForm.do".equals(request.getRequestURI())
				|| "/metelSOS/customerValidateId.do".equals(request.getRequestURI()) || "/metelSOS/customerRegister.do".equals(request.getRequestURI())
				|| "/metelSOS/findId.do".equals(request.getRequestURI()) || "/metelSOS/findPasswdPageMove.do".equals(request.getRequestURI())
				|| "/metelSOS/sendTempPasswd.do".equals(request.getRequestURI())){
			//이 조건의 URI는 세션 체크 확인을 할 필요가 없으므로 바로 true 리턴 
			return true;
		}
		
		
		try{	
			if(request.getSession().getAttribute("SESSION_LOGIN_USER_ID") == null){
				//로그인하지 않은 경우 로그인 페이지로 이동
				response.sendRedirect("/metelSOS/login.jsp");
				return false;
			}
		}catch(Exception e){
			log.error(e,e);
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//  후처리기. 컨트롤러에서 클라이언트로 응답하기 전에 실행됨
		log.info("====================== END ======================");
	}
	
	
}
