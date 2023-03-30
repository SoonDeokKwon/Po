package co.prod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.prod.common.Control;

public class MapForm implements Control {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		String lat = request.getParameter("lat");
		String lng = request.getParameter("lng");
		String name = request.getParameter("name");
		
		request.setAttribute("lat", lat);
		request.setAttribute("lng", lng);
		request.setAttribute("name", name);
		
		return "product/map.jsp";
	}

}
