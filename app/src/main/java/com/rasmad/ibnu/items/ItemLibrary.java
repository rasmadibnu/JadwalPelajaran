package com.rasmad.ibnu.items;

public class ItemLibrary
{
	private String libName, libOwn, link;
	
	public ItemLibrary(String libName, String libOwn, String link)
	{
		this.libName = libName;
		this.libOwn = libOwn;
		this.link = link;
	}
	
	public String getNameLib() {
		return libName;
	}
	
	public String getOwnLib() {
		return libOwn;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setNameLib(String libName) {
		this.libName = libName;
	}
	
	public void setOwnlib(String libOwn) {
		this.libOwn = libOwn;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
}
