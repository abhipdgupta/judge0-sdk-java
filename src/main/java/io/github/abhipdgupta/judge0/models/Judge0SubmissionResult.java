/* (C)2025 */
package io.github.abhipdgupta.judge0.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Judge0SubmissionResult extends SubmissionBaseResult {

    @JsonProperty("source_code")
    private String sourceCode;

    @JsonProperty("language_id")
    private Integer languageId;

    private String stdin;

    @JsonProperty("expected_output")
    private String expectedOutput;

    private String stdout;

    @JsonProperty("status_id")
    private Integer statusId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("finished_at")
    private String finishedAt;

    private Float time;

    private Integer memory;

    private String stderr;

    @JsonProperty("number_of_runs")
    private Integer numberOfRuns;

    @JsonProperty("cpu_time_limit")
    private String cpuTimeLimit;

    @JsonProperty("cpu_extra_time")
    private String cpuExtraTime;

    @JsonProperty("wall_time_limit")
    private String wallTimeLimit;

    @JsonProperty("memory_limit")
    private Integer memoryLimit;

    @JsonProperty("stack_limit")
    private Integer stackLimit;

    @JsonProperty("max_processes_and_or_threads")
    private Integer maxProcessesAndOrThreads;

    @JsonProperty("enable_per_process_and_thread_time_limit")
    private Boolean enablePerProcessAndThreadTimeLimit;

    @JsonProperty("enable_per_process_and_thread_memory_limit")
    private Boolean enablePerProcessAndThreadMemoryLimit;

    @JsonProperty("max_file_size")
    private Integer maxFileSize;

    @JsonProperty("compile_output")
    private String compileOutput;

    @JsonProperty("exit_code")
    private Integer exitCode;

    @JsonProperty("exit_signal")
    private String exitSignal;

    private String message;

    @JsonProperty("wall_time")
    private Float wallTime;

    @JsonProperty("compiler_options")
    private String compilerOptions;

    @JsonProperty("command_line_arguments")
    private String commandLineArguments;

    @JsonProperty("redirect_stderr_to_stdout")
    private Boolean redirectStderrToStdout;

    @JsonProperty("callback_url")
    private String callbackUrl;

    @JsonProperty("additional_files")
    private String additionalFiles;

    @JsonProperty("enable_network")
    private Boolean enableNetwork;

    @JsonProperty("post_execution_filesystem")
    private String postExecutionFilesystem;

    private Judge0Status status;

    private Judge0Language language;
}
