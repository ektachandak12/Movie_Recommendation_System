# 🎬 Simple Movie Recommender System (Java + Swing)

This is a basic **content-based movie recommender system** built using **Java** with a **Swing GUI** interface. It recommends movies based on cosine similarity of user ratings stored in a CSV file. The system reads past user-item ratings and provides top-N personalized recommendations for any given user ID.

---

## 🛠 Features

- Java Swing-based graphical user interface
- Accepts user ID input and displays top movie recommendations
- Uses cosine similarity between users for recommendation scoring
- Loads user-item rating data from a CSV file
- Clean and minimal design suitable for learning and extending

---

## 📁 File Structure

MovieRecommender/
│
├── src/
│   ├── MainFrame.java             # GUI class (Swing-based)
│   └── RecommenderEngine.java     # Recommendation engine using cosine similarity
│
├── data/
│   └── user_ratings.csv           # CSV file containing user-item-rating data
│
└── README.md                      # Project documentation

---

## 📊 Sample CSV Format (`data/user_ratings.csv`)

userId,itemId,rating
1,101,4.0
1,102,3.5
2,101,5.0
2,103,2.0
3,102,4.5


> - `userId`: Unique ID for a user  
> - `itemId`: Unique ID for a movie/item  
> - `rating`: Rating given by the user (scale 1.0 to 5.0)

---

## 🚀 How to Run

### 🧰 Requirements
- Java JDK 8 or above
- Any IDE (e.g., IntelliJ IDEA, Eclipse) or command line terminal

### 🧪 Steps

1. Clone or download the repository.
2. Place a valid `user_ratings.csv` file inside the `data/` folder.
3. Compile the project:
   ```bash
   javac MainFrame.java RecommenderEngine.java
4. Run the application:

java MainFrame

Enter a valid userId (from your dataset) to see top 5 recommendations.

---

### 🧠 How It Works

1. **Load Data**  
   The application loads user-item-rating data from a CSV file (`user_ratings.csv`).

2. **User Input**  
   The user enters their **User ID** into the GUI.

3. **Similarity Calculation**  
   The system computes **cosine similarity** between the target user and all other users based on their rating vectors.

4. **Score Prediction**  
   For items not yet rated by the target user, the system calculates predicted scores using weighted averages from similar users.

5. **Recommendation**  
   The system displays the **top-N recommendations** (default: 5) with the highest scores.

---

### 👩‍💻 Author

**Ekta Naresh Chandak**  
B.Tech in Artificial Intelligence & Data Science  


> _Feel free to connect and collaborate on Java projects!_

---

### 📄 License

This project is intended **for educational purposes only**.  
You are free to use, modify, or distribute this code for learning and experimentation.  
No commercial use is permitted without prior consent.

---
