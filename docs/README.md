# User Guide
Duke is a chat bot that helps user manage their task list.
It has the ability to create, read, update and delete a task 
in the task list depending on the command given by the user.
## Features 

#### Feature 1 - Adding a task 
There are 3 types of tasks that can be added into the tasklist - `deadline`, `event`, `todo`
    
## Usage
**`todo`**: Type "**todo {Your task description}**" into the dialog box.    

This will add a ToDo task with the given description into tasklist.  

<br/>

**`deadline`**: Type "**deadline {Your task description} /by dd/MM/YYYY HHmm**" into the dialog box.    

This will add a Deadline task with the given description, date and time into tasklist.  

<br/>

**`event`**: Type "**event {Your task description} /by dd/MM/YYYY HHmm**" into the dialog box.    

This will add an Event task with the given description, date and time into tasklist.  

<br/>

### Example of usage:   

todo watch spiderman 3  
deadline Team Project /by 19/12/2019 2359  
event NUS career fair /at 12/12/2019 1200   

### Expected outcome:

`todo watch spiderman 3`:    
  
![](https://i.ibb.co/r3Mh85f/todo.png)  

`deadline Team Project /by 19/12/2019 2359`

`event NUS career fair /at 12/12/2019 1200`