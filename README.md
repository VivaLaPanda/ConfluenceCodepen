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

At the top level are folders containing the plugins targeted to the various
Atlassian applications.  Within each are folders which may be intended to
contain a plugin, an instance of the Atlassian application or some arbitrary
data. The general idea is to fire up a particular version of the Atlassian application
in one of the container folders to make it easier to switch between versions.

First, move into the repository root folder and execute **atlas-run**.  That
will bring up an instance of Confluence version 6-2-0 (as defined in the pom.xml).

Next, in another command shell execute **atlas-package**.
That will package all of the plugins as defined in the local directory's pom.xml file.

To setup a new container, move into ./confluence and execute **atlas-create-confluence-plugin**.

<!--
  This documentation mirrors that in the codepen-help.vm template
  in the resources folder.
  If you edit it make sure that file reflects the changes.
-->

Usage
---------------

This macro can be used to wrap a code macro and provide a link to Codepen
to allow readers of your documentation to try out the code you include.
The macro only works for CSS, JS, and HTML.

To use the macro, simply place it down. Within the macro body place
macros with code highlighting for HTML, JS, and CSS. You must specify the
language in the code block macro, but if you do not include one of the code
macros the codepen macro will simply use the blocks provided. Any
additional blocks will be rendered but will not effect the pen generated by the macro.

#### Normal Use Case

###### Input
![Input](http://i.imgur.com/G2x564i.png)

###### Confluence Output
![Confluence Output](http://i.imgur.com/4LDMtMa.png)

###### Resulting Codepen
![Resulting Codepen](http://i.imgur.com/5TPKuqY.png)


#### Ommitting Blocks

###### Input
![Input](http://i.imgur.com/8Zm7n2W.png)

###### Confluence Output
![Confluence Output](http://i.imgur.com/3PeCjks.png)

###### Resulting Codepen
![Resulting Codepen](http://i.imgur.com/kdvillr.png)


#### Extra Blocks

###### Input
![Input](http://i.imgur.com/tCc8Cl2.png)

###### Confluence Output
![Confluence Output](http://i.imgur.com/J7HvGlb.png)

###### Resulting Codepen
![Resulting Codepen](http://i.imgur.com/2YjAIoN.png)
