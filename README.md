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
web-scraping's goal is to recover, handle and store in a local repository, the articles published on news website or blogs.

This software is able to work with many websites, including major news websites.
To allow web-scraping get articles from web, the user has needed to store into repository informations about the template of a specific website: simply enter css selectors for article heading, summary, eyelet, author, date and text, and the software will be able to recover articles from article page or pages that listing many of them.

The articles stored into repository will be available for reading everytime user wants.

web-scraping has been tested with reference to major italian news websites like Repubblica (www.repubblica.it)
Il Corriere della Sera (www.corriere.it).


How it's done :
-------------------
web-scraping is written in Java, using external libraries to support web connection and querying.

 * Jaunt (http://jaunt-api.com)
 * Jerry (http://jaunt-api.com)
 
There is also a graphic user interface, developed using Swing and SWT libraries.

This program has been developed using many interfaces that allow to modify it very quickly and handle different type of user-interface, web-connector or repository.

License :
-------------------
Copyright 2016 retained by authors of the project.
