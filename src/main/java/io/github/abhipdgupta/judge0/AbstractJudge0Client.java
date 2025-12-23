/* (C)2025 */
package io.github.abhipdgupta.judge0;

import io.github.abhipdgupta.judge0.exceptions.Judge0ConnectionException;
import io.github.abhipdgupta.judge0.exceptions.Judge0InvalidResponseMapException;
import io.github.abhipdgupta.judge0.models.*;
import io.github.abhipdgupta.tryutil.Try;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

/**
 * <h1>About:</h1>
 * <p>
 * This is an abstract class implementation of the judge0 client. Two methods
 * are abstract to incorporate various API providers.
 * </p>
 *
 * <p>
 * This client acts as an SDK for judge0 APIs.
 * </p>
 *
 * <h4>Official Api Docs: <a href="https://ce.judge0.com/">Click</a> </h4>
 *
 * @author Abhishek Pd. Gupta (abhishek@redyhire.com)
 */
public abstract class AbstractJudge0Client implements Judge0Client {

    protected final ObjectMapper objectMapper = new ObjectMapper();
    protected final HttpClient httpClient;

    // Abstract methods to be implemented by concrete providers
    protected abstract String getBaseUrl();

    protected abstract Consumer<HttpRequest.Builder> getAuthHeadersConsumer();

    protected AbstractJudge0Client() {
        this.httpClient =
                HttpClient.newBuilder()
                        .version(HttpClient.Version.HTTP_2)
                        .connectTimeout(Duration.ofSeconds(10))
                        .build();
    }

    @Override
    public Judge0Response<SubmissionBaseResult> createSubmission(
            CreateJudge0SubmissionRequest request, boolean base64Encoded, boolean waitFor)
            throws Judge0ConnectionException, Judge0InvalidResponseMapException {

        URI uri =
                getUri(
                        "/submissions",
                        Map.of(
                                "base64_encoded", String.valueOf(base64Encoded),
                                "wait", String.valueOf(waitFor),
                                "fields", "*"));

        HttpResponse<String> res = executePostRequest(uri, request);

        if (res.statusCode() == 200) {
            var result = castResponse(res, Judge0SubmissionResult.class);
            return Judge0Response.success(result, 200);
        } else if (res.statusCode() == 201) {
            var result = castResponse(res, Judge0SubmissionAsyncResult.class);
            return Judge0Response.success(result, 201);
        }
        return handleResponseForError(res);
    }

    @Override
    public Judge0Response<List<Judge0SubmissionAsyncResult>> createBatchSubmission(
            CreateJudge0BatchSubmissionRequest request, boolean base64Encoded)
            throws Judge0ConnectionException, Judge0InvalidResponseMapException {

        URI uri =
                getUri(
                        "/submissions/batch",
                        Map.of("base64_encoded", String.valueOf(base64Encoded), "fields", "*"));

        HttpResponse<String> res = executePostRequest(uri, request);

        if (res.statusCode() == 201) {
            List<Judge0SubmissionAsyncResult> result =
                    castResponse(res, new TypeReference<List<Judge0SubmissionAsyncResult>>() {});
            return Judge0Response.success(result, 201);
        }

        return handleResponseForError(res);
    }

    @Override
    public Judge0Response<Judge0SubmissionResult> getSubmission(String token, boolean base64Encoded)
            throws Judge0ConnectionException, Judge0InvalidResponseMapException {

        URI uri =
                getUri(
                        "/submissions/" + token,
                        Map.of("base64_encoded", String.valueOf(base64Encoded), "fields", "*"));

        HttpResponse<String> res = executeGetRequest(uri);

        if (res.statusCode() == 200) {
            Judge0SubmissionResult result = castResponse(res, Judge0SubmissionResult.class);
            return Judge0Response.success(result, 200);
        }

        return handleResponseForError(res);
    }

