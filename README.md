web-scraping
=========================================

Authors :
-------------------
Created by:

 * Marco De Toma - [Github](https://github.com/detomarco)
 * Gianluca Filippone - [Github](https://github.com/Gianlufil)
 * Alessandro D'Errico - [Github](https://github.com/lamoichenzio)


What is :
-------------------
web-scraping purpouse is to recover and store in a local repository articles published on news website or blogs.

This software is able to work with many websites, including major news websites.
To alow web-scraping get articles from web, the user needs to store into repository informations about the template
used by the selected website. Simply enter css selectors for article heading, summary, eyeley and text, and the software
will be able to get articles from both article page or pages that list many of them.

Article stored into repository will be available for reading everytime user wants.

web-scraping has been developed and tested with reference to major italian news websites like Repubblica (www.repubblica.it),
Il Corriere della Sera (www.corriere.it).

How it's done :
-------------------
web-scraping is written in Java, using external libraries to support web connection and querying.

 * Jaunt (http://jaunt-api.com)
 * Jerry (http://jaunt-api.com)
 
There is also a graphic user interface, developed using Swing and SWT libraries.


License :
-------------------
Copyright 2016 retained by authors of the project.
