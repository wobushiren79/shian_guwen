package com.shian.shianlife.provide.imp.impl;

import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.imp.MUserManager;

public class MUserManagerImpl implements MUserManager {
	public HttpRequestExecutor excutor = new HttpRequestExecutor();
	private static MUserManager manager;

	private MUserManagerImpl() {
	};

	public static MUserManager getInstance() {
		if (manager == null) {
			manager = new MUserManagerImpl();
		}
		return manager;
	}

	

}
