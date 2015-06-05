package com.smriti.utilites;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

public class CharUtil {

	public static final char[] lower = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' };
	public static final char[] upper = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };

	public char[] getLowerCase() {
		return lower;
	}
	public char[] getUpperCase() {
		return upper;
	}
	
	public List<String> getSubCharList(boolean isupper, String from, String to){
		char[] chars = null;
		if (isupper) {
			chars = upper;
		}else {
			chars = lower;
		}
		String a2z = new String(chars);
		int fromP =  a2z.indexOf(from); 
		int toP =  a2z.indexOf(to); 
		
		if (fromP > toP) {
			int p = fromP;
			fromP = toP;
			toP = p;
		}
		Assert.assertTrue(fromP >= 0 && toP < upper.length);		
		List<String> list = new ArrayList<String>();
		for (int i = fromP; i < toP; i++) {
			list.add(i-fromP, new String(chars, i, 1));
		}
		return list;
	}
	
}
