# Sandrone User Guide

<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/488be4ac-40be-4857-93ca-0f64c610aa7e" />

Sandrone is a modified version of Duke Chatbot as part of CS2103T's individual project. 
Inspired by the character Sandrone from Genshin Impact, the chatbot has a mean but loveable personality, and supports the following functions:

1. Adding and deleting a task (todo, deadline and events);
2. Marking and unmarking tasks;
3. Storing / loading tasks in / from a text file;
4. Finding a specific task;
5. Adding and deleting recurring tasks. 

## Quick Start
* `help` -- returns the list of commands and their format
* `bye` -- to exit the application
* `list` -- print out the list of tasks and recurring tasks
* [`todo`](#todo) -- add a todo task
* [`deadline`](#deadline) -- add a deadline task
* [`event`](#event) -- add an event task
* `delete` -- deletes a task
* `mark` -- marks a task
* `unmark` -- unmarks a task
* `find` -- print out the list of tasks that contain a specific keyword
* `recur` -- to be combined with todo / deadline / event to add a recurring task
* `drecur` -- deletes a recurring task

## Command Guide
<a name="todo"></a>
### Todo

<a name="deadline"></a>
### Deadline

<a name="event"></a>
### Event

## Recurring Tasks
Recurring tasks are implemented using a corresponding generator for each type of task. These generators store a LocalDate that indicates
the next date a task is to be added to the list of tasks; and advances said date when such a task is added.

Every generator stores a blueprint of the task which it uses to generate instances of it as a recurring task.
