/**
 * PanchangList.java
 * Created On 2007, Sep 14, 2007 1:52:34 PM
 * @author E. Rajasekar
 */

package app.astrosoft.core;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import app.astrosoft.core.Ephemeris.Mode;
import app.astrosoft.export.Exportable;
import app.astrosoft.export.Exporter;
import app.astrosoft.util.AstroUtil;

public class PanchangList implements Iterable<Panchang>, Exportable {

	private Calendar cal;
    
    private Mode mode;
    
    public static class PanchangIterator implements Iterator<Panchang>{

    	private Calendar start;
    	private Calendar current;
    	private Mode mode;
    	
    	public PanchangIterator(Calendar start,Mode mode){
    		this.start = start;
    		this.current = AstroUtil.getCalendar(start.getTime());
    		this.mode = mode;
    	}
		
    	public boolean hasNext() {
			return current.get(mode.parentCalField()) == start.get(mode.parentCalField());
		}

		public Panchang next() {
			Panchang p = new Panchang(current);
			current.add(Calendar.DATE, 1);
			return p;
		}

		public void remove() {
			throw new UnsupportedOperationException("remove not supported for PanchangIterator");
			
		}
    	
    }
    
    public PanchangList(int year){
    	 this(new GregorianCalendar(year, Calendar.JANUARY, 1), Mode.Monthly);
    }
    
    public PanchangList(int year,int month){
   	 	this(new GregorianCalendar(year, month, 1), Mode.Daily);
    }
    
    private PanchangList(Calendar cal, Mode mode){
        
    	this.cal = cal;
    	this.mode = mode;
    	cal.set(Calendar.DATE, 1);
    }
    
	public Iterator<Panchang> iterator() {
		
		return new PanchangIterator(cal,mode);
	}
	
	public void doExport(Exporter e) {
		e.export(this);
		
	}
	
	public static void main(String[] args) {
		PanchangList pl = new PanchangList(2007);
		
		for(Panchang p : pl){
			System.out.println(p);
			System.out.println("------------------------------------------------------");
		}
	}

	}
