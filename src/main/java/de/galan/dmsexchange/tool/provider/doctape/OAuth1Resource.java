package de.galan.dmsexchange.tool.provider.doctape;

import static java.nio.charset.StandardCharsets.*;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.common.io.Resources;


/**
 * Takes the first response and redirects to a second resource with the parameter put as query parameters instead of
 * fragments.
 */
@Path("/dms-exchange/auth")
public class OAuth1Resource {

	@GET
	@Produces("text/html")
	public String getUser() {
		try {
			return Resources.toString(Resources.getResource(getClass(), "redirect.html"), UTF_8);
		}
		catch (IOException ex) {
			return null;
		}
	}

}
