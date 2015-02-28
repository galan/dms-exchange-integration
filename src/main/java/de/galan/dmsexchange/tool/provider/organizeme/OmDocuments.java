package de.galan.dmsexchange.tool.provider.organizeme;

import java.util.List;


/**
 * Represents the &gt;documents&lt; in the proprietary organize.me format.
 *
 * @author daniel
 */
public class OmDocuments {

	private List<OmDocument> documents;


	public List<OmDocument> getDocuments() {
		return documents;
	}


	public void setDocuments(List<OmDocument> documents) {
		this.documents = documents;
	}

}
