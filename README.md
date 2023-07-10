## Kittycats

This is a simple Java Swing app that will display a random cat image from Imgur.
The app uses cached data from Imgur so hopefully it should never get ratelimited.

![Windows Demo of App](https://raw.githubusercontent.com/fishydarwin/Kittycats-Java/master/__demo/demo_windows.png?token=GHSAT0AAAAAACEYEASKBYJYK55J53HMFP7OZFMK4JQ)

### Building the app

Clone this repository first:
```
git clone git@github.com:fishydarwin/Kittycats-Java.git
```

Then, inside the directory, run the tasks `clean install` from Maven.
```
mvn clean install
```

The result is placed under the `target` directory.

### Running the app

The app should be runnable by simply double-clicking the .jar file.

Should that not work, run a Terminal or PowerShell where the .jar file is placed and execute
```
java -jar Kittycats-x.x-SNAPSHOT.jar
```
Replace x.x with the version you are running.