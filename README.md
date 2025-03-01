# **📽️ TMDb Movie & TV Show App**  

A Jetpack Compose Android app that **fetches popular movies and TV shows** from **The Movie Database (TMDb) API**, displaying them in a clean and interactive UI. The app uses **Retrofit for API calls, RxKotlin for reactive programming, Koin for dependency injection, and unit testing with JUnit & MockK**.

---

## **📌 Features Implemented**  

✅ **Movie & TV Show Tabs**  
- The app is divided into **two tabs**: one for **Movies** and one for **TV Shows**.  
- Users can **switch between tabs** to view the respective content.  

✅ **Shimmer Effect for Loading State**  
- Before the data loads, a **shimmer effect** is displayed to improve user experience.  
- A **3-second artificial delay** is added to **ensure shimmer is visible** before actual content loads.  

✅ **Home Screen**  
- Displays a **list of movies & TV shows** in a **LazyColumn**.  
- Shows **title & plot overview** for each item.  
- Loads **only the first page** of results from the **TMDb API**.  

✅ **Details Screen**  
- Clicking on any movie/TV show **navigates to a detailed screen**.  
- The detailed screen displays:  
  - **Backdrop image**  
  - **Release date / First air date**  
  - **Language, Popularity, and Ratings**  
  - **Genres**  

✅ **Networking & API Handling**  
- Uses **Retrofit** for making API calls.  
- Implements **RxKotlin's `Single.zip`** to fetch **Movies and TV Shows simultaneously**.  
- **Koin** is used for **Dependency Injection**, ensuring clean architecture.  
- **Error handling** is implemented using **Toast messages** when API calls fail.  

---

## **🚀 Technologies Used**  

| **Tech Stack** | **Purpose** |
|---------------|------------|
| **Jetpack Compose** | UI Framework |
| **Retrofit** | API Networking |
| **RxKotlin (Single.zip)** | API Call Management |
| **Koin** | Dependency Injection |
| **JUnit & MockK** | Unit Testing |
| **Shimmer Effect** | Loading UI |
| **Navigation Component** | Screen Transitions |

---

## **💡 Experience & Learnings**  

1️⃣ **Exploring TMDb API**  
- Understood **how pagination works** in TMDb API.  
- Learned **which API endpoints to call** for fetching **Movies & TV Shows**.  
- Dealt with **API response structures & JSON parsing**.  

2️⃣ **Using `Single.zip` (RxKotlin) for API Calls**  
- Instead of making **separate API calls**, I used **RxKotlin’s `Single.zip`** to **fetch Movies & TV Shows simultaneously**.  
- This improved **performance** and **reduced network latency**.  

3️⃣ **Implementing Koin for Dependency Injection**  
- **Removed manual ViewModel instantiation**, making the code **cleaner**.  
- Injected **Repository & API services** efficiently.  
- Encountered and fixed **Koin dependency injection issues** (like missing arguments in `appModule`).  

4️⃣ **Adding Shimmer Effect & Delay**  
- **Implemented Shimmer placeholders** while data loads.  
- **Added a 3-second delay** to ensure shimmer remains visible.  

5️⃣ **Error Handling in API Calls**  
- **Handled network failures** by showing **Toast messages**.  
- **Managed empty responses** and **unexpected API failures**.  

---

## **🤯 Challenges Faced & How They Were Solved**  

| **Challenge** | **Solution Implemented** |
|--------------|--------------------------|
| **1. TMDb API Pagination** | Decided to **load only the first page** for simplicity. |
| **2. API Call Optimization** | Used **`Single.zip`** to **combine Movie & TV Show API calls** into one. |
| **3. Koin Dependency Injection Errors** | Ensured correct dependencies are **provided in `appModule.kt`**. |
| **4. Shimmer Effect Not Visible** | Added a **3-second delay** before setting LiveData values. |
| **5. Unit Testing Issues** | Using **MockK** to mock repository & API responses. |

---

## **🛠️ Assumptions Made**  

✅ **1. Only fetching the first page of results**  
- The app does **not implement infinite scrolling** or pagination.  
- It **only fetches the first page** from TMDb API.  

✅ **2. Minimal UI & Styling**  
- UI is kept **minimal** with a **focus on functionality**.  
- Used **Material3 components** for a clean look.  

✅ **3. No Offline Caching**  
- The app **does not store data locally** (e.g., using Room Database).  
- Every time the app is opened, it **fetches fresh data from TMDb API**.  

✅ **4. Assumed Stable API Responses**  
- Expected that **TMDb API responses follow a consistent format**.  
- Did not handle cases like **sudden API schema changes**.  

---

## **🔄 Future Improvements**  

🚀 **1. Implement Infinite Scrolling**  
- Currently, the app only loads **the first page**.  
- Future versions will implement **pagination using `LazyColumn`**.  

🚀 **2. Add Room Database for Caching**  
- Cache movie & TV show data locally to **reduce network calls**.  

🚀 **3. Improve UI & Theming**  
- Enhance UI with **better visuals & animations**.  
- Add **dark mode** support.  

🚀 **4. Add More Unit & UI Tests**  
- Improve test coverage with **Espresso UI Tests**.  
- Use **MockWebServer** to test **network calls**.  

---

## **👨‍💻 Author**  
📌 **Developed By:** Param .
📌 **Email:** param21340@iiitd.ac.in

---

---

### **🚀 Final Thoughts**
This project was a great learning experience in **API integration, Jetpack Compose, RxKotlin, and Dependency Injection (Koin)**.  
Hope you find this README helpful! 😊  

