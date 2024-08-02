<!-- Top Image -->
<p align="center">
  <img src="https://i.ibb.co/x3qtZzr/Screenshot-2024-06-30-130735.png" alt="Library Image" width="100%">
</p>

## Update

- **1.0** - The Library Management System is now a dynamic web application that allows users to manage books and members efficiently through a user-friendly web interface. Built using Java, Maven, JSP, and servlets, this application enhances the library management experience compared to the earlier static version.

A simple Java-based library management system that allows users to manage books and members efficiently.

## Features

- **Add Book** - Add new books to the library database.
  - Allows users to add new books with details such as title, author, and ISBN.
  
- **Remove Book** - Remove existing books from the library.
  - Enables users to delete books from the library database based on ISBN.

- **Add Member** - Register new members.
  - Registers new members with their name and assigns a unique member ID.

- **Search Book by ISBN** -  Find books by their unique ISBN.
  - Searches and retrieves books from the library based on their ISBN.

- **Search Member by ID** - Find members by their unique ID.
  - Looks up members in the system using their assigned member ID.

- **Borrow and Return Books** - Manage borrowing and returning books by members.
  - Tracks borrowing and returning transactions between members and books.

- **Show Available Books** - Display books currently available for borrowing.
  - Shows a list of books that are currently available in the library.

- **Show Member Details** - View detailed information about a member.
  - Provides comprehensive details about a specific member including borrowed books.

- **Exit** - Terminate the program.
  - Quits the library management system application.

## Setup Instructions

Follow these steps to set up the Library Management System on your local machine.

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/library-management-system.git
   cd library-management-system

### Import Existing Maven Project into Eclipse
1. Open **Eclipse IDE**.
2. Go to `File` > `Import`.
3. Select `Maven` > `Existing Maven Projects` and click `Next`.
4. Browse to the location of the cloned repository and select it.
5. Click `Finish` to import the project.

### Install Apache Tomcat Server
1. Download Apache Tomcat from the [official website](https://tomcat.apache.org/).
2. Extract the downloaded ZIP file to your desired location.

### Add Tomcat Server to Eclipse
1. Open **Eclipse IDE**.
2. Go to `Window` > `Preferences`.
3. In the Preferences window, expand `Server` and click on `Runtime Environments`.
4. Click `Add`, select `Apache Tomcat vX.X Server`, and click `Next`.
5. Browse to the location where you extracted Tomcat and click `Finish`.

### Initialize the Tomcat Server
1. In Eclipse, go to the `Servers` tab (you can open it via `Window` > `Show View` > `Servers`).
2. Right-click in the `Servers` tab and select `New` > `Server`.
3. Choose `Apache Tomcat vX.X Server`, select the installed Tomcat server, and click `Next`.
4. Add your project to the server and click `Finish`.
5. Right-click on the server in the `Servers` tab and select `Start` to run the server.

### Access the Application
1. Open a web browser and go to `http://localhost:8080/library-management-system` or run server from index.jsp.
2. You can now use the Library Management System.

## Note
Ensure that you have JDK and Maven installed on your system. Configure the necessary environment variables if required.
