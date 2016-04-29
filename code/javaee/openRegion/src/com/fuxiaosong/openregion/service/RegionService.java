package com.fuxiaosong.openregion.service;

import java.util.ArrayList;
import java.util.HashMap;
import com.fuxiaosong.openregion.model.Region;
import com.fuxiaosong.openregion.util.DBUtils;

public class RegionService {

	@SuppressWarnings("unchecked")
	public ArrayList<Region> fetchProvinces(){
		String sql = "select id,name from provinces";

		DBUtils dbUtils = new DBUtils();
		ArrayList<Object> result = dbUtils.excuteQuery(sql, null);
		
		ArrayList<Region> provincesList = new ArrayList<Region>();
		Region provinces = null;
		if(null != result){
			for(Object obj : result){
				provinces = new Region();
				HashMap<String, String> map = (HashMap<String, String>) obj;
				
				provinces.setId(String.valueOf(map.get("id")));
				provinces.setName(map.get("name"));
				
				provincesList.add(provinces);
			}
			return provincesList;
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Region> fetchCities(String provinceID){
		String sql = "select id,name from cities where provinceID=?";

		DBUtils dbUtils = new DBUtils();
		Object[] params = {provinceID};
		
		ArrayList<Object> result = dbUtils.excuteQuery(sql, params);
		
		ArrayList<Region> citiesList = new ArrayList<Region>();
		Region city = null;
		if(null != result){
			for(Object obj : result){
				city = new Region();
				HashMap<String, String> map = (HashMap<String, String>) obj;
				
				city.setId(String.valueOf(map.get("id")));
				city.setName(map.get("name"));
				
				citiesList.add(city);
			}
			return citiesList;
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Region> fetchCountries(String cityID){
		String sql = "select id,name from countries where cityID=?";

		DBUtils dbUtils = new DBUtils();
		Object[] params = {cityID};
		
		ArrayList<Object> result = dbUtils.excuteQuery(sql, params);
		
		ArrayList<Region> countryList = new ArrayList<Region>();
		Region country = null;
		if(null != result){
			for(Object obj : result){
				country = new Region();
				HashMap<String, String> map = (HashMap<String, String>) obj;
				
				country.setId(String.valueOf(map.get("id")));
				country.setName(map.get("name"));
				
				countryList.add(country);
			}
			return countryList;
		}else{
			return null;
		}
	}
}
