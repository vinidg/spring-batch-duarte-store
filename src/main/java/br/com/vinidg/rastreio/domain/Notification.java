package br.com.vinidg.rastreio.domain;

import java.util.List;

public class Notification {

	private String app_id;
	private List<String> include_player_ids;
	private Data data;
	private Content contents;
	private Headings headings;
	private String small_icon;
	private String android_accent_color;

	public Notification(String app_id, List<String> include_player_ids, Data data, Content contents, Headings headings,
			String small_icon, String android_accent_color) {
		super();
		this.app_id = app_id;
		this.include_player_ids = include_player_ids;
		this.data = data;
		this.contents = contents;
		this.headings = headings;
		this.small_icon = small_icon;
		this.android_accent_color = android_accent_color;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public List<String> getInclude_player_ids() {
		return include_player_ids;
	}

	public void setInclude_player_ids(List<String> include_player_ids) {
		this.include_player_ids = include_player_ids;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Content getContents() {
		return contents;
	}

	public void setContents(Content contents) {
		this.contents = contents;
	}

	public Headings getHeadings() {
		return headings;
	}

	public void setHeadings(Headings headings) {
		this.headings = headings;
	}

	public String getSmall_icon() {
		return small_icon;
	}

	public void setSmall_icon(String small_icon) {
		this.small_icon = small_icon;
	}

	public String getAndroid_accent_color() {
		return android_accent_color;
	}

	public void setAndroid_accent_color(String android_accent_color) {
		this.android_accent_color = android_accent_color;
	}

	public static class Builder {
		private String app_id;
		private List<String> include_player_ids;
		private Data data;
		private Content contents;
		private Headings headings;
		private String small_icon;
		private String android_accent_color;

		public Builder app_id(String app_id) {
			this.app_id = app_id;
			return this;
		}

		public Builder include_player_ids(List<String> include_player_ids) {
			this.include_player_ids = include_player_ids;
			return this;
		}

		public Builder data(Data data) {
			this.data = data;
			return this;
		}

		public Builder contents(Content contents) {
			this.contents = contents;
			return this;
		}

		public Builder headings(Headings headings) {
			this.headings = headings;
			return this;
		}

		public Builder small_icon(String small_icon) {
			this.small_icon = small_icon;
			return this;
		}

		public Builder android_accent_color(String android_accent_color) {
			this.android_accent_color = android_accent_color;
			return this;
		}

		public Notification build() {
			return new Notification(this);
		}
	}

	private Notification(Builder builder) {
		this.app_id = builder.app_id;
		this.include_player_ids = builder.include_player_ids;
		this.data = builder.data;
		this.contents = builder.contents;
		this.headings = builder.headings;
		this.small_icon = builder.small_icon;
		this.android_accent_color = builder.android_accent_color;
	}

}
