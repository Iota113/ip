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
<p>
<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/203a2c14-31b7-444d-abad-e268f5c3e468" />
</p>

**Command Validation**

Texts that do not fall under the list of commands will be rejected.
<p>
<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/71951252-1062-46e7-b8fa-02e55894044d" />
</p>

## Quick Start
If you're already familiar with CLI tools, here is a cheat sheet of the most common commands to get you started:

```
# Add tasks
todo CS2109S Lecture
deadline submit MA2202 Homework 2 /by 2026-02-20
event CS2103T meeting /from 2026-02-18 1400 /to 2026-02-18 1600

# Manage tasks
list
mark 1
delete 2
find CS

# Automation
recur deadline CS2103T Quiz /by 2026-02-20
sync

# Exit
bye
```

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
* [Saving and loading data](#saveload)
* [Troubleshooting & Error Messages](#troubleshooting)
* [Recurring Tasks](#recurring_tasks)

---

## Command guide

<a name="help"></a>
### help
Shows a list of all available commands and their usage formats.
* **Format:** `help`

<a name="bye"></a>
### bye
Sandrone will send a farewell message before closing the application in a promptly.
* **Format:** `bye`

<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/d66cfd84-e1f6-429d-8998-073677c9c975" />


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
Adds a task that occurs within a specific time frame.
* **Format:** `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`
* **Example:** `CS2103T project meeting /from 2026-02-18 2000 /to 2026-02-18 2100`

<a name="delete"></a>
### delete
Removes a task from the list using its 1-based index number.
* **Format:** `delete <index>`
* **Example:** `delete 3`

#### Usage Notes:
* Index Range: The index must be a positive integer within the range of the current list (e.g., if you have 5 tasks, delete 6 will fail). 
* Syncing: Deleting a task instance does not delete the recurring generator (use `drecur` for that).

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

<a name="saveload"></a>
## Saving and Loading Data
You don't need to manually save your progress. 
Sandrone automatically saves your state every time you add, delete, mark, unmark a task or used the sync command.

### Storage Location
When you run the application, Sandrone creates a `/data` folder in the same directory as your `.jar` file. Your data is split into two specialized text files:

* **`sandrone_task_list.txt`**: Stores your active one-time tasks.
* **`sandrone_task_generator_list.txt`**: Stores your recurring task blueprints.

### File Structure
```text
.
├── sandrone.jar
└── data/
    ├── sandrone_task_list.txt
    └── sandrone_task_generator_list.txt
 ``` 

### Automatic Loading
Upon startup, Sandrone automatically scans the /data folder. If existing files are found, your previous tasks and recurring generators are restored instantly. If no files exist, Sandrone starts with a clean slate.

>[!WARNING]
Manual Editing: While these are text files, manual editing is discouraged. If the formatting is corrupted, Sandrone may be unable to load your tasks correctly!

<a name="troubleshooting"></a>
## Troubleshooting & Error Messages
If you provide an invalid input, Sandrone will let you know (usually with a bit of attitude).

Refer to the table below to fix common issues:

| Issue | Typical Error Message | How to Fix                                                     |
| :--- | :--- |:---------------------------------------------------------------|
| **Empty Description** | "A task without a description? What do you expect me to do with it?" | Ensure text follows the command (e.g., `todo laundry`).        |
| **Missing Tag** | "Your deadline command is incomplete. It requires a ' /by ' tag." | Include the mandatory prefix for deadlines and events.         |
| **Invalid Index** | "Look at your list. Do you see that index? No. Because it isn't there." | Use a number corresponding to an existing item in your `list`. |
| **Wrong Date Format** | "Incorrect date format. Use yyyy-MM-dd for deadlines. I have no time for your sloppy notation" | Use the **yyyy-mm-dd** format (and **HHmm** for events).       |
| **Empty Date/Time** | "Hello? You are missing both the beginning and the end of your event." | Ensure a value follows your `/by`, `/from`, or `/to` tags.     |

<a name="recurring_tasks"></a>
## Recurring Tasks

Recurring tasks allow you to automate your schedule without cluttering your active list. 
Instead of adding a single task, Sandrone saves a **Generator** -- a blueprint that knows when the next instance of that task is due.

### How it Works
* **The Blueprint:** Each recurring task stores its description and frequency (currently weekly).
* **The `nextInitDate`:** This is the internal "checkpoint" date. Sandrone uses this to track when the next instance should be added to your active list.
* **Manual Control:** To keep your list tidy, Sandrone only adds these tasks when you tell her to.

> [!IMPORTANT]
> **Note on Syncing:** Sandrone won't automatically spam your list. You must manually use the `sync` command to generate the next batch of tasks.

### The `sync` Workflow
When you run `sync`, Sandrone checks all active Generators:
1. If the `nextInitDate` is today or in the past, an instance of that task is created in your main list.
2. The `nextInitDate` is then automatically advanced by one week.
3. If the date is still in the future, nothing happens—keeping your list clean and relevant.
