/**CollectionUtil.java
 Created On 2007, Oct 24, 2007 5:55:02 PM
 @author E. Rajasekar
*/

package app.astrosoft.util;

import java.util.EnumMap;
import java.util.Map;

public class CollectionUtil {

	public static <K,V> Map.Entry<K,V> newEntry(final K key, final V value){
		Map.Entry<K,V> entry = new Map.Entry<K,V>(){

			public K getKey() {
				return key;
			}

			public V getValue() {
				return value;
			}

			public V setValue(V value) {
				throw new UnsupportedOperationException("setValue not supported");
			}
			
		};
		
		return entry;
	}
	
	public static <K extends Enum<K>,V> EnumMap<K,V> newEnumMap(Class<K> type, Map.Entry<K, V> ...entries){
		
		EnumMap<K,V> map = new EnumMap<K, V>(type);
		
		for (Map.Entry<K, V> entry:entries){
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}
}
