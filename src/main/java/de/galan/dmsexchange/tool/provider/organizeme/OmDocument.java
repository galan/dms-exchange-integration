package de.galan.dmsexchange.tool.provider.organizeme;

import static de.galan.commons.time.Instants.*;
import static org.apache.commons.lang3.StringUtils.*;

import java.time.ZonedDateTime;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;


/**
 * Represents the &gt;document&lt; in the proprietary organize.me format.
 *
 * @author daniel
 */
public class OmDocument {

	private String file;
	private String title;
	private String uuid;
	private String created;
	private String category;
	private String type;
	private String tags;
	private String notes;


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}


	public ZonedDateTime getCreatedTime() {
		return from(dateLocal(getCreated())).toZdt();
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTags() {
		return tags;
	}


	public void setTags(String tags) {
		this.tags = tags;
	}


	public String[] getTagsArray() {
		if (isBlank(getTags())) {
			return new String[] {};
		}
		return Iterables.toArray(Splitter.on(",").omitEmptyStrings().trimResults().split(getTags()), String.class);
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}

}
