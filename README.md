# Reactive Jikan

[![](https://jitpack.io/v/SandroHc/reactive-jikan.svg?style=flat-square)]
(https://jitpack.io/#net.sandrohc/reactive-jikan)

API wrapper for the [Jikan API](https://jikan.moe), with the power of Project Reactor and reactive streams.

## Installation

Add the following dependency to your build file.

If using Gradle (`build.gradle`):
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'net.sandrohc:reactive-jikan:master-SNAPSHOT'
}
```

If using Maven (`pom.xml`):
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>net.sandrohc</groupId>
    <artifactId>reactive-jikan</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```

For other building tools, see: https://jitpack.io/#net.sandrohc/reactive-jikan

## Usage

```java
Jikan jikan = new Jikan();

// Fetch the anime with ID 1
Anime anime = jikan.getAnime(1).block();

// Search for 'sword art online'
AnimeSearch animeSearch = jikan.request(new AnimeSearchQuery()
        .query("sword art online")
        .limit(5)
        .status(AnimeStatus.AIRING)
        .orderBy(AnimeOrderBy.MEMBERS)
).block();
```