package com.smriti.utilites;

public class ObjectUtil {
	
	class Holder<T> {  
	    public T value = null;  
	    public Holder(T v) {  
	        this.value = v;  
	    }  
	}  
	  
	public static <T> void swap(Holder<T> a, Holder<T> b) {  
        T temp = a.value;  
        a.value = b.value;  
        b.value = temp;  
    }  
	
}

