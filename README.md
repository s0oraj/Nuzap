# Nuzap
A simple news android app which shows local news of top countries around the globe.


 Install & try the app: [Download APK](https://drive.google.com/file/d/1Ysg01UcOXfticLrtStf2CNlqX8CaMk4P/view?usp=sharingg)

 

 * This app displays recent news  in its main ui screen.
 * It fetches JSON Data by using [newsapi](https://newsapi.org/).
 * The JSON received is then extracted into relevant information and shown in the main user-interface of the app.


 ## Screenshots


  
 <a href="(https://user-images.githubusercontent.com/42529024/170065885-20281c84-a36c-4b6c-8bd0-7f7a14eff27e.png" target="_blank">
  <img src="https://user-images.githubusercontent.com/42529024/170065885-20281c84-a36c-4b6c-8bd0-7f7a14eff27e.png" width="22%" />
 <span>&nbsp;</span>
 <a href="https://user-images.githubusercontent.com/42529024/170066321-a2bab16a-fd96-46fa-a0d0-62610bbc5495.png" target="_blank">
  <img src="https://user-images.githubusercontent.com/42529024/170066321-a2bab16a-fd96-46fa-a0d0-62610bbc5495.png" width="22%" />
</a>
<span>&nbsp;</span>
<a href="https://raw.githubusercontent.com/s0oraj/ScienceGlass/master/illustration_gif_three.gif" target="_blank">
  <img src="https://raw.githubusercontent.com/s0oraj/ScienceGlass/master/illustration_gif_three.gif" width="22%" />
</a>
<span>&nbsp;</span>
<a href="https://raw.githubusercontent.com/s0oraj/ScienceGlass/master/illustration_gif_one.gif" target="_blank">
  <img src="https://raw.githubusercontent.com/s0oraj/ScienceGlass/master/illustration_gif_one.gif" width="22%" />
</a>


 ## How this app works

 - This android app displays recent news around the globe.
 - It fetches JSON Data from  [news api](https://newsapi.org/) by using [Volley](https://github.com/google/volley) library. Volley helps in fetching JSON data in a seperate background thread.
 - After this, the recieved JSON data is being parsed in the main ui thread. After JSON parsing the data is shown to the main user-interface of the app.
 - For displaying featured image of a post, [Picasso](https://github.com/square/picasso) library is used which takes image from url and loads it to the screen.
 - And finally, Androids [WebView](https://developer.android.com/reference/android/webkit/WebView) library is used to convert html content into relevant data to be loaded on the screen.
 
 ## Libraries used

 * [Volley] (https://github.com/google/volley)
 * [Picasso] (https://github.com/square/picasso)


 ## Features

 -  Users can set the number of recent news they want to see. For example, if a user sets his preference to three posts then the user is only shown three recent posts from the ScienceGlass blog
 - Users can tap on navigation menu at left and will see list of popular countries to fetch news from.
 -  Swapping and Deleting of Data: Users can tap on a news and swipe right or left to remove it from screen. Users can also tap on a post hold on it and change its location by moving it up or down the list of available posts. Refresh button will restore all data back to its original configuration.
 -  Change layout: Users can customize what layout they want to see their posts in. Two layouts are provided: LinearLayout and GridLayout.
 
 




