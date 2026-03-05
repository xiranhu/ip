# User Guide - Zayne Chatbox

Zayne is a powerful, CLI-based task management assistant designed to help you track your Todos, Deadlines, and Events with speed and precision.

## Quick Start

1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest `zayne.jar` from [here](https://github.com/xiranhu/ip/releases).
3. Copy the file to the folder you want to use as the *home folder* for your Task Manager.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar zayne.jar` command to run the application.

## Adding Deadlines: `deadline`
Adds a task to the list that needs to be completed by a specific time.

Format: `deadline DESCRIPTION /by DATE_OR_TIME`

Example: `deadline return book /by Sunday`

Expected outcome:
```
Got it. I've added this task:
[D][ ] return book (by: Sunday)
Now you have 3 tasks in the list.
```

## Adding Todos: `todo`

Adds a simple task without any date or time constraints.

Format: `todo DESCRIPTION`

Example: `todo read book`

Expected outcome:
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

## Adding Events: `event`

Adds a task that occurs during a specific time period.

Format: `event DESCRIPTION /from START /to END`

Example: `event project meeting /from Mon 2pm /to 4pm`

Expected outcome:
```
Got it. I've added this task:
[E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 5 tasks in the list.
```

## Marking Tasks: `mark`

Marks a task as completed.

Format: `mark INDEX`

Example: `mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
  [T][X] read book
```

## Unmarking Tasks: `unmark`

Marks a task as not completed.

Format: `unmark INDEX`

Example: `unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

## Finding Tasks: `find`

Allows you to search for tasks by a keyword in their description.

Format: `find KEYWORD`

Example: `find book`

Expected outcome:
```
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Sunday)
```

## Listing Tasks: `list`

Displays all current tasks in your list with their status.

Format: `list`

Expected outcome:
```
 1.[T][X] read book
 2.[D][ ] return book (by: Sunday)
 3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
```

## Deleting Tasks: `delete`

Removes a specific task from your list using its index number.

Format: `delete INDEX`

Example: `delete 1`

Expected outcome:
```
Noted. I've removed this task:
  [T][ ] read book
Now you have 4 tasks in the list.
```

## Exiting the Program: `bye`

Closes the Zayne application. Your tasks are automatically saved to the data file upon exiting.

Format: `bye`

Example: `bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```

## Data Storage
Zayne data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

File Location: Your task data is stored in a plain text file located at [Home Folder]/data/zayne.txt.

Editing the Data File: Advanced users can update data directly by editing the data file.

[!WARNING]
If your changes to the data file cause its format to become invalid, Zayne will ignore the corrupted lines and may start with an empty task list.

Data Format: The data is stored in a pipe-separated format for easy reading:

Todo: T | isDone | description

Deadline: D | isDone | description | by

Event: E | isDone | description | from | to