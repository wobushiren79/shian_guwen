package com.shian.shianlife.provide;

import com.shian.shianlife.provide.imp.FileManager;
import com.shian.shianlife.provide.imp.MAccountManager;
import com.shian.shianlife.provide.imp.MUserManager;
import com.shian.shianlife.provide.imp.impl.FileManagerImpl;
import com.shian.shianlife.provide.imp.impl.MAccountManagerImpl;
import com.shian.shianlife.provide.imp.impl.MUserManagerImpl;

/**
 * 接口工厂
 * 
 * @author Administrator
 * 
 */
public class MHttpManagerFactory {
	/**
	 * 获取账户接口manager
	 * 
	 * @return
	 */
	public static MAccountManager getAccountManager() {
		return MAccountManagerImpl.getInstance();
	}

	public static MUserManager getMUserManager() {
		return MUserManagerImpl.getInstance();
	}

	public static FileManager getFileManager() {
		return FileManagerImpl.getInstance();
	}
}
