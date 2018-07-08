# IBackdrop
A library to simply setup a backdrop in your project

<img src="/screenshots/gif1.gif" width="280" height="480">


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


### Basic usage

I wrote a step by step for how to use this library on Medium, follow the link: 
http://bit.ly/2uc8Cc1

### TODO

- [ ] Handle Device Orientantion Changes
- [ ] Call Toolbar via XML (by reference)
- [ ] Set a Front View Header (optional)
- [ ] set Inative state to front layer when the back layer is visible