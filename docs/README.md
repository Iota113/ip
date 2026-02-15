# Sandrone User Guide

// Product screenshot goes here

Sandrone is a modified version of Duke Chatbot as part of CS2103T's individual project. 
Inspired by the character Sandrone from Genshin Impact, the chatbot has a mean but loveable personality, and supports the following functions:

1. Adding and deleting a task (todo, deadline and events);
2. Marking and unmarking tasks;
3. Storing / loading tasks in / from a text file;
4. Finding a specific task;
5. Adding and deleting recurring tasks. 

## Command List
* bye -- to exit the application
* list -- print out the list of tasks and recurring tasks
* todo -- add a todo task
* deadline -- add a deadline task
* event -- add an event task
* find -- print out the list of tasks that contain a specific keyword
* recur -- to be combined with todo / deadline / event to add a recurring task


### Command Format
// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Recurring Tasks
Recurring tasks are implemented using a corresponding generator for each type of task. These generators store a LocalDate that indicates
the next date a task is to be added to the list of tasks; and advances said date when such a task is added.

Every generator stores a blueprint of the task which it uses to generate instances of it as a recurring task.
