package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;

public class CovidForm implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		
		return "product/covid19.jsp";
	}

}
