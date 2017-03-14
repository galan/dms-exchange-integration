package de.galan.dmsexchange.tool.provider.doctape;

import org.slf4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.galan.commons.logging.Slogr;
import de.galan.commons.net.flux.Flux;
import de.galan.commons.net.flux.Response;
import de.galan.commons.util.JvmUtil;


/**
 * daniel should have written a comment here.
 *
 * @author daniel
 */
public class ActualExport {

	private static final Logger LOG = Slogr.get();


	public void export(String accessToken) {
		try {
			getEmail(accessToken);
		}
		catch (Exception ex) {
			// OAuthProblemException{error='invalid_request', description='Missing parameters: access_token', uri='null', state='null', scope='null', redirectUri='null', responseStatus=0, parameters={}}
			LOG.warn("meep");
		}
		JvmUtil.terminate().message("Finished export").now();
	}


	protected String getEmail(String accessToken) throws DoctapeExportException {
		try (Response resp = Flux.request("https://api.doctape.com/v1/account").header("Authorization", "Bearer " + accessToken).get()) {
			String respString = resp.getStreamAsString();
			JsonNode tree = new ObjectMapper().readTree(respString);
			return tree.get("result").get("email").asText();
		}
		catch (Exception ex) {
			throw new DoctapeExportException("Failed getting account informations", ex);
		}
	}

}
