package com.fuxiaosong.openregion.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
	private Properties properties = null;
	
	public PropertiesUtils(String configFilePath){
		properties = new Properties();
		try {
			properties.load(this.getClass().getResourceAsStream(configFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key) {
		if(properties.isEmpty()){
			return "";
		}else{
			return this.properties.getProperty(key);
		}
	}
}
