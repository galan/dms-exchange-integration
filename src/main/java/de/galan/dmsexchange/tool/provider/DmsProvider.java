package de.galan.dmsexchange.tool.provider;

/**
 * Each DMS has it's own implementation
 *
 * @author daniel
 */
public interface DmsProvider {

	public void exportArchive();


	public void importArchive();

}
