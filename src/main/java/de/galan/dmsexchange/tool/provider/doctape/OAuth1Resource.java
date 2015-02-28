package de.galan.dmsexchange.tool.provider.doctape;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import de.galan.commons.logging.Logr;


/**
 * Takes the first response and redirects to a second resource with the parameter put as query parameters instead of
 * fragments.
 *
 * @author daniel
 */
@Path("/dms-exchange/auth")
public class OAuth1Resource {

	private static final Logger LOG = Logr.get();


	@GET
	@Produces("text/html")
	public String getUser() {
		try {
			return Resources.toString(Resources.getResource(getClass(), "redirect.html"), Charsets.UTF_8);
		}
		catch (IOException ex) {
			return null;
		}
	}

}
