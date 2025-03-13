<div align="center">
<img style="" src="https://github.com/Jinkogule/Banco-Sinistro/blob/main/web/images/logo.png" width="250px;" alt=""/>
<br>

[![Release](https://img.shields.io/github/v/release/Jinkogule/Banco-Sinistro?style=for-the-badge)](https://github.com/Jinkogule/BandejApp/releases)
[![License](https://img.shields.io/github/license/Jinkogule/Banco-Sinistro?style=for-the-badge)](LICENSE)<br>
![Status](https://img.shields.io/badge/STATUS-COMPLETED-brightgreen?style=for-the-badge)
</div>

<p align="center">
  <a href="#-about-the-project">About</a> •
  <a href="#-documentation">Documentation</a> •
  <a href="#-development">Development</a> •
  <a href="#-technologies">Technologies</a> •
  <a href="#-run-the-project-locally">Run the project locally</a> •
  <a href="#-authors">Authors</a> •
  <a href="#-license">License</a>
  <br>
  <a href="./README.pt-BR.md">Português (BR)</a> •
  <a href="./README.md">English</a>
</p>

---

## 💻 About the Project

**Banco Sinistro** is a fictional software developed to help manage users' credit and debit transactions. The system functions as a digital checking account, focusing on individual financial management. Through the application, users can register as either regular users or administrators, create checking accounts, make credit and debit transactions, and view transaction records with their respective totals and current balance.

The project was created as part of the Web Development course to apply concepts such as web application development using Java (Servlet + JSP), MVC architecture, MySQL database management, DAO (Data Access Object) usage, client- and server-side data validation, and the implementation of authentication and access control for different user roles.

**Note**: This project was developed solely as an academic exercise for learning purposes and is not a real banking system.

## 📋 Documentation

-   **[Wiki](https://github.com/Jinkogule/Banco-Sinistro/wiki)**

## 🧑🏻‍💻 Development

-   **[Código fonte](https://github.com/Jinkogule/Banco-Sinistro)**
-   **[Issue Tracking](https://github.com/Jinkogule/Banco-Sinistro/issues)**

## 🛠 Technologies

### **Back-End (Web Application)**

-   **[Java (Servlet + JSP)](https://www.oracle.com/br/java/technologies/downloads/)**

### **Database**

-   **[MySQL 8.0.3](https://www.mysql.com/)**

## ⚙ Run the Project Locally

### **Prerequisites**

Before you begin, make sure to:

- Install **[Git](https://git-scm.com/)**.
- Install **[MySQL Server 8.0.3](https://dev.mysql.com/downloads/mysql/)** (or a compatible version).
- Run the **[database script](https://github.com/Jinkogule/Banco-Sinistro/blob/main/financeiro.sql)** to create the required schema and tables.
- Install **[JDK 15](https://www.oracle.com/br/java/technologies/downloads/#java15)** (or a compatible version) and set the `JAVA_HOME` variable with the correct JDK path.
- Download **[MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)** to connect the project to MySQL.
- Install **[Apache NetBeans IDE](https://netbeans.apache.org/front/main/index.html)** to run the project.
- Configure a compatible web server (e.g., **[Tomcat](https://tomcat.apache.org/)** or **[GlassFish](https://javaee.github.io/glassfish/)**) in Apache NetBeans.

### **Running the Application**

1. **Clone this repository**
```bash
git clone https://github.com/Jinkogule/Banco-Sinistro
```

2. **Open the project in Apache NetBeans IDE**
   - In the menu, go to File > Open Project and select the project folder.

3. **Configure the database connection**
   - If needed, edit the `conexaoBD.java` file (located in `src/java/model/conexaoBD.java`).
   - Adjust the MySQL connection details.

4. **Add MySQL Connector/J to the project libraries**
   - In NetBeans, right-click the project → Properties → Libraries.
   - In the Compile (Compile-time Libraries) tab, click the **"+"** next to Classpath.
   - Select Add JAR/Folder and choose the `.jar` file for **MySQL Connector/J**.
   - Confirm and apply the changes.

5. **Run the application**
   - In Apache NetBeans, right-click the project and select Run.

## ✒ Authors

<table>
  <tr>
    <td align="center">
      Lucas Pimenta
      <br>
      <a href="https://github.com/Jinkogule">
        <img src="https://avatars.githubusercontent.com/u/52849575?v=4" width="100px;" alt="Lucas Pimenta"/>
      </a>
      <br>
      <a href="https://github.com/Jinkogule">
        <img src="https://img.shields.io/badge/-Github-black?style=flat-square&logo=Github&logoColor=white">
      </a>
    </td>
    <td align="center">
      Lucas Couto
      <br>
      <a href="https://github.com/LucasCouto22">
        <img src="https://avatars.githubusercontent.com/u/62523407?v=4" width="100px;" alt="Lucas Couto"/>
      </a>
      <br>
      <a href="https://github.com/LucasCouto22">
        <img src="https://img.shields.io/badge/-Github-black?style=flat-square&logo=Github&logoColor=white">
      </a>
    </td>
  </tr>
</table>

## 📝 License

This project is licensed under the **[MIT](./LICENSE)** license.

