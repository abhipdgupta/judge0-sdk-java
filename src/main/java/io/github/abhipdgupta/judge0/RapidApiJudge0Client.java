/* (C)2025 */
package io.github.abhipdgupta.judge0;

import java.net.http.HttpRequest;
import java.util.function.Consumer;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor(staticName = "of")
public final class RapidApiJudge0Client extends AbstractJudge0Client {
    private String baseUrl;
    private String apiKey;
    private String host;

    public RapidApiJudge0Client() {
        super();
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public Consumer<HttpRequest.Builder> getAuthHeadersConsumer() {
        return builder -> builder.header("x-rapidapi-key", apiKey).header("x-rapidapi-host", host);
    }
}
