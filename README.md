HTemplate: An HTML Templating Tool
==================================

HTemplate provides a fast and easy way to manage your HTML pages and make broad changes across your entire site. It allows you to have one central template from which you build all pages on your website (or some, if you want to have more than one template).

USAGE
-----

Create a template file ```template.html``` containing the html that you want to have on every page. In the spaces where you want to put different content on each page, put ```***HTEMPLATE***```. Then, in a separate folder create a ```.htp``` file for each page that you want to build from the template. Make sure that the name of the ```.htp``` file matches with the name of the desired corresponding ```.html``` file.

In each ```.htp``` file, insert ```***HTEMPLATE***``` for each occurrance in the template file. Then in the below each occurance in theh ```.htp``` file, write the HTML that you want to insert into the corresponding occurrance in the template file.

Finally, simply run the JAR from the terminal with ```java -jar -htemplate-1.1.jar``` (you can also try double clicking on the file but results may vary) and follow the prompts to build your HTML pages. The template filepath is the absolute path to your ```template.html``` file, and the content filepath is the absolute path to the directory containing all of your content (```.htp```) files (nested folders/files are now supported).

NOTE: File paths must be absolute.

A prebuilt jar is provided for your convenience if you don't want to build your own

A test directory is provided in ```src/test/java/root``` with a properly formatted template and content file.

Changelog
---------

1.1 (June 15, 2017): add support for nested content subfolders

