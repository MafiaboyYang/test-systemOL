package com.zjut.utils;

import org.slf4j.LoggerFactory;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月4日下午2:59:08
*日志处理
*/
public class Logger {
private org.slf4j.Logger logger;

	
	/**
	 * 构造方法，初始化Log4j的日志对象
	 */
	private Logger(org.slf4j.Logger log4jLogger) {
		logger = log4jLogger;
	}

	/**
	 * 获取构造器，根据类初始化Logger对象
	 * 
	 * @param Class
	 *            Class对象
	 * @return Logger对象
	 */
	public static Logger getLogger(Class classObject) {
		return new Logger(LoggerFactory.getLogger(classObject));
	}

	/**
	 * 获取构造器，根据类名初始化Logger对象
	 * 
	 * @param String
	 *            类名字符串
	 * @return Logger对象
	 */
	public static Logger getLogger(String loggerName) {
		return new Logger(LoggerFactory.getLogger(loggerName));
	}

	public void debug(String object) {
		logger.debug(object);
	}

	public void debug(String object, Throwable e) {
		logger.debug(object, e);
	}

	public void info(String object) {
		logger.info(object);
	}

	public void info(String object, Throwable e) {
		logger.info(object, e);
	}

	public void warn(String object) {
		logger.warn(object);
	}

	public void warn(String object, Throwable e) {
		logger.warn(object, e);
	}

	public void error(String object) {
		logger.error(object);
	}

	public void error(String object, Throwable e) {
		logger.error(object, e);
	}

//	public void fatal(String object) {
//		logger.fatal(object);
//	}

	public String getName() {
		return logger.getName();
	}

	public org.slf4j.Logger getLog4jLogger() {
		return logger;
	}

	public boolean equals(Logger newLogger) {
		return logger.equals(newLogger.getLog4jLogger());
	}
}
