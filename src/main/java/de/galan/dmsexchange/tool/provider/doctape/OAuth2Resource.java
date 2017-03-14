package de.galan.dmsexchange.tool.provider.doctape;

import static java.nio.charset.StandardCharsets.*;
import static org.apache.commons.lang3.StringUtils.*;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

import de.galan.commons.logging.Slogr;


/**
 * daniel should have written a comment here.
 *
 * @author daniel
 */
@Path("/dms-exchange/auth2")
public class OAuth2Resource {

	private static final Logger LOG = Slogr.get();


	@GET
	@Produces("text/plain")
	public String getUser(@Context UriInfo uriInfo, @QueryParam("access_token") String accessToken) throws IOException {
		//uriInfo.getPathSegments()
		LOG.info("access_token:'{}'", accessToken);
		if (isNotBlank(accessToken)) {
			// store for later calls
			File file = new File("access-token");
			file.getParentFile().mkdirs();
			FileUtils.write(file, accessToken, UTF_8);
		}

		new ActualExport().export(accessToken);

		// https://my.doctape.com/oauth2?scope=account.read+file.read&response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A8765%2Fdms-exchange%2Fauth&client_id=0006a3e9-7b7f-454d-93f9-f5a80648e535&oa2fc=d35ebc69-68f5-493e-a3b4-e6f7c3c331da
		// {"error":"unauthorized_client","error_description":"wrong application type for this response_type"}

		// http://localhost:8765/dms-exchange/auth#access_token=69603d04-8cef-4f29-8023-68fc3cdbc27f&token_type=Bearer&expires_in=157680000&scope=account.read%20file.read

		//OAuthAuthzResponse oar = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
		//String code = oar.getCode();

		/*
			try (Response resp = Flux.request("https://api.doctape.com/v1/account").header("Authorization", "Bearer " + accessToken).get()) {
				String respString = resp.getStreamAsString();
				LOG.info("{}", respString);
			}
			catch (Exception ex) {
				LOG.error("arg", ex);
			}
		 */
		/*
			//[[[
			OAuthClientRequest request = OAuthClientRequest
					.tokenLocation("https://my.doctape.com/oauth2/token")
					.setGrantType(GrantType.AUTHORIZATION_CODE)
					.setClientId("0006a3e9-7b7f-454d-93f9-f5a80648e535")
					//.setClientSecret("not required/used by type 'client'")
					.setRedirectURI("http://localhost:8765/dms-exchange")
					.setCode(accessToken)
					.buildQueryMessage();
			//]]]
			// http://localhost:8765/dms-exchange/auth2?state=anystring&access_token=xxx&token_type=Bearer&expires_in=157680000&scope=account.read%20file.read
			OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
			// https://my.doctape.com/oauth2/token?code=6711b898-52f3-455d-b579-07c753ac8edb&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalhost%3A8765%2Fdms-exchange&client_id=0006a3e9-7b7f-454d-93f9-f5a80648e535
			GitHubTokenResponse response = oAuthClient.accessToken(request, GitHubTokenResponse.class);

			request.getLocationUri();
		 */
		return "You should be redirected in a second";
	}

}
