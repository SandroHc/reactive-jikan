# Reactive Jikan

[![JitPack](https://jitpack.io/v/SandroHc/reactive-jikan.svg?style=flat-square)](https://jitpack.io/#net.sandrohc/reactive-jikan)
[![GitHub Workflow](https://img.shields.io/github/workflow/status/SandroHc/reactive-jikan/Build?style=flat-square)](https://github.com/SandroHc/reactive-jikan/actions?query=workflow:Build)
[![Codecov](https://img.shields.io/codecov/c/github/SandroHc/reactive-jikan?style=flat-square)](https://codecov.io/gh/SandroHc/reactive-jikan)

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

If you want to use the development version, place `master-SNAPSHOT` in the version.

For other building tools, see: https://jitpack.io/#net.sandrohc/reactive-jikan

## Usage

The way you fetch data is through the `Query` classes. Once you create your desired query (you can use the `QueryFactory` to help you navigating the list of queries), you can pass it to your `Jikan` instance. While building the query, you may specify optional parameters.

After you successfully configure the query with your desired parameters, you can now execute it by calling `query.execute()` (or use `jikan.query(yourQuery)`). The value returned is a reactive stream of type `Mono<T>`. To convert it into your desired result, you must call `subscribe()` or `block()`.

Here are some examples on how to use this library:
```java
// Create the Jikan instance with default parameters.
// You can also use the builder, JikanBuilder, if you desire different parameters.
Jikan jikan = new Jikan(); 

// Fetch the anime with ID 1 - mono response
Anime anime = jikan.query().anime().get(1)
        .execute()
        .block();

// Search for 'sword art online' - flux response
AnimeSearch animeSearch = jikan.query().anime().search()
        .query("sword art online")
        .limit(5)
        .status(AnimeStatus.AIRING)
        .orderBy(AnimeOrderBy.MEMBERS)
        .execute()
        .collectList()
        .block();
```

### Query list

| Endpoint                         	| Query                        	| Result      	            |
|----------------------------------	|------------------------------	|-------------------------- |
| **Anime** | | |
| /{id}                            	| AnimeQuery                    | Anime      	            |
| /{id}/characters_staff           	| AnimeCharactersAndStaffQuery	| AnimeCharactersAndStaff   |
| /{id}/episodes/{page}            	| AnimeEpisodesQuery            | AnimeEpisodes             |
| /{id}/news                       	| AnimeNewsQuery                | AnimeNews            	    |
| /{id}/pictures                   	| AnimePicturesQuery            | Flux<AnimePicture>        |
| /{id}/videos                     	| AnimeVideosQuery              | AnimeVideos            	|
| /{id}/stats                      	| AnimeStatsQuery               | Stats            	        |
| /{id}/forum                      	| AnimeForumQuery               | Flux<ForumTopic>          |
| /{id}/moreinfo                   	| AnimeMoreInfoQuery            | MoreInfo            	    |
| /{id}/reviews/{page}             	| AnimeReviewsQuery             | Flux<Review>            	|
| /{id}/recommendations            	| AnimeRecommendationsQuery     | Flux<Recommendation>      |
| /{id}/userupdates/{page}         	| AnimeUserUpdatesQuery         | Flux<UserUpdate>          |
| **Manga** | | |
| /{id}                            	| MangaQuery                    | Manga                     |
| /{id}/characters                 	| MangaCharactersQuery          | Flux<RoleSubEntity>       |
| /{id}/news                       	| MangaNewsQuery                | Flux<NewsArticle>         |
| /{id}/pictures                   	| MangaPicturesQuery            | Flux<Pictures>            	|
| /{id}/stats                      	| MangaStatsQuery               | Stats            	        |
| /{id}/forum                      	| MangaForumQuery               | Flux<ForumTopic>          |
| /{id}/moreinfo                   	| MangaMoreInfoQuery            | MoreInfo            	    |
| /{id}/reviews/{page}             	| MangaReviewsQuery             | Flux<Review>              |
| /{id}/recommendations            	| MangaRecommendationsQuery     | Recommendations           |
| /{id}/userupdates/{page}         	| MangaUserUpdatesQuery         | Flux<UserUpdate>          |
| **Person** | | |
| /{id}                            	| PersonQuery                   | Person            	    |
| /{id}/pictures                   	| PersonPicturesQuery           | Flux<Picture>            	|
| **Character** | | |
| /{id}                            	| CharacterQuery                | Character                 |
| /{id}/pictures                   	| CharacterPicturesQuery        | Flux<Picture>             |
| **Search** | | |
| /anime                            | AnimeSearchQuery              | Flux<AnimeSearchSub>      |
| /manga                            | MangaSearchQuery              | Flux<Manga>            	|
| /person                           | PersonSearchQuery             | Flux<PersonSub>           |
| /character                        | CharacterSearchQuery          | Flux<CharacterSub>        |
| **Season** | | |
| /{year}/{season}          	    | SeasonQuery                   | Flux<SeasonAnime>         |
| /archive                  	    | SeasonArchiveQuery            | Flux<SeasonArchiveYear>   |
| /later                    	    | SeasonLaterQuery              | Flux<SeasonAnime>         |
| **Schedule** | | |
| /{day}                  	        | ScheduleQuery                 | Schedule            	    |
| **Top** | | |
| /anime/{page}/{subtype}   	    | AnimeTopQuery | Flux<AnimeTopSub>  |
| /manga/{page}/{subtype}   	    | MangaTopQuery | Flux<MangaTopSub> |
| **Genre** | | |
| /anime/{page}/{subtype}   	    | AnimeGenreQuery | Flux<AnimeGenreSub> |
| /manga/{page}/{subtype}   	    | MangaGenreQuery | Flux<MangaGenreSub> |
| **Producer** | | |
| /{id}/{page}   	                |  |  |
| **Magazine** | | |
| /{id}/{page}   	                |  |  |
| **User** | | |
| /{username}   	                |  |  |
| /{username}/history   	        |  |  |
| /{username}/history/anime   	    |  |  |
| /{username}/history/manga   	    |  |  |
| /{username}/friends/{page}   	    |  |  |
| /{username}/animelist/{status}   	|  |  |
| /{username}/mangalist/{status}   	|  |  |
| **Club** | | |
| /{id}   	                        |  |  |
| /{id}/members/{page}   	        |  |  |
| **Meta** | | |
| /status   	                    |  |  |
| /requests/{type}/{period}/{page}  |  |  |
