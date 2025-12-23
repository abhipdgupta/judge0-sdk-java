/* (C)2025 */
package io.github.abhipdgupta.judge0.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Judge0Response<T> {
    private int statusCode;
    private boolean successful;
    private T data;
    private String error;

    public static <T> Judge0Response<T> success(T data, int statusCode) {
        return Judge0Response.<T>builder()
                .statusCode(statusCode)
                .successful(true)
                .data(data)
                .build();
    }

    public static <T> Judge0Response<T> error(String message, int statusCode) {
        return Judge0Response.<T>builder()
                .statusCode(statusCode)
                .successful(false)
                .error(message)
                .data(null)
                .build();
    }
}
