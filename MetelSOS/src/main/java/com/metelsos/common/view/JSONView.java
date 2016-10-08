package com.metelsos.common.view;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONView extends AbstractView{
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean isArray = false;
		
		if(logger.isInfoEnabled()){
			logger.info("Starting JSON rendering of "+this.getBeanName());
		}
		
		Object model = null;
		
		if(logger.isDebugEnabled()){
			logger.debug("Received Object");
		}
		
		model = map.get("AJAX_MODEL");
		
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		
		if(model != null){
			if(model instanceof Collection<?>){
				isArray = true;
				jsonArray = JSONArray.fromObject(model);
			}else{
				jsonObject = JSONObject.fromObject(model);
			}
		}else{
			jsonObject = JSONObject.fromObject(map);
		}
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			response.setContentType("text/xml;charset=utf-8");
		}
		
		response.setHeader("Cache-Control", "no-cache");
		
		if(isArray){
			response.getWriter().write(jsonArray.toString());
			if(logger.isInfoEnabled()){
				logger.debug("JSON ARRAY !!!!!");
				logger.info("content type : "+response.getContentType());
			}
		}else{
			response.getWriter().write(jsonObject.toString());
			if(logger.isInfoEnabled()){
				logger.debug("JSON OBJECT !!!!!");
				logger.info("content type : "+response.getContentType());
			}
		}
	}
}
