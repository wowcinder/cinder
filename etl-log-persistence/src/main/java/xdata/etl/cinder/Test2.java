package xdata.etl.cinder;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class Test2 {

	public static void main(String[] args) throws InterruptedException {
		String a = new String("a");
		String b = new String("b");
		Map weakMap = new WeakHashMap();
		weakMap.put(a, "aaa");
		weakMap.put(b, "bbb");
		a = null;
		System.gc();
		Iterator iterator = weakMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry en = (Entry) iterator.next();
			System.out.println(en.getKey() + " : " + en.getValue());
		}
	}
}
