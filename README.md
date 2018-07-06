# IBackdrop
A library to simply setup a backdrop in your project

<img src="/screenshots/img1.png" width="260" height="180"> <img src="/screenshots/img2.png" width="260" height="180">


## Requirements
​
- Android API level 16 or greater
- Your favorite IDE

## Setting up

Gradle:

Step 1. Add the JitPack repository to your build file.
Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
		...
	}
}
```

Step 2. Add the dependency
```groovy
dependencies {
        ...
	    implementation 'com.github.pedromassango:IBackdrop:1.0.0-beta'
	    ...
	}
```

Maven:

Step 1. register jitpack.io
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

Step 2. Add the dependency

```xml
<dependency>
    <groupId>com.github.pedromassango</groupId>
    <artifactId>IBackdrop</artifactId>
    <version>1.0.0-beta</version>
</dependency>
```


## Basic usage

After that everything that you need to do is:

```java

// set toolbar as actionBar (necessary an toolbar)
setSupportActionBar(home_toolbar)

// find your backdrop in your activity/fragment xml
val iBackdrop = findViewById(R.id.backdrop_view)

// build backdrop view
backdrop_view
    .withToolbar(home_toolbar)
    .build()
```