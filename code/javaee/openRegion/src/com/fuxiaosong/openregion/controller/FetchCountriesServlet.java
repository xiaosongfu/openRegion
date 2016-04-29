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
public class FetchCountriesServlet  extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cityID = req.getParameter("cityid");

		Response respone = new Response();
		if(null == cityID || "".equals(cityID)){
			respone.setCode("1000");
		}
		
		RegionService service = new RegionService();
		ArrayList<Region> countriesList = service.fetchCountries(cityID);
		if(null == countriesList){
			respone.setCode("1006");
		}else{
			respone.setCode("1003");
		}
		respone.setData(countriesList);
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