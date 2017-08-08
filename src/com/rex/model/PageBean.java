package com.rex.model;

public class PageBean {

	// which page is current page
	private int curPage;
	// how many items on a page
	private int pageSize;
	// the index of the starting item
	private int startIndex;
	
	public PageBean(int curPage, int pageSize) {
		// TODO Auto-generated constructor stub
		this.curPage = curPage;
		this.pageSize = pageSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getStartIndex(){
		return (curPage-1)*pageSize;
	}
}