    @Override
    public Judge0Response<List<Judge0SubmissionResult>> getBatchSubmissions(
            List<String> tokens, boolean returnAsBase64)
            throws Judge0ConnectionException, Judge0InvalidResponseMapException {

        String tokenString = String.join(",", tokens);

        URI uri =
                getUri(
                        "/submissions/batch",
                        Map.of(
                                "base64_encoded",
                                String.valueOf(returnAsBase64),
                                "tokens",
                                tokenString,
                                "fields",
                                "*"));

        HttpResponse<String> res = executeGetRequest(uri);

        if (res.statusCode() == 200) {
            JsonNode rootNode = castResponseToNode(res);
            JsonNode submissionsNode = rootNode.get("submissions");

            if (submissionsNode == null || !submissionsNode.isArray()) {
                throw new Judge0InvalidResponseMapException(
                        "Expected 'submissions' array in batch result");
            }

            List<Judge0SubmissionResult> result =
                    Try.ofChecked(
                                    () ->
                                            objectMapper.readValue(
                                                    submissionsNode.toString(),
                                                    new TypeReference<
                                                            List<Judge0SubmissionResult>>() {}))
                            .getOrElseThrow(
                                    e ->
                                            new Judge0InvalidResponseMapException(
                                                    "Failed to map batch submission results", e));

            return Judge0Response.success(result, 200);
        }

        return handleResponseForError(res);
    }

    @Override
    public Judge0Response<List<Judge0Language>> getActiveLanguages()
            throws Judge0ConnectionException, Judge0InvalidResponseMapException {

        HttpResponse<String> res = executeGetRequest(getUri("/languages"));

        if (res.statusCode() == 200) {
            List<Judge0Language> result = castResponse(res, new TypeReference<>() {});
            return Judge0Response.success(result, 200);
        }

        return handleResponseForError(res);
    }

    @Override
    public Judge0Response<List<Judge0Status>> getAllStatues()
            throws Judge0ConnectionException, Judge0InvalidResponseMapException {

        HttpResponse<String> res = executeGetRequest(getUri("/statuses"));

        if (res.statusCode() == 200) {
            List<Judge0Status> result = castResponse(res, new TypeReference<>() {});
            return Judge0Response.success(result, 200);
        }

        return handleResponseForError(res);
    }

    @Override
    public Judge0Response<Judge0Configuration> getConfiguration()
            throws Judge0InvalidResponseMapException, Judge0ConnectionException {

        HttpResponse<String> res = executeGetRequest(getUri("/config_info"));

        if (res.statusCode() == 200) {
            var result = castResponse(res, Judge0Configuration.class);
            return Judge0Response.success(result, 200);
        }

        return handleResponseForError(res);
    }

    @Override
    public Judge0Response<String> getVersion()
            throws Judge0ConnectionException, Judge0InvalidResponseMapException {
        HttpResponse<String> res = executeGetRequest(getUri("/about"));

        if (res.statusCode() == 200) {
            var result = castResponseToNode(res);
            String version = result.has("version") ? result.get("version").asString() : null;
            if (version == null) {
                throw new Judge0InvalidResponseMapException(
                        "Unexpected error, could not get version");
            }

            return Judge0Response.success(version, 200);
        }

        return handleResponseForError(res);
    }

    // --- HTTP Helper Methods ---
    private HttpResponse<String> executeRequest(HttpRequest request)
            throws Judge0ConnectionException {
        return Try.ofChecked(() -> httpClient.send(request, HttpResponse.BodyHandlers.ofString()))
                .getOrElseThrow(
                        e -> new Judge0ConnectionException("Failed connecting to server", e));
    }

    private HttpResponse<String> executeGetRequest(URI uri) throws Judge0ConnectionException {
        HttpRequest.Builder httpRequestBuilder =
                HttpRequest.newBuilder(uri).header("content-type", "application/json").GET();
        getAuthHeadersConsumer().accept(httpRequestBuilder);
        return executeRequest(httpRequestBuilder.build());
    }

