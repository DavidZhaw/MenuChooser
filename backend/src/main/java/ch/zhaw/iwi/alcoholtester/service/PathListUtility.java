package ch.zhaw.iwi.alcoholtester.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class PathListUtility {

	private PathListUtility() {
	}

	public static final <T> List<PathListEntry<T>> sort(List<PathListEntry<T>> list) {
		Comparator<PathListEntry<?>> comparator = new Comparator<PathListEntry<?>>() {
			@Override
			public int compare(PathListEntry<?> o1, PathListEntry<?> o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
		Collections.sort(list, comparator);
		return list;
	}

}
