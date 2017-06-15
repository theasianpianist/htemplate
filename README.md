HTemplate
An HTML Templating Tool

HTemplate provides a fast and easy way to manage your HTML pages and make broad changes across your entire site. It allows you to have one central template from which you build all pages on your website (or some, if you want to have more than one template).

USAGE:
Create a template file ```template.html``` containing the html that you want to have on every page. In the spaces where you want to put different content on each page, put "***HTEMPLATE***." Then, in a separate folder create a ```.htp``` file for each page that you want to build from the template. Make sure that the name of the ```.htp``` file matches with the name of the desired corresponding ```.html``` file.

In each ```.htp``` file, insert "*\**HTEMPLATE***" for each occurrance in the template file. Then in the below each occurance in theh ```.htp``` file, write the HTML that you want to insert into the corresponding occurrance in the template file.

Finally, simply run the JAR and follow the prompts to build your HTML pages.

NOTE: File paths must be absolute.

A prebuilt jar is provided for your convenience if you don't want to build your own