    private <T> HttpResponse<String> executePostRequest(URI uri, T reqBody)
            throws Judge0ConnectionException {
        String jsonReqBody =
                Try.ofChecked(() -> objectMapper.writeValueAsString(reqBody)).getOrElseThrow();

        HttpRequest.Builder httpRequestBuilder =
                HttpRequest.newBuilder(uri)
                        .header("content-type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonReqBody));
        getAuthHeadersConsumer().accept(httpRequestBuilder);
        return executeRequest(httpRequestBuilder.build());
    }

    /**
     * Builds a URI with optional query parameters using native Java classes.
     */
    private URI getUri(String path, Map<String, String> queryParams)
            throws Judge0ConnectionException {
        return Try.ofChecked(
                        () -> {
                            String baseUrl = getBaseUrl();
                            // Ensure no double slashes at the join
                            String fullPath =
                                    (baseUrl.endsWith("/") && path.startsWith("/"))
                                            ? baseUrl + path.substring(1)
                                            : (!baseUrl.endsWith("/") && !path.startsWith("/"))
                                                    ? baseUrl + "/" + path
                                                    : baseUrl + path;

                            if (queryParams == null || queryParams.isEmpty()) {
                                return URI.create(fullPath);
                            }

                            String queryString =
                                    queryParams.entrySet().stream()
                                            .map(
                                                    entry ->
                                                            encodeValue(entry.getKey())
                                                                    + "="
                                                                    + encodeValue(entry.getValue()))
                                            .collect(Collectors.joining("&"));

                            return URI.create(fullPath + "?" + queryString);
                        })
                .getOrElseThrow(e -> new Judge0ConnectionException("Bad URL construction", e));
    }

    private URI getUri(String path) throws Judge0ConnectionException {
        return getUri(path, null);
    }

    private String encodeValue(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    // --- JSON Mapping Helpers ---
    private <T> T castResponse(HttpResponse<String> response, Class<T> clazz)
            throws Judge0InvalidResponseMapException {
        return Try.ofChecked(() -> objectMapper.readValue(response.body(), clazz))
                .getOrElseThrow(
                        e -> new Judge0InvalidResponseMapException("Unexpected response", e));
    }

    private JsonNode castResponseToNode(HttpResponse<String> response)
            throws Judge0InvalidResponseMapException {
        return Try.ofChecked(() -> objectMapper.readTree(response.body()))
                .getOrElseThrow(
                        e -> new Judge0InvalidResponseMapException("Unexpected response", e));
    }

    private <T> T castResponse(HttpResponse<String> response, TypeReference<T> reference)
            throws Judge0InvalidResponseMapException {
        return Try.ofChecked(() -> objectMapper.readValue(response.body(), reference))
                .getOrElseThrow(
                        e -> new Judge0InvalidResponseMapException("Unexpected response", e));
    }

    private <T> Judge0Response<T> handleResponseForError(HttpResponse<String> res)
            throws Judge0InvalidResponseMapException {
        JsonNode rootNode = castResponseToNode(res);

        Supplier<String> errorMsgSupplier =
                () -> {
                    if (!rootNode.isObject()) {
                        return "\"message\": \"Invalid error structure\"";
                    }
                    StringBuilder sb = new StringBuilder("{");
                    Set<Map.Entry<String, JsonNode>> fields = rootNode.properties();

                    AtomicInteger i = new AtomicInteger();
                    fields.forEach(
                            (entry) -> {
                                sb.append(stringifyNode(entry.getKey(), entry.getValue()));
                                if (i.get() < rootNode.size() - 1) {
                                    sb.append(", ");
                                }
                                i.incrementAndGet();
                            });
                    sb.append("}");
                    return sb.toString();
                };
        return Judge0Response.error(errorMsgSupplier.get(), res.statusCode());
    }

    private String stringifyNode(String key, JsonNode node) {

        try {
            String valueStr = objectMapper.writeValueAsString(node);
            String escapedValue = valueStr.replace("\"", "\\\"");
            return String.format("\"%s\": \"%s\"", key, escapedValue);
        } catch (JacksonException e) {
            return String.format("\"%s\": \"%s\"", key, node.toString());
        }
    }
}
