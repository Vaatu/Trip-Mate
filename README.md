# Trip Mate Android Mobile Application
# Requiremnets:
-SDK Version:21-29

# General Information


Android Mobile Application that helps the user to record his/her planned trips with the start and end Geo-points,
accompanied with the date and time of the trip and notes.
The application should remind the user with the trips on the time specified by the user.
In addition, the application should navigate the user to the right destination , and save the user's trips notes if there are any.
After that, the application should keep track of the upcoming and past trips.

----------------------------------------------------------------------------------------------------------------------------------------

 # App Scenario

 -  First user can sign up by creating an account or sign in by his/her google account to login.

  - After he signs up or login he will be directed to the home screen.

  - Then user can add a new trip with a name, start point, end point,  date and time in addition to trip notes.

  - When it is the time to start the trip, a dialog  appears  as a reminder and the user can start, cancel or snooze.

  - If the user chose to start the trip a map with his start and end point will start navigating the trip, also there is a floating widget icon  showing the trip notes .

  - If the user cancels the trip before it begins , it will be added to history with a status of "Cancled!".

  - Once the trip is Done , it will be added to the history with a status of "Done!"

  - If the user choose snooze, a notification  will appear on top.

  - If user clicked on history, he can view all his history trips.

  - User can logout from the application and all data will be saved until the comes back again.

----------------------------------------------------------------------------------------------------------------------------------------
# Developers
  - Mohammed El-Arabi

  - Yasmine Mohey Dwedar

  - Aly Serry

# Corresponding Tasks:
 - Mohammed El Aarabi --> Add Trip with google auto-complete + FireBase Database  + Alert Dialog Reminder + Floating Widget 
 - Yasmine Mohey Dwedar --> Navigation Drawer + Upcoming Trip and Histroy CardViews with recycler + Side Pop Menus
 - Aly Serry --> Splash Screen + Login + Sign up + Google Sign in + Firebase Auth + Notification service  


