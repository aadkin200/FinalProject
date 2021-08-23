# FinalProject - Skill Distillery Full Stack Web Programming Bootcamp -

# Overview
 After a multi-round interview and test, extensive pre-work, and sixteen weeks of ten hour days, weekend projects, pair programming, OCA mock exams, individual, pair, and group projects a Midterm and well over ten languages and front end framework programs covered extensively, it is debatable if the sheer amount of material, or lessons learned are greater. It is with great pride we present to you our final full stack group project, created and implemented by Alex Adkins, Brandon Stine, Kim Nettleton, and Rachel Richardson.

## TrailNutz
 share your adventures with friends or learn about a trail near you with this dynamic user-friendly application. Designed with outdoor enthusiasts in mind, TrailNutz is unique in that it is user driven. Rather than picking and choosing which information is truly useful from a database of outdated topographical maps, you can learn about a hike from people like you who have recently traveled the same, yet still less beaten path. Aesthetic and functional, educational and useful; enjoy interacting with your friends on TrailNutz as you plan your next getaway!

# Endpoints - needs to be updated :)

| Method | URI                | Request Body | Response Body |
|--------|--------------------|--------------|---------------|
| POST    | `/api/register`      |   New User Account    | Create a new User and Profile
| GET    | `/api/authenticate`      |   User Login   | Validate Credentials and Log User in
| GET    | `/api/trail/comment/{commentId}`      |   Reply to a Comment    | Add a response to a comment posted by another user
| POST    | `/api/trail/{trailId}/comment`      |   Add a Comment to a Trail Page    | Create new comment and post it to a trail's page
| PUT    | `/api/trail/{trailId}/comment`      |  Update Comment    | Edit and update comment on a trail page
| DELETE    | `/api/trail/{trailId}/comment/{commentId}`      |   Delete Comment    | Remove a comment you posted
| GET    | `/api/trail`      |   List All Trails    | Collection of representations of Trails
| GET    | `/api/trail/{trailId}`      |   List a Trail by Id    | Single result based off of Id search
| POST    | `/api/trail`      |  Add a Trail  | Create a new Single Trail
| PUT    | `/api/trail`      |    Update a Trail    | Edit information for a Trail
| PUT    | `/api/trail/{trailId}`      |    Remove a Trail   | Remove the record for a single trail
| DELETE    | `/api/traildetails/difficulty` OR 'routetype' OR 'amenity'      |    Include details | Retrieve various details about a Trail
| POST    | `/api/trail/{trailId}/trailimg`       |    Add Image | Add an Image to the page for a trail
| DELETE    | `/api/trail/{trailId}/trailimg/{trailImgId}`       |    Remove photo  | Remove an image for a Trail
| POST    | `/api/trail/{trailId}/trailresources`       |    Add a Resource | Create a new resource for a Trail
| DELETE    | `/api/trail/{trailId}/trailresources/{trailResourceId}`      |    Remove Resource | Remove a resource for a Trail
| GET    | `/api/allusers`       |    Admin can View all Users | Admin can retrieve a list of all users
| PUT    | `/api/user`       |    Update User | User can update their information
| DELETE    | `/api/user/{userId}`       |    User has account disabled | Admin can disable any user and a user can disable their own account
| PUT    | `/api/user/{userId}`       |    Admin can enable other Users | Admin can enable access for users


# Technologies
This application is built on a strong java foundation paired with JPA entity creation and mapping through a Spring REST API. This project differs from the Midterm greatly, as so much of the CRUD operation was implemented using Javascript, Angular, and TypeScript. This allowed us greater view manipulation for the user without overloading true database manipulation. Instead we relied on a disabled or enabled view for many requests. However, where necessary, users and administrators are still able to complete SQL database queries and CRUD operations. Admin and Enabled user access differ based on their level of credentials, and we are proud of this added security and credibility. An administrator is able to review activity and revise trail entries as well as user comments.

# Lessons Learned
 How do four people in a virtual group articulate the culmination of a truly intense program? The biggest lesson through the entire program is to never hesitate to remind yourself why you started, and remain clear on your vision of the end product. One of the biggest lessons on this project specifically is the intricate logic behind giving different users different levels of access. A unique lesson is a bit more broad than coding itself; things on the internet are rarely truly gone when you click delete. So much of what we see when using a website is based on an enabled or disabled view. This is a great reminder to be mindful of how we design websites, and also how we use them.
