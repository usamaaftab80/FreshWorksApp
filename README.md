FreshWorks App for Giphy GIFs

DESIGN:
1.  MVP is used to design the application architecture.
2.  View Package contains all Activities and Fragments.
3.  Adapters Package contains Fragment and RecyclerViewAdapters.
4.  Giphy end points are hit using their API, so no Objects (POJOs) have been used.

LIBRARIES:
1.  GSON - to serialize and deserialize object to store in SharedPreferences
2.  Giphy SDK Core - to hit Giphy endpoints (hence no RxJava, RxAndroid or Retrofit API is used)
3.  Glide - to load animated GIFs

POSSIBLE IMPROVEMENTS:
1.  Only activity can hold are presenters. 
2.  Use of SQLite instead of SharedPreferences. Since the application is small, SharedPreferences would work just fine. In the end, they both are just files.
3.  Any other comment from you is welcome. 
