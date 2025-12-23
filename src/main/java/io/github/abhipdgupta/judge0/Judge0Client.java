/* (C)2025 */
package io.github.abhipdgupta.judge0;

import io.github.abhipdgupta.judge0.exceptions.Judge0ConnectionException;
import io.github.abhipdgupta.judge0.exceptions.Judge0InvalidResponseMapException;
import io.github.abhipdgupta.judge0.models.CreateJudge0BatchSubmissionRequest;
import io.github.abhipdgupta.judge0.models.CreateJudge0SubmissionRequest;
import io.github.abhipdgupta.judge0.models.Judge0Configuration;
import io.github.abhipdgupta.judge0.models.Judge0Language;
import io.github.abhipdgupta.judge0.models.Judge0Response;
import io.github.abhipdgupta.judge0.models.Judge0Status;
import io.github.abhipdgupta.judge0.models.Judge0SubmissionAsyncResult;
import io.github.abhipdgupta.judge0.models.Judge0SubmissionResult;
import io.github.abhipdgupta.judge0.models.SubmissionBaseResult;
import java.util.List;

public interface Judge0Client {

    Judge0Response<SubmissionBaseResult> createSubmission(
            CreateJudge0SubmissionRequest request, boolean base64Encoded, boolean waitFor)
            throws Judge0ConnectionException, Judge0InvalidResponseMapException;

    Judge0Response<List<Judge0SubmissionAsyncResult>> createBatchSubmission(
            CreateJudge0BatchSubmissionRequest request, boolean base64Encoded)
            throws Judge0ConnectionException, Judge0InvalidResponseMapException;

    Judge0Response<Judge0SubmissionResult> getSubmission(String token, boolean base64Encoded)
            throws Judge0ConnectionException, Judge0InvalidResponseMapException;

    Judge0Response<List<Judge0SubmissionResult>> getBatchSubmissions(
            List<String> tokens, boolean returnAsBase64)
            throws Judge0ConnectionException, Judge0InvalidResponseMapException;

    Judge0Response<List<Judge0Language>> getActiveLanguages()
            throws Judge0ConnectionException, Judge0InvalidResponseMapException;

    Judge0Response<List<Judge0Status>> getAllStatues()
            throws Judge0ConnectionException, Judge0InvalidResponseMapException;

    Judge0Response<Judge0Configuration> getConfiguration()
            throws Judge0InvalidResponseMapException, Judge0ConnectionException;

    Judge0Response<String> getVersion()
            throws Judge0ConnectionException, Judge0InvalidResponseMapException;
}
