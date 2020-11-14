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
        jcenter()
    }
}

dependencies {
    implementation 'net.sandrohc:reactive-jikan:0.1.2'
}
```

If using Maven (`pom.xml`):
```xml
<repositories>
    <repository>
        <id>central</id>
        <name>bintray</name>
        <url>https://jcenter.bintray.com</url>
    </repository>
</repositories>

<dependency>
    <groupId>net.sandrohc</groupId>
    <artifactId>reactive-jikan</artifactId>
    <version>0.1.2</version>
</dependency>
```

If you want to use development versions, see: https://jitpack.io/#net.sandrohc/reactive-jikan

## Usage

The way you fetch data is through the `Query` classes. Once you create your desired query (you can use the `QueryFactory` to help you navigate through all the available queries), you can pass it to your `Jikan` instance. Some queries may allow you to specify optional parameters, like the search queries.

After you configure the query with your desired parameters, you can now execute it by calling `query.execute()` (or use `jikan.query(yourQuery)`). The value returned is a reactive stream of type `Mono<T>` or `Flux<T>` depending on the query. To convert it into your desired result, you must call `subscribe()` or `block()`. For more information on reactive queries, please read the [relevant documentation](https://projectreactor.io/docs/core/release/reference/).

Here are some examples on how to use this library:
```java
// Create the Jikan instance with default parameters.
// You can also use the builder, JikanBuilder, to specify custom parameters.
Jikan jikan = new Jikan(); 

// Fetch the anime with ID 1. Returns a single value, a mono.
Anime anime = jikan.query().anime().get(1)
        .execute()
        .block();

// Search for 'sword art online'. Returns a list of values, a flux.
Collection<AnimeSearchSub> results = jikan.query().anime().search()
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
| /{id}/pictures                   	| AnimePicturesQuery            | (list) AnimePicture       |
| /{id}/videos                     	| AnimeVideosQuery              | AnimeVideos            	|
| /{id}/stats                      	| AnimeStatsQuery               | Stats            	        |
| /{id}/forum                      	| AnimeForumQuery               | (list) ForumTopic         |
| /{id}/moreinfo                   	| AnimeMoreInfoQuery            | MoreInfo            	    |
| /{id}/reviews/{page}             	| AnimeReviewsQuery             | (list) Review            	|
| /{id}/recommendations            	| AnimeRecommendationsQuery     | (list) Recommendation     |
| /{id}/userupdates/{page}         	| AnimeUserUpdatesQuery         | (list) UserUpdate         |
| **Manga** | | |
| /{id}                            	| MangaQuery                    | Manga                     |
| /{id}/characters                 	| MangaCharactersQuery          | (list) RoleSubEntity      |
| /{id}/news                       	| MangaNewsQuery                | (list) NewsArticle        |
| /{id}/pictures                   	| MangaPicturesQuery            | (list) Pictures           |
| /{id}/stats                      	| MangaStatsQuery               | Stats            	        |
| /{id}/forum                      	| MangaForumQuery               | (list) ForumTopic         |
| /{id}/moreinfo                   	| MangaMoreInfoQuery            | MoreInfo            	    |
| /{id}/reviews/{page}             	| MangaReviewsQuery             | (list) Review             |
| /{id}/recommendations            	| MangaRecommendationsQuery     | Recommendations           |
| /{id}/userupdates/{page}         	| MangaUserUpdatesQuery         | (list) UserUpdate         |
| **Person** | | |
| /{id}                            	| PersonQuery                   | Person            	    |
| /{id}/pictures                   	| PersonPicturesQuery           | (list) Picture            |
| **Character** | | |
| /{id}                            	| CharacterQuery                | Character                 |
| /{id}/pictures                   	| CharacterPicturesQuery        | (list) Picture            |
| **Search** | | |
| /anime                            | AnimeSearchQuery              | (list) AnimeSearchSub     |
| /manga                            | MangaSearchQuery              | (list) MangaSearchSub     |
| /person                           | PersonSearchQuery             | (list) PersonSearchSub    |
| /character                        | CharacterSearchQuery          | (list) CharacterSearchSub |
| **Season** | | |
| /{year}/{season}          	    | SeasonQuery                   | (list) SeasonAnime        |
| /archive                  	    | SeasonArchiveQuery            | (list) SeasonArchiveYear  |
| /later                    	    | SeasonLaterQuery              | (list) SeasonAnime        |
| **Schedule** | | |
| /{day}                  	        | ScheduleQuery                 | Schedule            	    |
| **Top** | | |
| /anime/{page}/{subtype}   	    | AnimeTopQuery                 | (list) AnimeTopSub        |
| /manga/{page}/{subtype}   	    | MangaTopQuery                 | (list) MangaTopSub        |
| **Genre** | | |
| /anime/{page}/{subtype}   	    | AnimeGenreQuery               | (list) AnimeGenreSub      |
| /manga/{page}/{subtype}   	    | MangaGenreQuery               | (list) MangaGenreSub      |
| **Producer** | | |
| /{id}/{page}   	                | ProducerQuery                 | Producer                  |
| **Magazine** | | |
| /{id}/{page}   	                | MagazineQuery                 | Magazine                  |
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
