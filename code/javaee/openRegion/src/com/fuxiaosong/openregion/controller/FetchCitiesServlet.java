package com.fuxiaosong.openregion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.fuxiaosong.openregion.model.Region;
import com.fuxiaosong.openregion.model.Response;
import com.fuxiaosong.openregion.service.RegionService;

@SuppressWarnings("serial")
public class FetchCitiesServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String provinceID = req.getParameter("provinceid");

		Response respone = new Response();
		if(null == provinceID || "".equals(provinceID)){
			respone.setCode("1000");
		}
		
		RegionService service = new RegionService();
		ArrayList<Region> citiesList = service.fetchCities(provinceID);
		if(null == citiesList){
			respone.setCode("1004");
		}else{
			respone.setCode("1003");
		}
		respone.setData(citiesList);
		String resJsonStr = JSON.toJSONString(respone);
		
		resp.setHeader("Content-Type", "text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(resJsonStr);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
