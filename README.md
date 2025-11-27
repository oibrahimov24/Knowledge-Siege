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
- Special admin login (`superadmin / SuperAdmin132`)  
- Users select a profile image for gameplay  

---

## 🎮 Gameplay Overview

### Player Controls
- Move **left** and **right** using keyboard arrows  
- Press **H** to hide the player  

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

