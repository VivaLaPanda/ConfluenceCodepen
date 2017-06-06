package com.keysight.macro.codepen.rest;

import com.atlassian.confluence.plugin.services.VelocityHelperService;
import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Scanned
public class RestService {
  @ComponentImport
  private final SettingsManager settingsManager;
  @ComponentImport
  private final VelocityHelperService velocityHelperService;

  @Inject
  public RestService(SettingsManager settingsManager,
      VelocityHelperService velocityHelperService
  ) {
    this.settingsManager = settingsManager;
    this.velocityHelperService = velocityHelperService;
  }

  @GET
  @Path("help/codepen-Wrapper")
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public Response codepenWrapperHelp() {
    String title = "codepen Wrapper";
    String bodyTemplate = "/com/keysight/codepen/templates/codepen-help.vm";

    return getMacroHelp(title, bodyTemplate);
  }

  private Response getMacroHelp(String title, String bodyTemplate) {
    StringBuilder html = new StringBuilder();
    String headerTemplate = "/com/keysight/codepen/templates/help-header.vm";
    String footerTemplate = "/com/keysight/codepen/templates/help-footer.vm";

    Map<String, Object> velocityContext = velocityHelperService.createDefaultVelocityContext();
    velocityContext.put("title", title);
    velocityContext.put("baseUrl", settingsManager.getGlobalSettings().getBaseUrl());

    html.append(velocityHelperService.getRenderedTemplate(headerTemplate, velocityContext));
    html.append(velocityHelperService.getRenderedTemplate(bodyTemplate, velocityContext));
    html.append(velocityHelperService.getRenderedTemplate(footerTemplate, velocityContext));

    return Response.ok(new RestResponse(html.toString())).build();
  }
}