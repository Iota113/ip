# Sandrone User Guide

Sandrone is a modified version of Duke Chatbot as part of CS2103T's individual project. 
Inspired by the character Sandrone from Genshin Impact, this task manager chatbot has a mean but loveable personality.

## Requirements
1. Make sure you have **Java 17** or above installed on your computer.
   - Mac users please ensure you have the exact JDK version as prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html)
2. Download the latest `.jar` file from [here](https://github.com/Iota113/ip/releases/tag/A-Release).
3. Move the `.jar` file into a folder you want to use as the home folder for your app.
4. Open the app by the `.jar` file directly or via command line with the command `java -jar sandrone.jar`

## Content
* [Getting Started](#getting_started)
* [Features](#features)
  * [`help`](#help) -- returns the list of commands and their usage format
  * [`bye`](#bye) -- exits the application
  * [`list`](#list) -- prints out the list of tasks and recurring tasks
  * [`todo`](#todo) -- adds a todo task
  * [`deadline`](#deadline) -- adds a deadline task
  * [`event`](#event) -- adds an event task
  * [`delete`](#delete) -- deletes a task
  * [`mark`](#mark) -- marks a task
  * [`unmark`](#unmark) -- unmarks a task
  * [`find`](#find) -- prints out the list of tasks that contain a specific keyword
  * [`recur`](#recur) -- to be combined with todo / deadline / event to add a recurring task
  * [`drecur`](#drecur) -- deletes a recurring task
  * [`sync`](#sync) -- adds recurring tasks to active task list.
* [Saving and Loading data](#saveload)
* [Troubleshooting and Error Messages](#troubleshooting)
* [Recurring Tasks](#recurring_tasks)

---

<a name="getting_started"></a>
## Getting Started
Upon bootup, Sandrone will send a greeting message. You may then interact with her via some specific commands.

<img 
  src="https://github.com/user-attachments/assets/0d6f0973-0fe5-4ef2-bac6-9fc669a1aff9"
  alt="sandrone_greetings" 
  style="max-width: 100%; height: auto; width: 400px;" 
/>

### Sample Commands
> Here are some sample commands meant to be keyed in sequentially for you to play around with the app!

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

<a name="features"></a>
## Features

<a name="help"></a>
### `help`
Shows a list of all available commands and their usage formats.
* **Format:** `help`

<a name="bye"></a>
### `bye`
Sandrone will send a farewell message before closing the application in a promptly.
* **Format:** `bye`

<img 
  src="https://github.com/user-attachments/assets/abb32ce2-e18a-4aa3-96c2-22cded47217a"
  alt="sandrone_message_farewell" 
  style="max-width: 100%; height: auto; width: 400px;" 
/>

<a name="list"></a>
### list
Displays all current active tasks and recurring tasks in your list.
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
calls on recurring task generators to create an instance of the task. Read more on how this works under the [**Recurring Tasks**](#recurring_tasks) section.
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
## Troubleshooting and Error Messages
If you provide an invalid input, Sandrone will let you know (usually with a bit of attitude).

<img 
  src="https://github.com/user-attachments/assets/d05da2c5-4a4a-4f96-9ac1-5d0871ba40c2"
  alt="sandrone_error_invalid_command" 
  style="max-width: 100%; height: auto; width: 400px;" 
/>

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
