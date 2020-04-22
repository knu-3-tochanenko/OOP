# How to run this project

Download and install [Tomcat 9](http://tomcat.apache.org/) on your desired location. I've installed it on `C:\utils\libs\tomcat\`.

Add Tomcat Server to your `Application Servers`:
1. Go to `Settings`
2. Go to `Build. Execution, Deployment` -> `Application servers`
3. Click on `+` sign and select `Tomcat Server` from dropdown
4. Paste path to your `Tomcat` folder in `Tomcat Home` field. It should get the server version automatically.

To run this application, to to `Gradle` window. You can find it on the right of the screen or by pressing `Ctrl + E` and selecting `Gradle`. Go to `Tasks` -> `gretty` -> `appRun`. When the app is started, you can press on the URL in the `Run` window to open client in browser. If you don't see `gretty` folder in `Tasks`, you will need to sync Gradle.