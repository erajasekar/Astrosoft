/**
 * AbstractDasa.java
 * Created On 2006, Mar 24, 2006 5:57:06 PM
 * @author E. Rajasekar
 */

package app.astrosoft.core;

import java.util.Date;

import app.astrosoft.util.AstroUtil;

public abstract class AbstractDasa implements Dasa {

	protected Dasa parent;

	protected double start;

	protected double end;

	protected boolean isRunning = false;

	protected int level = 0;

	public AbstractDasa() {
	}

	public AbstractDasa(Dasa parent, double start, int level) {
		this.parent = parent;
		this.start = start;
		this.level = level;
	}

	public Dasa getParent() {

		return parent;
	}

	

	public boolean isRunning() {
		return isRunning;
	}

	public double getStart() {
		return start;
	}

	public double getEnd() {
		return end;
	}

	public int getLevel() {
		return level;
	}

	public abstract double getPeriod();
	
	public abstract String getStartDate();

	public abstract String printTree();

	public abstract Dasa getCurrent();
}
