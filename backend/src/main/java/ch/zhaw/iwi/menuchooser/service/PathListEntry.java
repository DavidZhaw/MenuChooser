package ch.zhaw.iwi.menuchooser.service;

import java.util.ArrayList;
import java.util.List;

public class PathListEntry<KEYTYPE> {

	private Key key;
	private String name;
	private String color;
	private String icon;
	private String url;
	private boolean active;
	private List<String> details = new ArrayList<>();
	
	public PathListEntry() {
		this.active = true;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(KEYTYPE key, String name) {
		this.key = new Key(key, name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public class Key {

		public Key(KEYTYPE key, String name) {
			super();
			this.key = key;
			this.name = name;
		}

		private KEYTYPE key;
		private String name;

		public KEYTYPE getKey() {
			return key;
		}

		public String getName() {
			return name;
		}
		
	}

}
