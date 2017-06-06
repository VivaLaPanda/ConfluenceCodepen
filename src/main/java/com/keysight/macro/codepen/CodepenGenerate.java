package com.keysight.macro.codepen;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CodepenGenerate implements Macro {
  // Enum and helper that defines data types that codepen understands
  public enum validDatatype {
    html, js, css
  }
  private <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
    for (E e : enumClass.getEnumConstants()) {
      if(e.name().equals(value)) { return true; }
    }
    return false;
  }

  public String execute(Map<String, String> params, String body,
      ConversionContext conversionContext) throws MacroExecutionException {
    String codeString = "";
    String escapedCodeString = "";
    String langName = determineDataType(body, params);
    if (body != null) {
      Document doc = Jsoup.parse(body);
      codeString = doc.select("code").html();
      System.out.println("body: " + body);
      escapedCodeString = body.replace("\"", "\\\"");
    }

    Map<String, Object> context = MacroUtils.defaultVelocityContext();
    // Important note - for velocity to correctly render, 'html' a the end of the variable is
    // necessary. https://developer.atlassian.com/jiradev/jira-platform/jira-architecture/jira-templates-and-jsps/html-escaping-for-velocity-templates
    context.put("codeAsHtml", codeString);
    context.put("escCode", escapedCodeString);
    context.put("lang", langName);
    return VelocityUtils
        .getRenderedTemplate("/com/keysight/codepen/templates/codepen-reference.vm", context);
  }

  public BodyType getBodyType() {
    return BodyType.RICH_TEXT;
  }

  public OutputType getOutputType() {
    return OutputType.BLOCK;
  }


  /**
   * Decides on the datatype using the rule:
   * If user defined one, use that
   * else try to parse it from a codeblock we wrap
   * else default to html
   */
  private String determineDataType(String body, Map<String, String> params) {
    String langName = "";
    if (params.get("DataType") != null) {
      langName = params.get("DataType");
    } else {
      Pattern pattern = Pattern.compile("(?<=brush: ).*?(?=;)");
      Matcher matcher = pattern.matcher(body);
      boolean fullMatch = matcher.matches();
      if (matcher.groupCount() == 1 && !fullMatch) {
        langName = matcher.group(1);
      }
    }

    if (!isInEnum(langName, validDatatype.class)) {
      langName = "html";
    }

    return langName;
  }
}