/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UTIL;

/**
 * 
 * @author Mr Long
 */
public class NavigationInfo {

	private int currentPage;
	private int pageSize;
	private int rowCount;
	private int maxIndices;

	public NavigationInfo() {
		currentPage = 0;
		rowCount = 0;
		maxIndices = 5;
		pageSize = 5;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getMaxIndices() {
		return maxIndices;
	}

	public void setMaxIndices(int maxIndices) {
		this.maxIndices = maxIndices;
	}

	public int getPageCount() {
		return (int) Math.ceil((double) rowCount / pageSize);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {

		int pCount = getPageCount() - 1;
		if (currentPage <= 0) {
			this.currentPage = 0;
		} else if (currentPage > pCount) {
			this.currentPage = pCount;
		} else {
			this.currentPage = currentPage;
		}
	}

	public int getPrevIndex() {
		int prev = getIndexStart() - 1;
		return prev < 0 ? 0 : prev;
	}

	public int getNextIndex() {
		int lastIndex = getPageCount() - 1;
		int next = currentPage + 1;
		return next > lastIndex ? lastIndex : next;
	}

	public boolean isFirstPage() {
		if (getIndexStart()>0) {
			return false;
		}
		return true;
	}

	public boolean isLastPage() {
		if(getIndexLast()<getPageCount()) return false;
		return true;
	}

	public int[] getIndexRange() {
		// determine the standard window
		int temp1 = maxIndices / 2;
		int start = currentPage - temp1;

		int temp2 = maxIndices - 1;
		int end = start + temp2;
		// shift to right if start underflows 0
		if (start < 0) {
			end -= start; // end – -start = end + start = shift right
			start = 0;
		}
		// now maybe the window overflows pageCount – so shift to left again
		int lastIndex = getPageCount() - 1;
		if (end > (lastIndex)) {
			start -= end + lastIndex;
			end = lastIndex;
		}
		// we have finalized end, now if start < 0 then truncate it
		if (start < 0)
			start = 0;
		return new int[] { start, end };
	}

	public int[] getIndexList() {
		int[] range = getIndexRange();
		int[] ilist = new int[range[1] - range[0] + 1];
		for (int i = 0; i < ilist.length; i++) {
			ilist[i] = range[0] + i;
		}
		return ilist;
	}

	public int getIndexStart() {
		int index = (int) Math.ceil((double) (currentPage + 1) / maxIndices);
		return (index - 1) * maxIndices;

	}

	public int getIndexLast() {

		if (getPageCount() < (getIndexStart() + maxIndices)) {
			return getPageCount();
		} else {
			return getIndexStart() + maxIndices;
		}

	}

	public int getIndexNext() {
		return getIndexLast();
	}

	public boolean hasIndexNext() {
		//System.out.println("getPageCount: " + getPageCount());
		//System.out.println("maxIndices: " + maxIndices);
		if ((getIndexLast()+1) < getPageCount()) {
			//System.out.println("true");
			return true;

		}
		// System.out.println("false");
		return false;
	}

	public boolean hasIndexNext_2() {
		//System.out.println("getPageCount: " + getPageCount());
		//System.out.println("maxIndices: " + maxIndices);

		if ((getIndexLast()+1) < getPageCount()) {
			//System.out.println("true");
			return true;

		}
		// System.out.println("false");
		return false;
	}

	public int getIndexPrev() {
		int prev = getIndexStart() - 1;
		return prev < 0 ? 0 : prev;

	}

	public boolean hasIndexPrev() {
		if ((getPageCount() > maxIndices)) {
			if ((currentPage - maxIndices + 1) > 0) {
				return true;
			}
		}
		// System.out.println("false");
		return false;
	}

	public static void main(String[] args) {
		NavigationInfo n = new NavigationInfo();
		n.setPageSize(3);
		n.setRowCount(54);
		n.setCurrentPage(17);
		n.setMaxIndices(3);
		if(!n.isFirstPage()){
			System.out.println("dau: "+0);
		}
	
		System.out.println("pagecount: "+n.getPageCount());
		if (n.hasIndexPrev()) {
			System.out.println("getPrevIndex(): " + n.getPrevIndex());
		}
		System.out.println("n.getIndexStart(): " + n.getIndexStart());
		System.out.println("n.getIndexLast(): " + n.getIndexLast());

		if (n.hasIndexNext_2()) {
			System.out.println("n.getIndexNext(): " + n.getIndexNext());
		}
		if(!n.isLastPage()){
			System.out.println("cuoi: "+(n.getPageCount()-1));
		}
	}

}
