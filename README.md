QR Management Application
Overview

The QR Management Application is a project written in the Spring Java framework. Originally designed as a mini CRM for chemical cleaning shops, the application is evolving into a basic CRM that can be adapted to various business areas with minimal changes to the database and code.

Features

    Each product is assigned a QR code, which workers can scan using their mobile phones to update the status.
    Basic functionalities for managing customers, workers, products, and more.
    Database operations are performed using PostgreSQL.

Database

The SQL commands for setting up the database are located in the following file:

/src/main/utils/create_table_script.sql

Getting Started

To get started with the QR Management Application, follow these steps:

    Clone the repository.
    Set up the PostgreSQL database using the provided SQL script.
    Configure your application properties to connect to your PostgreSQL database.
    Build and run the application.

Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.
License

This project is licensed under the MIT License. See the LICENSE file for details.
