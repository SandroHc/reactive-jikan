# Reactive Jikan

[![Maven Central](https://img.shields.io/maven-central/v/net.sandrohc/reactive-jikan?label=version&style=flat-square)](https://mvnrepository.com/artifact/net.sandrohc/reactive-jikan)
[![GitHub Workflow](https://img.shields.io/github/workflow/status/SandroHc/reactive-jikan/Build?style=flat-square)](https://github.com/SandroHc/reactive-jikan/actions?query=workflow:Build)
[![Codecov](https://img.shields.io/codecov/c/github/SandroHc/reactive-jikan?style=flat-square)](https://codecov.io/gh/SandroHc/reactive-jikan)

A fast and fully-typed API wrapper for the [Jikan API](https://jikan.moe) V4, with the power of Project Reactor and reactive streams. 

## Supported versions

| API version                         | reactive-jikan version                                                           |
|-------------------------------------|----------------------------------------------------------------------------------|
| [V3](https://jikan.docs.apiary.io/) | [1.1.0](https://search.maven.org/artifact/net.sandrohc/reactive-jikan/1.1.0/jar) |
| [V4](https://docs.api.jikan.moe/)   | [2.2.0](https://search.maven.org/artifact/net.sandrohc/reactive-jikan/2.2.0/jar) |


## Installation

Add the following dependency to your build file.

If using Gradle (`build.gradle`):
```groovy
dependencies {
    implementation 'net.sandrohc:reactive-jikan:2.2.0'
}
```

If using Maven (`pom.xml`):
```xml
<dependency>
    <groupId>net.sandrohc</groupId>
    <artifactId>reactive-jikan</artifactId>
    <version>2.2.0</version>
</dependency>
```

If you want to use development versions, see: https://jitpack.io/#net.sandrohc/reactive-jikan

## Usage

The Reactive Jikan library provides all queries through the `Query` classes. Once the desired query is created (the `QueryFactory` can be used to help you navigate through all the available queries), it can be passed to the `Jikan` instance. Some queries have optional parameters, like the search queries; for more details, please see the javadocs for each query or refer to the [official Jikan API documentation](https://jikan.docs.apiary.io).

After the query has been configured with the desired parameters, it can be executed by calling `query.execute()` (or alternatively `jikan.query(yourQuery)`). The value returned is a reactive stream of type `Mono<T>` or `Flux<T>` depending on the query. The HTTP request is only made once the methods `subscribe()` or `block()` are called. For more information on reactive queries, please read the [relevant documentation](https://projectreactor.io/docs/core/release/reference/).

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
Collection<Anime> results = jikan.query().anime().search()
		.query("sword art online")
		.limit(5)
		.status(AnimeStatus.AIRING)
		.orderBy(AnimeOrderBy.MEMBERS, SortOrder.ASCENDING)
		.execute()
		.collectList()
		.block();
```

## Android

The following changes are necessary to use this library on Android.

```java
Jikan jikan = new Jikan.JikanBuilder()
        .httpClientCustomizer(httpClient -> httpClient.resolver(DefaultAddressResolverGroup.INSTANCE))
        .build();
```

## Caching

Caching comes **disabled** by default. To enable it, please provide an implementation of `JikanCache` to the `Jikan` instance.

The following is an example using [Caffeine](https://github.com/ben-manes/caffeine) as the cache library. Beware that Caffeine is not supported in Android!

```java
// build.gradle
dependencies {
	implementation 'com.github.ben-manes.caffeine:caffeine:2.9.3'
}

// App.java
JikanCache jikanCache = new JikanCache() {
    protected final Cache<String, JikanValueHolder> cache = Caffeine.newBuilder().maximumSize(10_000).expireAfter(new JikanExpiry()).build();

    public void put(String key, Object value, OffsetDateTime expires) {
        cache.put(key, new JikanValueHolder(value, expires));
    }

    public Optional<Object> get(String key) {
        return Optional.ofNullable(cache.getIfPresent(key)).map(holder -> holder.value);
    }

    class JikanValueHolder {
        public final Object value;
        public final long expireTime;

        public JikanValueHolder(Object value, OffsetDateTime expires) {
            this.value = value;
            this.expireTime = TimeUnit.SECONDS.toNanos(expires.toEpochSecond());
        }
    }

    class JikanExpiry implements Expiry<String, JikanValueHolder> {
        public long expireAfterCreate(String key, JikanValueHolder value, long currentTime) {
            return value.expireTime;
        }

        public long expireAfterUpdate(String key, JikanValueHolder value, long currentTime, long currentDuration) {
            return currentDuration; // Do not modify expire date
        }

        public long expireAfterRead(String key, JikanValueHolder value, long currentTime, long currentDuration) {
            return currentDuration; // Do not modify expire date
        }
    }
};

Jikan jikan = new Jikan.JikanBuilder()
        .cache(jikanCache)
        .build();
```

## Endpoints

The following is an exhaustive list of all the endpoints and query classes supported by the Reactive Jikan library.

| Endpoint                         	| Query                        	| Result      	            |
|----------------------------------	|------------------------------	|-------------------------- |
| **Anime** | | |
| /{id}                            	| AnimeQuery                    | Anime      	            |
| /{id}/characters_staff           	| AnimeCharactersAndStaffQuery	| AnimeCharactersAndStaff   |
| /{id}/episodes/{page}            	| AnimeEpisodesQuery            | AnimeEpisodes             |
| /{id}/news                       	| AnimeNewsQuery                | AnimeNews            	    |
| /{id}/pictures                   	| AnimePicturesQuery            | (list) AnimePicture       |
| /{id}/videos                     	| AnimeVideosQuery              | AnimeVideos            	|
| /{id}/statistics                  | AnimeStatisticsQuery          | Stats            	        |
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
| /{id}/statistics                  | MangaStatisticsQuery          | Stats            	        |
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
| /{username}   	                | UserProfileQuery | UserProfile |
| /{username}/history   	        | (not implemented) |  |
| /{username}/history/anime   	    | UserHistoryAnimeQuery | (list) UserHistory |
| /{username}/history/manga   	    | UserHistoryAnimeQuery | (list) UserHistory |
| /{username}/friends/{page}   	    | UserFriendsQuery | (list) UserFriend |
| /{username}/animelist/{status}   	| UserAnimeQuery | (list) UserAnime |
| /{username}/mangalist/{status}   	| UserMangaQuery | (list) UserManga |
| **Club** | | |
| /{id}   	                        | ClubQuery | Club  |
| /{id}/members/{page}   	        | ClubMembersQuery | (list) ClubMember  |
| **Meta** | | |
| /status   	                    | (not implemented) |  |
| /requests/{type}/{period}/{page}  | (not implemented) |  |
