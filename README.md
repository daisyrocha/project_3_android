# Project Title: Hero Brawl

[Website Repo](https://github.com/IGramajoO/project_3_website)

### Contributors: 
  * [Daisy Rocha-Montanez](https://github.com/daisyrocha) 
  * [Ignacio Gramajo](https://github.com/IGramajoO)
  * [Abraham Robles](https://github.com/abeRoblesMartinez)
  * [Luis Morales](https://github.com/LuiM112)

### Description: 
 Hero Brawl is an app the revolves around collecting heroes and having them battle other heroes. The Player is to create an account in order to keep track of their efforts in the app, in terms of gaining points to obtain new heroes and their wins and losses in their battles. They will be allowed to create two teams of five or less to send to battle, it doesn't matter what's in the team who's in the team, as long as they have them. This will be functional between both in the web and andriod.
 
### Tech Stack:
  * Room
  * Retrofit2
  * JawsDB - mySQL


### API being used: 
  [Superhero API](https://www.superheroapi.com)
  

### Tentative Endpoints: 
  * GET /allUsers 
  * PUT /addTeam?userId=${userId}&teamName={$teamName} (Create new team for a user)
  * PUT /addToTeam?teamId={$teamId}&characterId=${charId} (Add a character to a team)
  * GET /getTeams?userId={$userId} (Get teams for a specific user)
  * GET /getTeam?userId=${userId}&teamId={$teamId} (Get a specific team from a user)
  * POST /login?username={$username}&password={$password}
  * POST /newuser?username={$username}&password={$password}
  * POST /logout?username={$username}
  * DELETE /deleteAccount?userId={$userId}
  * PUT /removeCharacter?teamId={$teamId}&characterId=${charId} (Remove Character from a team)
  
  
 ### Wireframe:
![Screen Shot 2022-04-10 at 12 43 10 AM](https://user-images.githubusercontent.com/72002539/162608077-73172162-7ddc-4f48-a05d-a78bb05cdb50.png) 

### ERD:
![image](https://user-images.githubusercontent.com/89751770/162643741-2254910e-aa88-46a6-9192-9778289fcdd2.png)
                

### Resources: 
  * (TBA)
  * (TBA)
  * (TBA)               
  * (TBA)

### Status: 
  * (In progress)
