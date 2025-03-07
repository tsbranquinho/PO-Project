# PO Project 23/24

## Project Description

This repository contains the PO Project for the 2023/2024 academic year, developed in Java. The project aims to implement a spreadsheet application, allowing users to create, edit, and manage data within a spreadsheet, with support for various operations, mathematical functions, and cell manipulation.

## Team Members

- **Tiago Branquinho** (GitHub: [tsbranquinho](https://github.com/tsbranquinho))
- **Rafael Avelar** (GitHub: [RafaelAvelar14](https://github.com/RafaelAvelar14))

## Key Features
- **Cell Management**: The system allows for inserting, updating, deleting, and manipulating cells within a spreadsheet.
- **Function Operations**: It supports functions like addition (ADD), subtraction (SUB), multiplication (MUL), division (DIV), average (AVERAGE), product (PRODUCT), concatenation (CONCAT), and coalescence (COALESCE).
- **File Reading and Writing**: It allows importing and exporting spreadsheet data from files and serialization for data persistence.
- **User Management**: The application provides functionality for creating and managing users, allowing each to have their own set of spreadsheets.

## Project Structure

1. **007/xxl-core**
   - Contains the main files for the project, including the logic for cell functionality, spreadsheet management, functions, and data manipulation.
   - **Main Classes:**
     - **Spreadsheet.java**: Represents the spreadsheet, with methods for cell manipulation.
     - **Cell.java**: Represents a cell, which can contain data or references to other cells.
     - **Content.java**: Defines the content of cells (e.g., text or formulas).
     - **Calculator.java**: The main class for user interaction and executing spreadsheet operations.
     - **Functions**: Implementations of mathematical and logical functions (such as ADD, SUB, PRODUCT, etc.).
   - **Important Directories:**
     - **content**: Contains classes that define the types of content in cells, including literals and references.
     - **exceptions**: Defines custom exceptions for error handling, such as when importing files or when cell ranges are unrecognized.
     - **visitor**: A design pattern to ease the addition of new functionalities in content classes.
     - **observer**: Implements the observer design pattern, used to update dependent cells when the value of another cell changes.

2. **007/xxl-app**
   - This part of the project contains the main application and related resources for running the program, such as JAR files and build configurations (Makefile).
   - **Structure:**
     - **src**: Contains the source code of the application logic, which interacts with the spreadsheet, processes data, and provides the user interface.
     - **xxl-app.jar**: The compiled JAR file that can be run to execute the application.

3. **po-uilib**
   - This directory contains the user interface library required by the project.
   - **Structure:**
     - **src**: Contains the source code for the user interface library.
   
4. **README.md**
   - This file contains the information and instructions for the PO Project, including details about the features, team members, and project structure.
