# Google Play Integrity API server

> [!CAUTION]
> This project is just PoC sample. Not production ready!


- [Play Integrity Documentation](https://developer.android.com/google/play/integrity/overview)
- Enable API in Cloud Console - [Google Play Integrity API](https://console.cloud.google.com/apis/library/playintegrity.googleapis.com)
- Enable API in Google Play console - [Select app](https://play.google.com/console/u/0/developers/) > Test and release > App integrity
- Create a service account in Cloud console and his service key (JSON) store into `.private/` folder.
- Edit [Config.kt](./src/main/kotlin/Config.kt) to provide proper service account key path.
- Run this project webserver
- Implement Android FE part 🤖
---

## Backend part

- Uses [backend Google Play Integrity API](https://github.com/googleapis/google-api-java-client-services/tree/main/clients/google-api-services-playintegrity/v1) dependency

---

## Android part

- Obtain Token from Integrity API SDK (as described in [Play Integrity Documentation](https://developer.android.com/google/play/integrity/standard)) 
- call `POST http://[THIS-SERVER:8080]/play/integrity/all` and add `IntegrityQuery` as body. Into this body just put token taken from SDK.


```kotlin
@Serializable
data class IntegrityQuery(
    @SerialName("token")
    val token: String,
)
```

Response will contains json with content of [TokenPayloadExternal](https://googleapis.dev/java/google-api-services-playintegrity/latest/index.html?com/google/api/services/playintegrity/v1/model/TokenPayloadExternal.html)

---

This project was created using the [Ktor Project Generator](https://start.ktor.io).

Here are some useful links to get you started:

- [Ktor Documentation](https://ktor.io/docs/home.html)
- [Ktor GitHub page](https://github.com/ktorio/ktor)
- The [Ktor Slack chat](https://app.slack.com/client/T09229ZC6/C0A974TJ9). You'll need to [request an invite](https://surveys.jetbrains.com/s3/kotlin-slack-sign-up) to join.

## Features

Here's a list of features included in this project:

| Name                                                                   | Description                                                                        |
| ------------------------------------------------------------------------|------------------------------------------------------------------------------------ |
| [Koin](https://start.ktor.io/p/koin)                                   | Provides dependency injection                                                      |
| [Content Negotiation](https://start.ktor.io/p/content-negotiation)     | Provides automatic content conversion according to Content-Type and Accept headers |
| [Routing](https://start.ktor.io/p/routing)                             | Provides a structured routing DSL                                                  |
| [kotlinx.serialization](https://start.ktor.io/p/kotlinx-serialization) | Handles JSON serialization using kotlinx.serialization library                     |

## Building & Running

To build or run the project, use one of the following tasks:

| Task                          | Description                                                          |
| -------------------------------|---------------------------------------------------------------------- |
| `./gradlew test`              | Run the tests                                                        |
| `./gradlew build`             | Build everything                                                     |
| `buildFatJar`                 | Build an executable JAR of the server with all dependencies included |
| `buildImage`                  | Build the docker image to use with the fat JAR                       |
| `publishImageToLocalRegistry` | Publish the docker image locally                                     |
| `run`                         | Run the server                                                       |
| `runDocker`                   | Run using the local docker image                                     |

If the server starts successfully, you'll see the following output:

```
2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080
```

