/**
 * Timer.java
 * Created On 2006, Jan 8, 2006 6:48:02 PM
 * @author E. Rajasekar
 */
package app.astrosoft.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer {

	 private static final Logger log = Logger.getLogger(Timer.class.getName());
	 
	 int printCounter = 0;
	 long t;

	 public Timer() {
	 reset();
	 }
	 
	 public void reset() {
		 t = System.currentTimeMillis();
	 }

	 public long elapsed() {
		 return System.currentTimeMillis() - t;
	 }

	 public void print(String s) {
		 
		 if(log.isLoggable(Level.FINE)) {
			 log.fine(s + ": " + elapsed() + " ms");
		 }
	 }
	 
	 public void print(){
		 print(String.valueOf(printCounter++));
	 }
	 
	 public void printAndReset(){
		 print();
		 reset();
	 }
}