![keysight-banner-560x274.png](https://bitbucket.org/repo/kMMaq9R/images/227789766-keysight-banner-560x274.png)

This repository is a plugin for easily making Codepen sandboxes of snippets in Confluence
documentation, and was developed by Keysight Technologies.

Keysight Technologies is an industry leader providing solutions in the area of
electronic test and measurement.

If you encounter issues with this plugin, contact Adrian Smith via adrian@smithdev.io.
Pull Requests welcome.

Getting Started
---------------
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