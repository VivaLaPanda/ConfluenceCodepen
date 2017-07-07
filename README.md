# Confluence Codepen

Introduction
------------
This repository is a plugin for easily making Codepen sandboxes of snippets in Confluence
documentation, and was developed by Adrian Smith.

This code relies on several external libraries:

* [Jsoup](https://jsoup.org/) - A great library for JQuery style HTML processing in Java

* [Atlassian SDK](https://developer.atlassian.com/docs/getting-started/downloads) - Used to interact with Confluence

* [Apache Commons Lang](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3) - Used for HTML parsing

If you encounter issues with this plugin, contact Adrian Smith via adrian@smithdev.io.
Pull Requests welcome.

Building from Source
--------------------
Prerequisites: The Atlassian SDK

The lastest work is on the development branch.  The master branch represents the code for the last release.

First, move into the codepen folder and execute **atlas-run**.  That
will bring up an instance of Confluence.

Next, in another command shell execute **atlas-package**.
That will package all of the plugins as defined in the local directory's pom.xml file.

<!--
  This documentation mirrors that in the codepen-help.vm template
  in the resources folder.
  If you edit it make sure that file reflects the changes.
-->

Usage
---------------

The **Codepen** macro is used to wrap a related set of Confluence **Code** macros
and provide a special link to [Codepen](https://codepen.io) which will allow readers
of the page to experiment with the code in a live sandbox. The macro will sent the
contents from the first CSS **Code** macro, the first Javascript **Code** macro
and the first HTML **Code** contained in it's body.  All other macros in the body
will be rendered as normal on the Confluence page, but will not be included in the
special link to Codepen.

As the **Codepen** macro is looking for the first HTML, CSS and Javascript **Code**
macros, the language for those instances must be explicitly specified in the **Code**
macro (or that block will be ignored).

if you omit one of the code macros the codepen macro will simply use the 1 or 2 blocks provided, and the codepen page generated will not have code for those languages.
 
#### Normal Use Case

###### Input
![Input](./src/main/resources/images/docs/three-blocks-editor.png)

###### Confluence Output
![Input](./src/main/resources/images/docs/three-blocks-view.png)

###### Resulting Codepen
![Input](./src/main/resources/images/docs/three-blocks-codepen.png)


#### Omitting Blocks

###### Input
![Input](./src/main/resources/images/docs/two-blocks-editor.png)

###### Confluence Output
![Input](./src/main/resources/images/docs/two-blocks-view.png)

###### Resulting Codepen
![Input](./src/main/resources/images/docs/two-blocks-codepen.png)


#### Extra Blocks

###### Input
![Input](./src/main/resources/images/docs/extra-blocks-editor.png)

###### Confluence Output
![Input](./src/main/resources/images/docs/extra-blocks-view.png)

###### Resulting Codepen
![Input](./src/main/resources/images/docs/extra-blocks-codepen.png)