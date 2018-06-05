package com.rasmad.ibnu.items;

public class ItemMe
{
	private int image;
	private String title;
	private String subtitle;
	private String link;
	
	public ItemMe(int image, String title, String subtitle, String link)
	{
		this.image = image;
		this.title = title;
		this.subtitle = subtitle;
		this.link = link;
	}
	
	public int getImage()
	{
		return image;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getSubtitle()
	{
		return subtitle;
	}
	
	public String getLink()
	{
		return link;
	}
	
	public void setImage(int image)
	{
		this.image = image;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setSubtitle(String subtitle)
	{
		this.subtitle = subtitle;
	}
	
	public void setLink(String link)
	{
		this.link = link;
	}
}
