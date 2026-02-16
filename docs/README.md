# Sandrone User Guide

<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/488be4ac-40be-4857-93ca-0f64c610aa7e" />

Sandrone is a modified version of Duke Chatbot as part of CS2103T's individual project. 
Inspired by the character Sandrone from Genshin Impact, the chatbot has a mean but loveable personality, and supports the following functions:

1. Adding and deleting a task (todo, deadline and events);
2. Marking and unmarking tasks;
3. Storing / loading tasks in / from a text file;
4. Finding a specific task;
5. Adding and deleting recurring tasks. 

## Quick start
* [`help`](#help) -- returns the list of commands and their format
* [`bye`](#bye) -- to exit the application
* [`list`](#list) -- print out the list of tasks and recurring tasks
* [`todo`](#todo) -- add a todo task
* [`deadline`](#deadline) -- add a deadline task
* [`event`](#event) -- add an event task
* [`delete`](#delete) -- deletes a task
* [`mark`](#mark) -- marks a task
* [`unmark`](#unmark) -- unmarks a task
* [`find`](#find) -- print out the list of tasks that contain a specific keyword
* [`recur`](#recur) -- to be combined with todo / deadline / event to add a recurring task
* [`drecur`](#drecur) -- deletes a recurring task

---

## Command guide

<a name="help"></a>
### help
Shows a list of all available commands and their usage formats.
* **Format:** `help`

<a name="bye"></a>
### bye
Closes the application.
* **Format:** `bye`

<a name="list"></a>
### list
Displays all current tasks in your list, including their status, type, and recurring status.
* **Format:** `list`

<a name="todo"></a>
### todo
Adds a basic task without any specific date or time.
* **Format:** `todo <description>`
* **Example:** `todo laundry`

<a name="deadline"></a>
### deadline
Adds a task that needs to be done by a specific date.
* **Format:** `deadline <description> /by <yyyy-MM-dd>`
* **Example:** `deadline MA2202 Homework 2 /by 2026-02-20`

<a name="event"></a>
### event
Adds a task that occurs between two dates.
* **Format:** `event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>`
* **Example:** `CS2103T project meeting /from 2026-02-18 /to 2026-02-18`

<a name="delete"></a>
### delete
Removes a task from the list using its index number.
* **Format:** `delete <index>`
* **Example:** `delete 3`

<a name="mark"></a>
### mark
Marks a specific task as completed.
* **Format:** `mark <index>`
* **Example:** `mark 1`

<a name="unmark"></a>
### unmark
Reverts a completed task back to "not done".
* **Format:** `unmark <index>`
* **Example:** `unmark 1`

<a name="find"></a>
### find
Searches for tasks that contain the specified keyword in their description.
* **Format:** `find <keyword>`
* **Example:** `find CS`

<a name="recur"></a>
### recur
Prefixes a task to make it repeat at a set interval (currently set to weekly).
* **Format:** `recur <todo/deadline/event>`
* **Example:** `recur deadline CS2103T Quiz /by 2026-02-20`

<a name="drecur"></a>
### drecur
Stops a task from recurring by deleting the recurring task.
* **Format:** `drecur <index>`
* **Example:** `drecur 1`

## Recurring Tasks
Recurring tasks are implemented using a corresponding generator for each type of task. These generators store a LocalDate that indicates
the next date a task is to be added to the list of tasks; and advances said date when such a task is added.

Every generator stores a blueprint of the task which it uses to generate instances of it as a recurring task.
