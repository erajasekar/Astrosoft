/**
 * YogaResults.java
 * Created On 2007, Oct 22, 2007 2:58:29 PM
 * @author E. Rajasekar
 */

package app.astrosoft.xps.yoga;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import app.astrosoft.consts.YogaCombination;

public class YogaResults {
	
	public static class Result{
		
		private YogaCombination yoga;
		private String efficacy;
		
		public Result(YogaCombination yoga, String efficacy){
			this.yoga = yoga;
			this.efficacy = efficacy;
		}
		
		public YogaCombination getYoga() {
			return yoga;
		}
		
		public String getEfficacy() {
			return efficacy;
		}
		
		@Override
		public int hashCode() {
			
			int hash = 17;
			
			hash = hash + 31 * yoga.hashCode();
			hash = hash + 31 * efficacy.hashCode();
			
			return hash;
		}
		
		@Override
		public boolean equals(Object o) {

			if (o instanceof Result){
				
				Result r = (Result) o;
				return (r.yoga.equals(this.yoga) && r.efficacy.equals(this.efficacy));
			}
			
			return false;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(yoga.toString());
			sb.append(" ( ");
			sb.append(efficacy);
			sb.append(" )");
			return sb.toString();
		}
	}
	
	private List<Result> yogas;
	
	public YogaResults() {
		yogas = new ArrayList<Result>();
	}
	
	public void addYoga(YogaCombination yoga, String efficacy){
		yogas.add(new Result(yoga, efficacy));
	}
	
	public void addYoga(YogaCombination yoga){
		addYoga(yoga,"100%");
	}
	
	public List<Result> getYogas() {
		return yogas;
	}
	
	public void clearAll(){
		yogas.clear();
	}
	
	public boolean hasYogaCombination(YogaCombination yoga){
		
		for(Result r : yogas){
			if (r.yoga.equals(yoga)){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		
		return yogas.toString();
	}
}
