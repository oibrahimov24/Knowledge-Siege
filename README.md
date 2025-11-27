# Knowledge Siege
*A Java Swing Educational Game for COMP 132*

Knowledge Siege is a 2D educational Java game built with **Java Swing**, **OOP principles**, and **file handling**.  
Your goal is to survive 3 levels of academic “Knowledge Keepers”—Section Leaders, TAs, and Professors—while collecting information to gain points and avoiding harmful questions that reduce health.

This project was developed as part of **COMP 132: Advanced Programming**, demonstrating mastery of GUI programming, inheritance, polymorphism, exception handling, and game loop logic.

---

## 🚀 Features

### ✔ Login & User Management
- Register new users  
- Login with existing accounts  
- Users select a profile image for gameplay  

---

## 🎮 Gameplay Overview

### Player Controls
- Move **left** and **right** using keyboard arrows  

### Enemies (Knowledge Keepers)
- **Section Leaders (SLs)**  
- **Teaching Assistants (TAs)**  
- **Professors**  
- Move horizontally at different speeds  
- Shoot **ShotBoxes**:
  - ❓ *Questions* → deal damage  
  - 💡 *Information* → award points  
- Text content of each shot is displayed on a side panel

### Levels

| Level | Enemies | Required Score | Difficulty |
|-------|---------|----------------|------------|
| **1** | 4 SLs | 50 | Low |
| **2** | Remaining SLs + 2 TAs | 100 | Medium |
| **3** | Remaining TAs + 2 Professors | 150 | High |

### ShotBoxes
- SL: slow shots  
- TA: medium shots  
- Professor: fast shots  
- Colliding with ❓ = health loss  
- Colliding with 💡 = score gain  

---

## 📁 File Handling
The game loads educational content from two files:
- questions.txt
- info.txt


Each file contains **30+ entries**, split by difficulty:
- 10 for SLs  
- 10 for TAs  
- 10 for Professors  

Missing files trigger custom exceptions + GUI warnings.

---

## 🧠 Object-Oriented Structure
The project uses:
- **Inheritance** (SL → TA → Professor hierarchy)  
- **Abstract classes & interfaces**  
- **Java Collections**  
- **Encapsulation of drawing logic**  
- **Separation of logic and GUI (MVC style)**  

---

## 📊 Score System
- Collect 💡 *information* to earn points  
- Take damage from ❓ *questions*  
- Scoreboard:
  - Sorted by score (descending)  
  - Same user → Game1, Game2, Game3…  

---

## 📝 Logging
A detailed log file is generated containing:
- Game start  
- Collisions (info collected or damage taken)  
- Score updates  
- “User is unable to move.” events  
- Level transitions  
- Game Over / Victory  

---

## 🖥 GUI (Java Swing)
The GUI is created using:
- `JPanel`
- `JButton`
- `JLabel`
- `JComboBox`
- `JOptionPane`
- `javax.swing.Timer` for:
  - Movement
  - Collision checking
  - Animation

Custom images are used for characters.

---

## 🧪 Demo Requirements (All Supported)

### Initial Setup
- New user registration
- Login system  
- GUI scoreboard  

### Gameplay
- Player movement  
- Keeper behaviors (SL, TA, Professor)  
- ShotBox attacks and text display  
- Score/health updates  
- Level transitions  
- Game Over / Victory screens  

### Error Handling
- Detects missing / empty content files  
- Prevents out-of-bound movement  
- Shows meaningful GUI errors  

---


