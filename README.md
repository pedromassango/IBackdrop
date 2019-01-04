# IBackdrop
[![](https://jitpack.io/v/pedromassango/IBackdrop.svg)](https://jitpack.io/#pedromassango/IBackdrop)

A library to setup a backdrop component in your project since for now there is no a easy way to implement that in our project. Words from official material link: "A backdrop appears behind all other surfaces in an app, displaying contextual and actionable content". Here is an example of what you get using this library:


<img src="/screenshots/gif1.gif" width="270" height="480">


## Requirements
- Android API level 16 or greater
- Your favorite IDE

## Setting up

*Current version:*  [![](https://jitpack.io/v/pedromassango/IBackdrop.svg)](https://jitpack.io/#pedromassango/IBackdrop)


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

Step 2. Adding Dependency
```groovy
dependencies {
        ...
	    implementation 'com.github.pedromassango:IBackdrop:CURRENT-VERSION'
	    ...
	}
```

Maven:

Step 1. Register jitpack.io
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
    <version>CURRENT-VERSION</version>
</dependency>
```


### Basic usage

I wrote a step by step for how to use this library on Medium, follow the link: 
https://blog.usejournal.com/android-material-design-components-backdrop-96a3044a3b2

### TODO

- [x] Handle Device Orientation Changes (you can set it in your manifest's activity tag)
- [x] Call Toolbar via XML (by reference)
- [x] Set a custom animation duration 
- [x] Set a custom backdrop view size
- [ ] set Inactive state to front layer when the back layer is visible (grey the layer)

### :heart: Star :heart: the repo to support the project or :smile:[Follow Me](https://github.com/pedromassango).Thanks!
[![Twitter Follow](https://img.shields.io/twitter/follow/pedromassangom.svg?style=social&label=Follow)](https://twitter.com/pedromassangom)
