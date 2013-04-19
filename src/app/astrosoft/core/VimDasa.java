/**
 * VimDasa.java
 * Created On 2006, Mar 22, 2006 6:20:58 PM
 * @author E. Rajasekar
 */

package app.astrosoft.core;

import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.DisplayFormat;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Planet;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.util.AstroUtil;

public class VimDasa extends AbstractDasa {
	
	private Planet lord;
	
	private EnumMap<Planet, Dasa> subDasas;
	
	public VimDasa() {
		super();
	}
	
	public VimDasa(Planet lord, Dasa parent, double start, int level) {
		super(parent, start, level);
		this.lord = lord;
		this.end = start + getPeriod();
		isRunning = (today > start && today < end);
	}
	
	public Map<Planet, Dasa> getSubDasas() {
		
		generateSubDasas();
		return subDasas;
	}

	public String getStartDate() {

		return Vimshottari.dateFormat.format(AstroUtil.decimalYearToDate(start));
	}

	public String getEndDate() {
		return Vimshottari.dateFormat.format(AstroUtil.decimalYearToDate(end));
	}
	
	public Dasa getCurrent(){
		
		Dasa current = null;
		if(isLeaf()){
			current = this;
		}else{
			for(Dasa d : subDasas()){
				if(d.isRunning()){
					current = d.getCurrent();
				}
			}
		}
		return current;
	}
	
	@Override
	public String toString() {
		
		if(lord == null){
			return DisplayStrings.DASA_STR.toString();
		}
		return lord.toString();
	}
	
	public String dasaPeriod(){
		StringBuilder sb = new StringBuilder(fullDasa());
		sb.append(getStartDate() + "\n");
		if(isRunning){
			sb.insert(0, "*");
		}
		return sb.toString();
	}
	
	public String fullDasa(){
		
		StringBuilder sb = new StringBuilder(lord.toString(DisplayFormat.SYMBOL));
		VimDasa major = (VimDasa)parent;
		while(major != null){
			sb.insert(0, major.getDasa().toString(DisplayFormat.SYMBOL) + "/");
			major = (VimDasa) major.getParent();
		}
		return sb.toString();
	}
	
	public String printTree(){
		
		StringBuilder sb = new StringBuilder();
		
		if(subDasas == null){
			sb.append(dasaPeriod());
			
		}else{
			for(Dasa d : subDasas()){
				sb.append(d.printTree());
			}
		}
		
		return sb.toString();
	}
	
	public DefaultMutableTreeNode createTreeNode(){
		
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(this);
		if(subDasas == null){
			return node;
		}else{
			for(Dasa d : subDasas()){
				node.add(d.createTreeNode());
			}
			return node;
		}
		
	}
	public Planet getDasa() {
		
		return lord;
	}
	
	public double getPeriod(){
		if(parent == null){
			return lord.dasaPeriod();
		}else{
			return ((parent.getPeriod() * lord.dasaPeriod()) / 120.00);
		}
	}
	
	public void generateSubDasas() {
		
		if (subDasas == null && !isLeaf()){
			subDasas = generateSubDasas(lord, this, start, level);
		}
	}
	
	public static EnumMap<Planet, Dasa> generateSubDasas(Planet lord, Dasa parent, double start, int level) {
		
		EnumMap<Planet, Dasa>subDasas = new EnumMap<Planet, Dasa>(Planet.class);
		for(Planet subLord : Planet.dasaLords(lord)){
			Dasa dasa = new VimDasa(subLord, parent, start, level + 1);
			start = dasa.getEnd();
			subDasas.put(subLord, dasa);
		}
		
		return subDasas;
	}

	public boolean isLeaf(){
		return (level > Vimshottari.MAX_LEVEL - 1 );
	}
	
	public Iterable<Dasa> subDasas(){
		generateSubDasas();
		return new DasaIterable(lord, subDasas);
	}
	
	public static class DasaIterable implements Iterable<Dasa>{
		
		Planet startWith;
		EnumMap<Planet, Dasa> dasas;
		
		public DasaIterable(Planet startWith, EnumMap<Planet, Dasa> dasas) {
			this.startWith = startWith;
			this.dasas = dasas;
		}

		public Iterator<Dasa> iterator() {
			return new DasaIterator(startWith, dasas);
		}
	}
	
	public static class DasaIterator implements Iterator<Dasa>{

		Iterator dasaIterator;
		EnumMap<Planet, Dasa> subDasas;
		
		public DasaIterator(Planet startWith, EnumMap<Planet, Dasa> subDasas) {
			
			dasaIterator = Planet.dasaIterator(startWith);
			this.subDasas = subDasas;
			if(subDasas == null){
				dasaIterator = Collections.EMPTY_LIST.iterator();
			}
			
		}
		
		public boolean hasNext() {
			return dasaIterator.hasNext();
		}

		public Dasa next() {
			
			return subDasas.get(dasaIterator.next());
		}

		public void remove() {
			throw new UnsupportedOperationException("remove is not supported");
		}
	}

	
	public Object getColumnData(AstrosoftTableColumn col) {
		
		switch(col){
			case Dasa: return fullDasa();
			case Start: return getStartDate();
			case End: return getEndDate();
			default : return null;
		}
	}
	
}
