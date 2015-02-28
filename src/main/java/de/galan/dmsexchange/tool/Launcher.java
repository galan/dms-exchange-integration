package de.galan.dmsexchange.tool;

import de.galan.dmsexchange.tool.provider.DmsProvider;
import de.galan.dmsexchange.tool.provider.organizeme.OrganizemeDmsProvider;


/**
 * Very early alpha, don't look here ;)
 *
 * @author daniel
 */
public class Launcher {

	public static void main(String[] args) {
		DmsProvider provider = new OrganizemeDmsProvider();
		provider.exportArchive();
	}

}
