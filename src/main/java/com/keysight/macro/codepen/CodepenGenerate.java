package com.keysight.macro.codepen;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import java.util.Map;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CodepenGenerate implements Macro {
  // Enum and helper that defines data types that codepen understands

  public String execute(Map<String, String> params, String body,
      ConversionContext conversionContext) throws MacroExecutionException {
    String codeString = ""; // The text to be printed in Confluence
    String cssCode = "";
    String htmlCode = "";
    String jsCode = "";
    System.out.println("Body: " + body);

    // Extract all of the needed info from the body.
    if (body != null) {
      codeString = body;

      // Jsoup parsing:
      // Translates to grab all pre tags with a data-syntaxhighlighter-params attribute that is
      // equal to a value starting in brush: [whateverlanguage];
      // The selector grabs the first matching element, and stores it's html body after escaping it
      // We don't have a css escaper, and the html one works fine for css.
      Document doc = Jsoup.parse(body);
      cssCode = StringEscapeUtils.escapeHtml(safeSelect(doc, "css"));
      htmlCode = StringEscapeUtils.unescapeHtml(safeSelect(doc, "xml"))
          .replaceAll("\"", "&quot;")
          .replaceAll("\'", "&apos;");
      jsCode = StringEscapeUtils.escapeJavaScript(safeSelect(doc, "js"));
    }

    Map<String, Object> context = MacroUtils.defaultVelocityContext();
    // Important note - for velocity to correctly render, 'html' a the end of the variable is
    // necessary. https://developer.atlassian.com/jiradev/jira-platform/jira-architecture/jira-templates-and-jsps/html-escaping-for-velocity-templates
    context.put("codeAsHtml", codeString);
    context.put("cssCode", cssCode);
    context.put("htmlCode", htmlCode);
    context.put("jsCode", jsCode);
    return VelocityUtils
        .getRenderedTemplate("/com/keysight/codepen/templates/codepen-reference.vm", context);
  }

  private String safeSelect(Document doc, String language) {
    Element elem = doc.select("pre[data-syntaxhighlighter-params^=brush: "+language+";]").first();
    if (elem != null) {
      return elem.html();
    }

    return "";
  }

  public BodyType getBodyType() {
    return BodyType.RICH_TEXT;
  }

  public OutputType getOutputType() {
    return OutputType.BLOCK;
  }
}