package com.smriti.nav;

public class Nav {
	
	public enum TransitType {
		Enter_Mask (4096), 
		EXIT_MASK (8192), 
		Transit_Unset (-1), 
		Transit_None (0),
		Fragment_Open  (4097),
		Fragment_Close (8194),
		Fragment_Fade (4099); //FragmentTransaction.TRANSIT_FRAGMENT_FADE

	       private int nCode ;
	       private TransitType( int _nCode) {
	           this.nCode = _nCode;
	       }
	       int get(){
	    	   return this.nCode;
	       }
	       @Override
	       public String toString() {
	           return String.valueOf ( this.nCode );
	       }
	    }

}
