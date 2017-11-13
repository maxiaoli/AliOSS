package com.howie.util;

import java.util.UUID;

public class UUIDUtils {

	/*
	 * 返回UUID的值
	 * */
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
}
