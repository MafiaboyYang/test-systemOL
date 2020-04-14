package com.zjut.utils;

import java.util.UUID;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月14日下午10:10:22
*类说明
*/
public class UuidUtil {
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}
