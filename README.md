# Doubly Cooked

## table of contents
* [Documents](./documents)
* [Summary](#Summary)
* [Routes](#Routes)
* [Notes](#Notes)
* [Versions](#Versions)
* [Creators](#Creators)

## Summary

Doubly Cooked is a recipe app developed for the casual home cook. It stores recipes in the cloud that can be accessed from the profile page after logging in. Recipes can be added, edited and deleted. User names must be unique and passwords are hashed.
***

## Routes
### Fullstack App routes (routes using thymeleaf)
http://confirmation/id
- Page to review a recipe, after it is added, with options to edit or delete the recipe

http://recipe/edit/id
- Returns a form with all input fields prepopulated to allow user to edit a recipe

http://recipe/delete/id
- Delete route to remove a recipe, its ingredients and steps

http://recipe/aboutus
- About Us page

### Alexa routes (routes for Alexa only)
```GET``` Routes

```/alexa/recipes``` - Returns all the recipes that are created on Doubly

```/alexa/recipes/{title}``` - Returns the first recipe that is created in the app with a given title

```/alexa/recipes/{title}/cooktime``` - Returns the cook time that for a recipe with a specific title

```/alexa/recipes/{title}/preptime``` - Returns the prep time for a recipe with a specific title

```/alexa/recipes/{title}/ingredients``` - Returns the ingredients for a given recipe by title in JSON format

```/alexa/recipes/{title}/steps``` - Returns the steps for a given recipe by by title in JSON format
***
## Notes
### Alexa controller for recipe and Json response
```@JsonManagedReference``` and ```@JsonBackReference``` annotations were used to obtain JSON repsonses. This [stack overflow](https://stackoverflow.com/questions/47693110/could-not-write-json-infinite-recursion-stackoverflowerror-nested-exception) post was used
for setting up respective associations in the model


## Creators

- [Ed.Scott Abrahamsen](https://github.com/esa2)
- [Matt Burger](https://github.com/)
- [Paolo Chidrome](https://github.com/)
- [Tisha Greenidge](https://github.com/)
- [Jhia Turner](https://github.com/)
