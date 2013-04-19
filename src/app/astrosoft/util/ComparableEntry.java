/**
 * ComparableEntry.java
 * Created On 2006, Feb 25, 2006 5:13:03 PM
 * @author E. Rajasekar
 */

package app.astrosoft.util;

import java.util.Map;

public class ComparableEntry<K,V extends Comparable<V>> implements Comparable<ComparableEntry<K,V>>, Map.Entry<K,V> {

    K key;
	V value;
	
	public ComparableEntry(Map.Entry<K,V> entry){
		
		this(entry.getKey(), entry.getValue());
	}
	
	public ComparableEntry(K k, V v){
		key = k;
		value = v;
	}
	
	public int compareTo(ComparableEntry<K,V> o) {
	
		return getValue().compareTo(o.getValue());
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public V setValue(V value) {
		throw new UnsupportedOperationException("Comparable Entry Set.setValue()");		
	}
	
	@Override
	public String toString() {
		
		return key + " : " + value;
	}
	
}
