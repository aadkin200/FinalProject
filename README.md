# FinalProject - Skill Distillery Full Stack Web Programming Bootcamp -

# Overview
 After a multi-round interview and test, extensive pre-work, and sixteen weeks of ten hour days, weekend projects, pair programming, OCA mock exams, individual, pair, and group projects a Midterm and well over ten languages and front end framework programs covered extensively, it is debatable if the sheer amount of material, or lessons learned are greater. It is with great pride we present to you our final full stack group project, created and implemented by Alex Adkins, Brandon Stine, Kim Nettleton, and Rachel Richardson.

## TrailNutz
 share your adventures with friends or learn about a trail near you with this dynamic user-friendly application. Designed with outdoor enthusiasts in mind, TrailNutz is unique in that it is user driven. Rather than picking and choosing which information is truly useful from a database of outdated topographical maps, you can learn about a hike from people like you who have recently traveled the same, yet still less beaten path. Aesthetic and functional, educational and useful; enjoy interacting with your friends on TrailNutz as you plan your next getaway!

# Endpoints - needs to be updated :) 

| Method | URI                | Request Body | Response Body |
|--------|--------------------|--------------|---------------|
| GET    | `/api/trails`      |   List All Trails    | Collection of representations of Trails
| GET    | `/api/trails/{name}`      |  List Trails By Name  | Single representations of Trails
| POST    | `/api/trails`      |    Add a Trails    | Add to the Collection of representations of Trails
| PUT    | `/api/trails`      |    Update a Trails   | Update a single representation in the Collection of Trails
| DELETE    | `/api/trails/{id}`      |    Delete a Record  | Remove a single representation from the Collection of Trails

# Technologies
This application is built on a strong java foundation paired with JPA entity creation and mapping through a Spring REST API. This project differs from the Midterm greatly, as so much of the CRUD operation was implemented using Javascript, Angular, and TypeScript. This allowed us greater view manipulation for the user without overloading true database manipulation. Instead we relied on a disabled or enabled view for many requests. However, where necessary, users and administrators are still able to complete SQL database queries and CRUD operations. Admin and Enabled user access differ based on their level of credentials, and we are proud of this added security and credibility. An administrator is able to review activity and revise trail entries as well as user comments.

# Lessons Learned
 How do four people in a virtual group articulate the culmination of a truly intense program? The biggest lesson through the entire program is to never hesitate to remind yourself why you started, and remain clear on your vision of the end product. One of the biggest lessons on this project specifically is the intricate logic behind giving different users different levels of access. A unique lesson is a bit more broad than coding itself; things on the internet are rarely truly gone when you click delete. So much of what we see when using a website is based on an enabled or disabled view. This is a great reminder to be mindful of how we design websites, and also how we use them.
