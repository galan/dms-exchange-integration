package de.galan.dmsexchange.tool.provider.doctape;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;

import de.galan.commons.logging.Slogr;


/**
 * daniel should have written a comment here.
 *
 * @author daniel
 */
@Path("/dms-exchange")
public class StatusResource {

	private static final Logger LOG = Slogr.get();


	@GET
	@Produces("text/plain")
	public String getUser(@PathParam("code") String code) {
		LOG.info("code:'{}'", code);
		//OAuthAuthzResponse oar = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
		//String code = oar.getCode();

		try {
			//[[[
			OAuthClientRequest request = OAuthClientRequest
					.tokenLocation("https://my.doctape.com/oauth2/token")
					.setGrantType(GrantType.AUTHORIZATION_CODE)
					.setClientId("0006a3e9-7b7f-454d-93f9-f5a80648e535")
					//.setClientSecret("not required/used by type 'client'")
					.setRedirectURI("http://localhost:8765/dms-exchange")
					.setCode(code)
					.buildQueryMessage();
			//]]]
			request.getLocationUri();
		}
		catch (Exception ex) {
			LOG.warn("meep");
		}
		return "You should be redirected in a second";
	}

}
