/**
 * Attributes.java
 * Got from http://artho.com/webtools/java/index.shtml
 */


package app.astrosoft.html;

import java.util.*;

public class Attributes extends LinkedList {

    public Attributes() {
	super();
    }

    public Attributes(String attr) throws IllegalArgumentException {
	super();
	setAttributes(attr);
    }

    public Attributes(Attribute[] attrs) {
	super();
	for (int i = 0; i < attrs.length; i++) {
	    add(attrs[i]);
	}
    }

    public Attributes(Attribute attr) {
	super();
	add(attr);
    }

    /**
       * Set the value of attributes.
       * @param str  String with HTML attributes like:
       * border="0" cellpadding="0" cellspacing="0"
       */

    public void setAttributes(String str) throws IllegalArgumentException {
	Attribute attribute = new Attribute();	
	final int START = 0;
	// final int EOL = 1;
	final int ERR = -1;
	final int Attr = 2;
	final int Val0 = 3;
	final int Val1 = 4;
	final int Value = 5;
	final int QVal = 6;
	// finite state machine for parsing
	int state = START;
	int prev = -1; // debug
	int i = 0;
	StringBuffer token = new StringBuffer();

	while ((i <= str.length()) && (state != ERR)) {
	    char chr;
	    if (i < str.length())
		chr = str.charAt(i);
	    else
		chr = ' ';
	    i++;
	    prev = state;
	    switch(state) {
	    case START:
		switch(chr) {
		case ' ': break;
		case '"': case '=': 
		    state = ERR; break;
		default: token.append(chr); state = Attr;
		}
		break;
	    case Attr:
		switch(chr) {
		case ' ': 
		    attribute = new Attribute(token.toString());
		    state = Val0;
		    break;
		case '=': 		    
		  attribute = new Attribute(token.toString());
		  state = Val1; 
		  break;
		case '"':
		    state = ERR; break;
		default: token.append(chr);
		}
		break;
	    case Val0:
	        switch(chr) {
	            case '=': state = Val1; break;
	            case ' ': break;
	            default: 
		      this.add(attribute);
		      token = new StringBuffer();
		      token.append(chr);
		      state = Attr;
	        }
		break;
	    case Val1:
	        token = new StringBuffer();
	        switch(chr) {
	        case '"': state = QVal; break;
	        case '=': state = ERR; break;
	        case ' ': break;
	        default: 
		  token.append(chr);
		  state = Value;
		  break;
		}
		break;
	    case Value:
		switch(chr) {
		case ' ':
		    attribute.setValue(token.toString());
		    this.add(attribute);
		    token = new StringBuffer();
		    state = START;
		    break;
		case '=': state = ERR; break;
		default: token.append(chr);
		}
		break;
	    case QVal:
		switch(chr) {
		case '"':
		    attribute.setValue(token.toString());
		    this.add(attribute);
		    token = new StringBuffer();
		    state = START;
		    break;
		default: token.append(chr);
		}
		break;
	    }
	}
	if ((i <= str.length()) || (state == ERR)) {
	    System.err.println("Parse error:");
	    System.err.println(str);
	    for (int j = 0; j < i; j++)
		System.err.print(' ');
	    System.err.println('^');
	    throw new IllegalArgumentException("Parse error in state " + 
					       Integer.toString(prev));
	}
    }
	
    public String toString() {
	StringBuffer out = new StringBuffer(" ");
	ListIterator iterator = this.listIterator();
	while (iterator.hasNext()) {
	    out.append(iterator.next().toString());
	    out.append(' ');
	}
	out.deleteCharAt(out.length() - 1);
	return out.toString();
    }
}


