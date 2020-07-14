# Reactive Jikan

[![](https://jitpack.io/v/SandroHc/reactive-jikan.svg?style=flat-square)](https://jitpack.io/#net.sandrohc/reactive-jikan)

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
Anime anime = jikan.query().anime().get(1)
        .execute()
        .block();

// Search for 'sword art online'
AnimeSearch animeSearch = jikan.query().anime().search()
        .query("sword art online")
        .limit(5)
        .status(AnimeStatus.AIRING)
        .orderBy(AnimeOrderBy.MEMBERS)
        .execute()
        .block();
```

### Query list

| Endpoint                         	| Query                        	| Result      	            |
|----------------------------------	|------------------------------	|-------------------------- |
| **Anime**                        |                              	|             	            |
| /{id}                            	| AnimeSearchQuery              | AnimeSearch 	            |
| /{id}/characters_staff           	| AnimeCharactersAndStaffQuery	| AnimeCharactersAndStaff   |
| /{id}/episodes/{page}            	| AnimeEpisodesQuery            | AnimeEpisodes             |
| /{id}/news                       	| AnimeNewsQuery                | AnimeNews            	    |
| /{id}/pictures                   	| AnimePicturesQuery            | AnimePictures            	|
| /{id}/videos                     	| AnimeVideosQuery              | AnimeVideos            	|
| /{id}/stats                      	| AnimeStatsQuery               | AnimeStats            	|
| /{id}/forum                      	| AnimeForumQuery               | AnimeForum            	|
| /{id}/moreinfo                   	| AnimeMoreInfoQuery            | AnimeMoreInfo            	|
| /{id}/reviews/{page}             	|                              	|             	|
| /{id}/recommendations            	|                              	|             	|
| /{id}/userupdates/{page}         	|                              	|             	|
| **Manga**                        |                              	|             	|
| /{id}                            	| MangaQuery                    | Manga                     |
| /{id}/characters                 	|                              	|             	|
| /{id}/news                       	|                              	|             	|
| /{id}/pictures                   	|                              	|             	|
| /{id}/stats                      	|                              	|             	|
| /{id}/forum                      	|                              	|             	|
| /{id}/moreinfo                   	|                              	|             	|
| /{id}/reviews/{page}             	|                              	|             	|
| /{id}/recommendations            	|                              	|             	|
| /{id}/userupdates/{page}         	|                              	|             	|
| **Person**                           	|                              	|             	|
| /{id}                            	|                              	|             	|
| /{id}/pictures                   	|                              	|             	|
| **Character**                        	|                              	|             	|
| /{id}                            	|                              	|             	|
| /{id}/pictures                   	|                              	|             	|
| **Search**                           	|                              	|             	|
|                                  	|                              	|             	|
| **Season**                           	|                              	|             	|
| /{year}/{season}          	    |                              	|             	|
| /archive                  	    |                              	|             	|
| /later                    	    |                              	|             	|
| **Schedule**                     |                              	|             	|
| /{day}                  	        |                              	|             	|
| **Top**                          |                               	|             	|
| /{type}/{page}/{subtype}   	    |                              	|             	|
|                                  	|                              	|             	|
|                                  	|                              	|             	|
|                                  	|                              	|             	|
|                                  	|                              	|             	|
|                                  	|                              	|             	|
|                                  	|                              	|             	|
|                                  	|                              	|             	|

## Suggestions
* anime search sortBy doesn't seem ot be working
* anime/{id}/characters_staff - split into /characters and /staff
* manga/{id}/characters - split into /characters and /staff