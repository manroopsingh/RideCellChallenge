# Ride Cell Challenge

#### Approach  
##### Architecture: 
MVP  
##### Libraries used:
Retrofit  (Network calls)  
RxJava  (Asynchronous Communication)  
Butterknife  (View Binding)  
Dagger  (Dependency Injection)  


##### Steps
1. Create GetRouteActivity to obtain Origin and Destination coordinates.
2. Create a retrofit helper class to obtain observable instance, which will get data from Google Directions API.*
3. Import maps.utils library by google to draw on the map.
4. Create a thread class which will communicate with the ViewMapActivity to show each coordinate in the LatLng list.
5. Initialize PolylineOptions, CircleOptions, Circle classes with desired settings(color, radius etc)

*Create a new project on Google developer console to obtain a key to use the api.

#### Application
##### Activities
1. Splash Activity: To show RideCell logo  
2. GetRoute Activity: Contains search box to filter results and recyclerView with list of films from given URL.
3. ViewMap Activity: Contains ExoPlayer to play the video and other video details.

##### Utility
1. RetrofitHelper: Retrofit factory class and interface for network calls.
2. MapAnimation: Thread class to communicate animation back on ViewMapActivity

