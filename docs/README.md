# Sandrone User Guide

Sandrone is a modified version of Duke Chatbot as part of CS2103T's individual project. 
Inspired by the character Sandrone from Genshin Impact, the chatbot has a mean but loveable personality, and supports the following features:

1. Adding and deleting a task (todo, deadline and events);
2. Marking and unmarking tasks;
3. Storing / loading tasks in / from a text file;
4. Finding a specific task;
5. Adding and deleting recurring tasks.

**Getting Started**

Upon bootup, Sandrone will send a greeting message. The user may then interact with the chatbot via some specific commands.
<p align="center">
<img width="400" alt="Sandrone Greeting" src="https://github.com/user-attachments/assets/7bfc2be1-366a-4217-815e-c5d1f79cd916" />
</p>

**Command Validation**

Texts that do not fall under the list of commands will be rejected.
<p align="center">
<img width="400" alt="Invalid Command Error" src="https://github.com/user-attachments/assets/fff157e7-32b7-48d4-9caf-4ea6049a9016" />
</p>


## Detailed Guide
* Features
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
  * [`sync`](#sync) -- adds recurring tasks to active task list.
* [Saving and loading data](#storage)
* [Recurring Tasks](#recurring_tasks)

---

## Command guide

<a name="help"></a>
### help
Shows a list of all available commands and their usage formats.
* **Format:** `help`

<a name="bye"></a>
### bye
Sandrone will send a farewell message before closeing the application in a promptly.
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

<a name="sync"></a>
### sync
calls on task generators to create an instance of the task. Read more on how this works under the [**Recurring Tasks**](#recurring_tasks) section.
* **Format:** `sync`

<a name="storage"></a>
## Saving and Loading Data
Data is saved automatically every time a task or recurring task is added, deleted, marked or unmarked.

Tasks and Recurring Tasks are stored in text files: _sandrone_tesk_list.txt_ and _sandrone_task_generator_list.txt_ respectively. 

Both files are kept in a _data_ folder that is created in the same folder as the .jar file.

The two text files are loaded from the folder upon initialization of the app.

<a name="recurring_tasks"></a>
## Recurring Tasks
Recurring tasks are implemented using a corresponding generator for each type of task. These generators store a LocalDate called nextInitDate that indicates
the next date a task is to be added to the list of tasks.

Every Recurring Task (generator) stores a blueprint of the task which it uses to generate instances of it as a recurring task.

There are plans for automating this process in the future, but it is done manually now by the user -- The generator creates an instance of this task with the `sync` command, which generates an instance of the task for all tasks with their next initialization date not set to some date after today, and advances the nextInitDate.

