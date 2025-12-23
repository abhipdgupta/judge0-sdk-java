/* (C)2025 */
package io.github.abhipdgupta.judge0.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Judge0Configuration {
    @JsonProperty("maIntegerenance_mode")
    private Boolean maIntegerenanceMode;

    @JsonProperty("enable_wait_result")
    private Boolean enableWaitResult;

    @JsonProperty("enable_compiler_options")
    private Boolean enableCompilerOptions;

    @JsonProperty("allowed_languages_for_compile_options")
    private List<String> allowedLanguagesForCompileOptions;

    @JsonProperty("enable_command_line_arguments")
    private Boolean enableCommandLineArguments;

    @JsonProperty("enable_submission_delete")
    private Boolean enableSubmissionDelete;

    @JsonProperty("enable_callbacks")
    private Boolean enableCallbacks;

    @JsonProperty("callbacks_max_tries")
    private Integer callbacksMaxTries;

    @JsonProperty("callbacks_timeout")
    private Integer callbacksTimeout;

    @JsonProperty("enable_additional_files")
    private Boolean enableAdditionalFiles;

    @JsonProperty("max_queue_size")
    private Integer maxQueueSize;

    @JsonProperty("cpu_time_limit")
    private Integer cpuTimeLimit;

    @JsonProperty("max_cpu_time_limit")
    private Integer maxCpuTimeLimit;

    @JsonProperty("cpu_extra_time")
    private Integer cpuExtraTime;

    @JsonProperty("max_cpu_extra_time")
    private Integer maxCpuExtraTime;

    @JsonProperty("wall_time_limit")
    private Integer wallTimeLimit;

    @JsonProperty("max_wall_time_limit")
    private Integer maxWallTimeLimit;

    @JsonProperty("memory_limit")
    private Integer memoryLimit;

    @JsonProperty("max_memory_limit")
    private Integer maxMemoryLimit;

    @JsonProperty("stack_limit")
    private Integer stackLimit;

    @JsonProperty("max_stack_limit")
    private Integer maxStackLimit;

    @JsonProperty("max_processes_and_or_threads")
    private Integer maxProcessesAndOrThreads;

    @JsonProperty("max_max_processes_and_or_threads")
    private Integer maxMaxProcessesAndOrThreads;

    @JsonProperty("enable_per_process_and_thread_time_limit")
    private Boolean enablePerProcessAndThreadTimeLimit;

    @JsonProperty("allow_enable_per_process_and_thread_time_limit")
    private Boolean allowEnablePerProcessAndThreadTimeLimit;

    @JsonProperty("enable_per_process_and_thread_memory_limit")
    private Boolean enablePerProcessAndThreadMemoryLimit;

    @JsonProperty("allow_enable_per_process_and_thread_memory_limit")
    private Boolean allowEnablePerProcessAndThreadMemoryLimit;

    @JsonProperty("max_file_size")
    private Integer maxFileSize;

    @JsonProperty("max_max_file_size")
    private Integer maxMaxFileSize;

    @JsonProperty("number_of_runs")
    private Integer numberOfRuns;

    @JsonProperty("max_number_of_runs")
    private Integer maxNumberOfRuns;

    @JsonProperty("redirect_stderr_to_stdout")
    private Boolean redirectStderrToStdout;

    @JsonProperty("max_extract_size")
    private Integer maxExtractSize;

    @JsonProperty("enable_batched_submissions")
    private Boolean enableBatchedSubmissions;

    @JsonProperty("max_submission_batch_size")
    private Integer maxSubmissionBatchSize;

    @JsonProperty("submission_cache_duration")
    private Integer submissionCacheDuration;

    @JsonProperty("use_docs_as_homepage")
    private Boolean useDocsAsHomepage;

    @JsonProperty("allow_enable_network")
    private Boolean allowEnableNetwork;

    @JsonProperty("enable_network")
    private Boolean enableNetwork;
}
