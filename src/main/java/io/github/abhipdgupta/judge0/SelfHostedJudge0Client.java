/* (C)2025 */
package io.github.abhipdgupta.judge0;

import java.net.http.HttpRequest;
import java.util.function.Consumer;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor(staticName = "of")
public class SelfHostedJudge0Client extends AbstractJudge0Client {
    private final String baseUrl;

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public Consumer<HttpRequest.Builder> getAuthHeadersConsumer() {
        return builder -> {};
    }
}
