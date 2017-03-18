package de.galan.dmsexchange.tool.provider.centerdevice;

import de.galan.dmsexchange.tool.provider.DmsProvider;


/**
 * CenterDevice sample implementation
 */
public class CenterDeviceDmsProvider implements DmsProvider {

	protected String getBaseUrl() {
		return "https://api.centerdevice.de/v2";
	}


	@Override
	public void exportArchive() {
		// TODO
	}


	@Override
	public void importArchive() {
		// TODO
	}

}
