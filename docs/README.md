# 1.Introduction
Duke is a chat bot that helps user manage their task list.
It has the ability to create, read, update and delete a task 
in the task list depending on the command given by the user.  
![](https://i.ibb.co/9Gnmkkz/duke.png)
# 2.Requirement  
Ensure you have Java `11` installed.

# 3.Features 
**Command Format**
* Words in UPPER_CASE are the parameters to be supplied by the user. e.g in 
`todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter and can be used as `todo read book`. 
* For date and time format in `dd/MM/YYYY HHmm`, `dd` represents day, `MM` represents month,
`YYYY` represents year, `HH` represents hour and `mm` represents minute. E.g. `12/12/2019 2200`. 
* Word named `INDEX` is the parameter to be supplied by the user and they have to be integer number. eg. 
`delete INDEX`can be used as `delete 3`.
### 3.1. Adding a task: `todo`, `deadline`, `event`  
    
**1.** Adds a ToDo task with the given description into tasklist.  
Format: `todo TASK_DESCRIPTION`  

Example:

  * `todo read book` 
  * `todo run`

<br/>

**2.** Adds a deadline task with the given description into tasklist.  
Format: `deadline TASK_DESCRIPTION /by dd/MM/YYYY HHmm`

Example:
  * `deadline read book /by 12/12/2019 1300`
  * `deadline run /by 12/12/2019 1200`  

<br/>

**3.** Adds an event task with the given description into tasklist.  
Format: `event TASK_DESCRIPTION /at dd/MM/YYYY HHmm`    
  
Example:  
  * `deadline read book /by 12/12/2019 1300`
  * `deadline run /by 12/12/2019 1200`  
<br/>  
<br/>  

### 3.2. Deleting a task: `delete`

Deletes a task with the specified index from the tasklist.  
Format: `delete INDEX`

Example:  
  
  * `delete 3`  
<br/>    
<br/>  
  
### 3.3. Listing tasklist: `list`
Prints the list of tasks that are stored in the task list.  
Format:`list`   
<br/>    

### 3.4. Finding tasks that match keyword: `find`  
Prints a list of tasks that matches keyword given by user.  
Format:`find KEYWORD`    

Example:  
  
  * `find read`  

  <br/>  
  
### 3.5. Mark a task as done: `done`   
Mark the specified task in the task list as done.    
Format:`done INDEX`      

Example:  
  
  * `done 2`   

<br/>    

### 3.6. Exiting: `bye`   
Prints goodbye message and store updated task list in storage file.    
Format:`bye`      

<br/> 

### 3.7. Gets statistic report: `statistics`   
Prints statistic report on the different type of task to do and percentage of work done.    
Format:`statistics`    