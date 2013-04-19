/**
 * AstroScrap.java
 * Created On 2005, Oct 15, 2005 2:36:14 PM
 * @author relango
 */

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import app.astrosoft.beans.Interval;
import app.astrosoft.consts.Rasi;
import app.astrosoft.util.AstroUtil;
import app.astrosoft.util.Timer;
import static app.astrosoft.consts.AstroConsts.*;
import swisseph.SweDate;


public class AstroScrap {
	
	
	public static enum Alp {
		A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;
		
		private static Alp values[] = values();
		
		public static Alp ofVal1(int index){
			Alp []vals = values();
			return vals[index % vals.length];
		}
		
		public static Alp ofVal2(int index){
			return values[index % values.length];
		}
		
		
	};

	public static void main(String []args){
		
		
		for(int i = 0; i < 27; i++){
			
			System.out.println(i + " -> " + AstroUtil.dms(i * nakLength) );
		}
		
		//System.out.println(longitudeForRasiNak(4,9));
		
		SweDate sd = new SweDate();
		System.out.println(SweDate.getDate(AstroUtil.incJulDate(sd.getJulDay(), 1, 1, 0)));
		
		//TEST ENUM PERFORMANCE
		
		Timer t = new Timer();
		for(int i=0; i < 10000; i++){
			Alp.ofVal1(i);
		}
		t.print("Time of ofVal1()");
		t.reset();
		for(int i=0; i < 10000; i++){
			Alp.ofVal2(i);
		}
		t.print("Time of ofVal2()");
		
		EnumMap<Alp, String> em = new EnumMap<Alp, String>(Alp.class);
		for(int i=0; i < 10000; i++){
			em.put(Alp.values()[i%26], "a");
		}
		
		int arr[] = new int[10000];
		
		t.reset();
		for(int i=0; i < 100000; i++){
			String s = em.get(Alp.A);
		}
		t.print("Time of enum");
		
		t.reset();
		for(int i=0; i < 100000; i++){
			int a = arr[0];
		}
		t.print("Time of arr");
		method1(Alp.A);
		
	}
	
	/*private static Interval longitudeForRasiNak(int rasi, int nak){
		
		double rasiStart = rasi * rasiLength;
		double rasiEnd = rasiStart + rasiLength;
		
		double nakStart = nak * nakLength;
		double nakEnd = nakStart + nakLength;
		
		Interval rasiInt = new Interval (rasiStart, rasiEnd);
		Interval nakInt = new Interval (nakStart, nakEnd);
		
		return rasiInt.intersection(nakInt);
	}*/
	
	private  static <T extends Enum<T>> void method1(Enum<T> e){
		
		//Enum t = (Enum)e.getDeclaringClass();
		//System.out.println((e.valueOf(e.getDeclaringClass(),"A")));
		
		T t = Enum.valueOf(e.getDeclaringClass(), e.name());
		/*switch(t){
			
		}*/
		
		List<String> c = new ArrayList<String>();
		List l = java.util.Collections.unmodifiableList(c);
		
		System.out.println(l.iterator());
		
		
	}
	
	/*private <T extends Object> void  m(Collection<T> c){
		Object o=null;
		T t = (T) o;
	}
	
    /*private double m(){
		return 2.0;
	}*/
	
	
}
