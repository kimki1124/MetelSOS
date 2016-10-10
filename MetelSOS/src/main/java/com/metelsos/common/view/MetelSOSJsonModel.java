package com.metelsos.common.view;

import org.springframework.web.servlet.ModelAndView;

public class MetelSOSJsonModel extends ModelAndView{
	public MetelSOSJsonModel(Object modelObject){
		super("jsonView", "AJAX_MODEL", modelObject);
	}
}
