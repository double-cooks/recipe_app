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

http://confirmation/id
- Page to review a recipe, after it is added, with options to edit or delete the recipe

http://recipe/edit/id
- Returns a form with all input fields prepopulated to allow user to edit a recipe

http://recipe/delete/id
- Delete route to remove a recipe, its ingredients and steps

http://recipe/aboutus
- About Us page
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
