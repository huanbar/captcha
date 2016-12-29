package cn.com.huanbar.util;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AppUtil implements Serializable {
	private static final long serialVersionUID = 606271452116829389L;
	public static Logger logger = LogManager.getLogger(AppUtil.class);
	public static final String propertiesUrl = "config.app";
	private Properties properties = null;
	private static AppUtil appUtil = null;

	public static AppUtil getInstance() {
		if (appUtil == null) {
			appUtil = new AppUtil();
			appUtil.init();
		}

		return appUtil;
	}

	public void init() {
		try {
			this.properties = new Properties();
			ResourceBundle resourceBundle = ResourceBundle.getBundle("app");
			Enumeration enumeration = resourceBundle.getKeys();
			String key = null;
			while (enumeration.hasMoreElements()) {
				key = (String) enumeration.nextElement();
				this.properties.put(key, resourceBundle.getString(key));
			}
			resourceBundle = null;
		} catch (Exception e) {
			logger.error("初始化出错", e);
		}
	}

	public Properties getProperties() {
		return this.properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getProperties(String key) {
		return this.properties.getProperty(key);
	}

	public void appendProperties(Properties properties) {
		this.properties.putAll(properties);
	}

	public boolean getBooleanProperties(String key) {
		return Boolean.valueOf(getProperties(key)).booleanValue();
	}

	public static void main(String[] args) {
		AppUtil app = getInstance();
		app.init();
	}
}