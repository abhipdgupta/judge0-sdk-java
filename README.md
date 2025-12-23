# Judge0 SDK for Java

A Java SDK for interacting with the Judge0 API.

## Installation

This project is hosted on GitHub Packages. To use it, you need to add the dependency to your `pom.xml` and configure your `~/.m2/settings.xml` to authenticate to GitHub Packages.

### 1. Add the repository to your `pom.xml`

You need to add two repositories to your `pom.xml` file. One for the `judge0-sdk` and another for its `try-util` dependency.

```xml
<repositories>
    <repository>
        <id>github</id>
        <name>GitHub abhipdgupta Apache Maven Packages</name>
        <url>https://maven.pkg.github.com/abhipdgupta/judge0-sdk-java</url>
    </repository>
    <repository>
        <id>github-try-util</id>
        <name>GitHub abhipdgupta try-util-java Apache Maven Packages</name>
        <url>https://maven.pkg.github.com/abhipdgupta/try-util-java</url>
    </repository>
</repositories>
```

### 2. Add the dependency to your `pom.xml`

```xml
<dependency>
    <groupId>io.github.abhipdgupta</groupId>
    <artifactId>judge0-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 3. Configure `settings.xml`

Add a server to your `~/.m2/settings.xml` file to authenticate to GitHub Packages.

```xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>YOUR_GITHUB_USERNAME</username>
      <password>YOUR_GITHUB_PERSONAL_ACCESS_TOKEN</password>
    </server>
  </servers>
</settings>
```

Replace `YOUR_GITHUB_USERNAME` with your GitHub username and `YOUR_GITHUB_PERSONAL_ACCESS_TOKEN` with a personal access token with the `read:packages` scope.

## Usage

This SDK provides two clients: `SelfHostedJudge0Client` for self-hosted instances of Judge0 and `RapidApiJudge0Client` for the Judge0 API on RapidAPI.

### `SelfHostedJudge0Client`

```java
import io.github.abhipdgupta.judge0.Judge0Client;
import io.github.abhipdgupta.judge0.SelfHostedJudge0Client;

public class Main {
    public static void main(String[] args) {
        Judge0Client client = SelfHostedJudge0Client.builder()
            .baseUrl("YOUR_SELF_HOSTED_JUDGE0_URL")
            .build();
            
        // Use the client to interact with the Judge0 API
    }
}
```

### `RapidApiJudge0Client`

```java
import io.github.abhipdgupta.judge0.Judge0Client;
import io.github.abhipdgupta.judge0.RapidApiJudge0Client;

public class Main {
    public static void main(String[] args) {
        Judge0Client client = RapidApiJudge0Client.builder()
            .baseUrl("https://judge0-ce.p.rapidapi.com")
            .apiKey("YOUR_RAPIDAPI_KEY")
            .host("judge0-ce.p.rapidapi.com")
            .build();
            
        // Use the client to interact with the Judge0 API
    }
}
```

### Extending `AbstractJudge0Client` for Custom Endpoints

If you have a custom Judge0 endpoint that requires different authentication mechanisms or a different base URL than the provided `SelfHostedJudge0Client` or `RapidApiJudge0Client`, you can extend the `AbstractJudge0Client` class.

You need to implement two abstract methods:

1.  `protected abstract String getBaseUrl();`: This method should return the base URL of your custom Judge0 endpoint.
2.  `protected abstract Consumer<HttpRequest.Builder> getAuthHeadersConsumer();`: This method should provide a `Consumer` that adds any necessary authentication headers to the `HttpRequest.Builder`.

Here's an example:

```java
import io.github.abhipdgupta.judge0.AbstractJudge0Client;
import java.net.http.HttpRequest;
import java.util.function.Consumer;

public class CustomJudge0Client extends AbstractJudge0Client {
    private final String customBaseUrl;
    private final String customAuthToken;

    public CustomJudge0Client(String customBaseUrl, String customAuthToken) {
        this.customBaseUrl = customBaseUrl;
        this.customAuthToken = customAuthToken;
    }

    @Override
    protected String getBaseUrl() {
        return customBaseUrl;
    }

    @Override
    protected Consumer<HttpRequest.Builder> getAuthHeadersConsumer() {
        return builder -> builder.header("Authorization", "Bearer " + customAuthToken);
    }

    // You can also override other methods from Judge0Client if needed for specific custom logic
}

// How to use your custom client:
public class Main {
    public static void main(String[] args) {
        CustomJudge0Client customClient = new CustomJudge0Client(
            "YOUR_CUSTOM_JUDGE0_BASE_URL",
            "YOUR_CUSTOM_AUTH_TOKEN"
        );
        // Use customClient to interact with your custom Judge0 API
    }
}
```

### Available Methods

The `Judge0Client` interface provides the following methods:

*   `createSubmission(CreateJudge0SubmissionRequest request, boolean base64Encoded, boolean waitFor)`: Creates a single submission.
*   `createBatchSubmission(CreateJudge0BatchSubmissionRequest request, boolean base64Encoded)`: Creates a batch of submissions.
*   `getSubmission(String token, boolean base64Encoded)`: Retrieves the result of a single submission.
*   `getBatchSubmissions(List<String> tokens, boolean returnAsBase64)`: Retrieves the results of a batch of submissions.
*   `getActiveLanguages()`: Gets a list of supported languages.
*   `getAllStatues()`: Gets a list of all possible submission statuses.
*   `getConfiguration()`: Gets the Judge0 instance's configuration.
*   `getVersion()`: Gets the Judge0 instance's version.

## Unimplemented Features

This SDK does not currently implement the following Judge0 API endpoints:

*   `GET /languages/{id}`: Get a specific language by its ID.
*   `GET /statistics`
*   `GET /workers`
*   `GET /about`
*   `GET /is_extra_sandbox_running`
*   `GET /system_info`
*   `GET /health_check`

## Project Status

This is an early version of the Judge0 SDK for Java. It currently does not include comprehensive test cases. We welcome contributions for adding more test cases.

This SDK is designed to be simple and easy to understand, making it a good starting point if you wish to integrate Judge0 into your Java projects. While this version does not fully implement all available Judge0 APIs, and is not a complete exact API specification to Judge0, it aims to simplify your interaction with the Judge0 platform. Future updates will focus on adding more API implementations and test cases.