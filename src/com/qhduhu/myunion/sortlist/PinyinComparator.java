package com.qhduhu.myunion.sortlist;

import java.util.Comparator;

import com.qhduhu.myunion.entity.TXLEntity;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<TXLEntity> {

	public int compare(TXLEntity o1, TXLEntity o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
