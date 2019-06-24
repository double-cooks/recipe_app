As a appUser, I want a digital place to store my recipes so that I can conveniently keep track of them.
* Create database
* Create Models
* Create Repositories
* Create controller(s)
* Utilize Spring Auth
As a appUser, I want to be able to add the details of my recipe using a simple, easy to use form so that I can save my recipes in my digital cookbook.
* Create html form
o    Utilize Thymeleaf
* Create POST route to handle form data to db process

As a appUser, I want to be able to see links to all of my recipes on a single page so that I can have a general view of all the recipes I have available.
* Create a html template 
    * Utilize Thymeleaf
* Create a GET route to pull data from db

As a appUser, I want to be able to click on an individual recipe and see the details of the recipe so that I can prepare it.
* Create a html template 
    * Utilize Thymeleaf
* Create GET route to pull data from db

As a appUser, I want to be able to make edits to the recipes I have already saved so that I can ensure my recipes are accurate.
* Create html form
    * Utilize Thymeleaf
* Create POST route to update data stored in db
As a appUser, I want to be able to delete recipes I no longer want in my cookbook so that I can keep my cookbook full of only the recipes I want and use.
* Add a delete feature to Recipes html template
* Create DELETE route to delete recipe from db

As a appUser, I want to be able to ask my Alexa to read the details of my recipe (prep time, cook time, ingredients needed and steps) so that I can follow a recipe without having to stop and use my hands to look through the recipe.
* Integrate Alexa api
* Reference Alexa api to appropriate endpoints