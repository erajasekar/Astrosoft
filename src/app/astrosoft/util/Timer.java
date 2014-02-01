/**
 * Timer.java
 * Created On 2006, Jan 8, 2006 6:48:02 PM
 * @author E. Rajasekar
 */
package app.astrosoft.util;

import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

public class Timer {

	 private static final Logger log = getLogger(Timer.class.getName());
	 
	 int printCounter = 0;
	 long t;

	 public Timer() {
	 reset();
	 }
	 
	 public void reset() {
		 t = currentTimeMillis();
	 }

	 public long elapsed() {
		 return currentTimeMillis() - t;
	 }

	 public void print(String s) {
		 
		 if(log.isLoggable(Level.FINE)) {
			 log.fine(s + ": " + elapsed() + " ms");
		 }
	 }
	 
	 public void print(){
		 print(valueOf(printCounter++));
	 }
	 
	 public void printAndReset(){
		 print();
		 reset();
	 }
}