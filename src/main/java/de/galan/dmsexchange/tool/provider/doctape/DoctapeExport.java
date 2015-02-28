package de.galan.dmsexchange.tool.provider.doctape;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.simple.SimpleContainerFactory;
import org.glassfish.jersey.simple.SimpleServer;
import org.slf4j.Logger;

import de.galan.commons.logging.Logr;
import de.galan.flux.Flux;


/**
 * Export of the doctape data in the format specified by the dms-exchange-specification
 *
 * @author daniel
 */
public class DoctapeExport {

	private static final Logger LOG = Logr.get();


	public static void main(String[] args) {
		try {
			DoctapeExport export = new DoctapeExport();
			export.startServer(); // TODO close at the end
			export.startExport();
		}
		catch (Throwable t) {
			LOG.error("Failed", t);
		}
	}


	public void startExport() throws IOException {
		// Check existing accesstoken
		File file = new File("access-token");
		if (file.exists()) {
			String accessToken = FileUtils.readFileToString(file);
			new ActualExport().export(accessToken);
		}
		else {
			String url = Flux.request("https://my.doctape.com/oauth2").param("scope", "account.read file.read").param("response_type", "token").param(
				"redirect_uri", "http://localhost:8765/dms-exchange/auth").param("state", "anystate").param("client_id", "0006a3e9-7b7f-454d-93f9-f5a80648e535").toUrlString();
			LOG.info("Calling {}", url);
			openWebpage(url);
		}
	}


	protected SimpleServer startServer() {
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(8765).build();
		ResourceConfig config = new ResourceConfig(OAuth1Resource.class, OAuth2Resource.class, StatusResource.class);
		return SimpleContainerFactory.create(baseUri, config);
	}


	public void openWebpage(String uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(URI.create(uri));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
