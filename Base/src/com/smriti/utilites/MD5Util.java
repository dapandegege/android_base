package com.smriti.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	static MessageDigest mDigest = null;
	static {
		try {
			mDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static String md5OfString(String val) {
		mDigest.reset();
		mDigest.update(val.getBytes());
		return new BigInteger(1, mDigest.digest()).toString(16);
	}


	public static String md5OfFile(File file) {
		if (!file.isFile()) {
			return null;
		}
		mDigest.reset();

		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			mDigest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				mDigest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, mDigest.digest());
		return bigInt.toString(16);
	}
}
