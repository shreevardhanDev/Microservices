package com.test.order.listners;

import com.test.order.dto.ThreadLocalObject;

public class AuditListener {

	private AuditListener() {
		throw new IllegalAccessError();
	}

	private static ThreadLocal<ThreadLocalObject> USER = new ThreadLocal<>();

	public static void clearThreadLocal() {
		USER.remove();
	}

	public static void setAuditUserDetail(ThreadLocalObject user) {
		USER.set(user);
	}

	public static ThreadLocalObject getAuditUserDetial() {
		return USER.get();
	}

	public static String getJsonWebTocken() {
		return USER.get().getJsonTocken();
	}

}
