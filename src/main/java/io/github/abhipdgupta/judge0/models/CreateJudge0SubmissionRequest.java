/* (C)2025 */
package io.github.abhipdgupta.judge0.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateJudge0SubmissionRequest {
    @JsonProperty("language_id")
    private int languageId;

    @JsonProperty("source_code")
    private String sourceCode;

    private String stdin;

    @JsonProperty("compiler_options")
    private String compilerOptions;

    @JsonProperty("command_line_arguments")
    private String commandLineArguments;

    @JsonProperty("expected_output")
    private String expectedOutput;

    @JsonProperty("cpu_time_limit")
    private Float cpuTimeLimit;

    @JsonProperty("cpu_extra_time")
    private Float cpuExtraTime;

    @JsonProperty("wall_time_limit")
    private Float wallTimeLimit;

    @JsonProperty("memory_limit")
    private Float memoryLimitInKb;

    @JsonProperty("stack_limit")
    private Integer stackLimitInKb;

    @JsonProperty("max_processes_and_or_threads")
    private Integer maxProcessesAndOrThreads;

    @JsonProperty("enable_per_process_and_thread_time_limit")
    private Boolean enablePerProcessAndThreadTimeLimit;

    @JsonProperty("enable_per_process_and_thread_memory_limit")
    private Boolean enablePerProcessAndThreadMemoryLimit;

    @JsonProperty("max_file_size")
    private Integer maxFileSizeInKb;

    @JsonProperty("redirect_stderr_to_stdout")
    private Boolean redirectStderrToStdout;

    @JsonProperty("enable_network")
    private Boolean enableNetwork;

    @JsonProperty("number_of_runs")
    private Integer numberOfRuns;

    @JsonProperty("additional_files")
    private String additionalFiles;

    @JsonProperty("callback_url")
    private String callbackUrl;
}
