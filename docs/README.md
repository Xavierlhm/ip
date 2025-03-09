# Tracker User Guide

![Ui.png](Ui.png)

## Overview
Tracker is a JavaFX-based application to help users keep track of their day-to-day tasks. 

## Getting Started

### Prerequisites
Ensure that you have: 
* Java 17 or later versions of Java installed
* JavaFX dependencies included in your runtime environment

### Installation
1. Download the latest JAR file in the [Releases page](https://github.com/Xavierlhm/ip/releases).
2. Navigate to the folder and open a terminal in that directory.
3. Run the application using the following command:
   
   `java -jar tracker.jar`
4. Once the application launches, you can start using it to track your tasks!

## Command Guide

### Adding todos

Todo tasks do not need dates.

Command Usage: `todo <description>`

Example: `todo eat`

Example Output:
```
eat
```

### Adding deadlines

Deadline tasks includes a deadline that the user has to take note of.

Command Usage: `deadline <description> /by <yyyy-MM-dd HHmm>`

Example: `deadline return book /by 2025-02-03 1800`

Example Output:
```
return book (by: Feb 03 2025, 6:00pm)
```

### Adding events

Event tasks includes two dates: from and to, to specify the event duration.

Command Usage: `event <description> /from <start> /to <end>`

Example: `event project /from 2025-02-03 1800 /to 2025-02-03 1900`

Example Output:
```
project (from: Feb 03 2025, 6:00pm to: Feb 03 2025, 7:00pm)
```

### Mark Tasks

Mark specific tasks as completed.

Command Usage: `mark <index>`

Example: `mark 1`

Example Output:
```
Nice! I've marked this task as done:
[T][X] eat
```

### Unmark Tasks

Unmark specific tasks as uncompleted.

Command Usage: `unmark <index>`

Example: `unmark 1`

Example Output:
```
OK, I've marked this task as not done yet:
[T][] eat
```

### Delete Tasks

Delete specific tasks when it is no longer required to be tracked.

Command Usage: `delete <index>`

Example: `delete 1`

Example Output:
```
Noted. I've removed this tasks:
[T][X] eat
Now you have 3 tasks in the list.
```

### Find Tasks

Find tasks with specific keyword.

Command Usage: `find <index>`

Example: `find return`

Example Output:
```
Here are the matching tasks in your list:
2. [D][X] return book (by: Feb 03 2025, 6:00pm)
```

### Exiting the Program

Stop using / Exit the program

Command Usage: `bye`

## Saving of Data
* Tasks are **automatically saved** to the hard disk after every change.
* The data is saved as a text file with the name **_Tracker.txt_**.
* The text file is located in the user's **_C:\Documents_** folder.
* **Warning:** Moving the **_Tracker.txt_** file to another directory will result in the application chatbot unable to load the data.

## Editing of Data File
* As the data is saved as a text file, it is possible for the user to manually edit the file.
* **Warning:** If the text file is modified incorrectly (e.g. wrong convention), the application chatbot may fail to load the data properly. 

## FAQ

**Q:** Can I transfer the data to another computer?

**A:** Yes you can! Simply download the application by following the steps mentioned in the **_Installation_** section above and copy the **_Tracker.txt_** file over to the new computer.

**Note:** Remember to store the **_Tracker.txt_** file in the **_C:\Documents_** folder so that the application chatbot can load the tasks!