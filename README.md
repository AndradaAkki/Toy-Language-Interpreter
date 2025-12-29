# 💻 Toy Language Interpreter

A complex execution engine built in **Java** that interprets and runs a custom programming language. This application simulates the internal workflow of a computer, managing memory allocation (Heap), execution stacks, and file I/O operations in real-time.

![Java](https://img.shields.io/badge/Language-Java_17+-orange)
![Architecture](https://img.shields.io/badge/Architecture-MVC-blue)
![Pattern](https://img.shields.io/badge/Pattern-Command-green)

## 📖 Overview
This project is an interpreter for a C-like procedural language. Unlike a compiler that translates code to machine language, this application executes source code instructions step-by-step, maintaining a global application state.

It was built to demonstrate advanced understanding of **Programming Language Semantics**, **Memory Management**, and **Software Architecture**.

## 🏗️ Architectural Design
The application follows a strict **Model-View-Controller (MVC)** architecture:

| Layer | Component | Responsibility |
| :--- | :--- | :--- |
| **Model** | `src/model/` | Defines the language semantics (Statements, Expressions) and the **Program State** (Execution Stack, Heap, Symbol Table). |
| **Repository** | `src/repository/` | Manages the list of active program states (allowing for future multi-threading support). |
| **Controller** | `src/controller/` | Orchestrates the execution logic, including the **Garbage Collector** which safely removes unreferenced heap memory. |
| **View** | `src/view/` | A console-based UI using the **Command Pattern** to allow users to select and run different code examples. |

## ✨ Key Features
* **Complex Execution State:** Maintains distinct data structures for program execution:
    * **Execution Stack:** Tracks nested statement execution.
    * **Symbol Table:** Manages variable scope and values.
    * **Heap Memory:** Supports dynamic memory allocation (`new`, `readHeap`, `writeHeap`) and pointer references.
    * **File Table:** Manages file descriptors for reading/writing data.
* **Garbage Collection:** Implements a "Mark-and-Sweep" style Safe Garbage Collector to free memory on the Heap that is no longer referenced by the Symbol Table.
* **Rich Language Support:**
    * **Types:** Integers, Booleans, Strings, and Reference Types (Pointers).
    * **Control Flow:** `If`, `While`, and sequential composition.
    * **I/O:** File operations (`openRFile`, `readFile`, `closeRFile`).
    * **Operations:** Arithmetic, Logic, and Relational expressions.

## 🛠️ Project Structure
```text
src/
├── controller/       # Execution orchestration & Garbage Collection logic
├── model/
│   ├── expression/   # AST nodes for expressions (Arith, Logic, Var)
│   ├── state/        # Data structures (Heap, Stack, Dict)
│   ├── statement/    # AST nodes for statements (If, While, Print)
│   ├── type/         # Type system definitions
│   └── value/        # Concrete value implementations
├── repository/       # State persistence
└── view/             # CLI Menu and Command implementation
