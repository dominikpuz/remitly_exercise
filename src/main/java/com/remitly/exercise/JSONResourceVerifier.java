package com.remitly.exercise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONResourceVerifier {
    /**
     * @param jsonFilePath path to a JSON file in <a href="https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-iam-role-policy.html">AWS::IAM::Role Policy</a> format
     * @return <code>false</code> if any resource field in JSON file contains a single asterisk or <code>true</code> in any other case
     * @throws IOException   if file does not exist or could not be read
     * @throws JSONException if file is not a valid JSON file or is not in <a href="https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-iam-role-policy.html">AWS::IAM::Role Policy</a> format
     */
    public static boolean verify(String jsonFilePath) throws IOException, JSONException {
        JSONObject inputData = new JSONObject(new String(Files.readAllBytes(Paths.get(jsonFilePath))));

//        checks if required field PolicyName is present
        inputData.getString("PolicyName");

        JSONObject policyDocument = inputData.getJSONObject("PolicyDocument");
        Object statementObject = policyDocument.get("Statement");
        if (statementObject instanceof JSONArray statements) {
            for (Object statement : statements) {
                if (!verifyResource(((JSONObject) statement).get("Resource"))) {
                    return false;
                }
            }
            return true;
        } else {
            return verifyResource(((JSONObject) statementObject).get("Resource"));
        }
    }

    private static boolean verifyResource(Object resource) {
        if (resource instanceof String) {
            return !resource.equals("*");
        } else if (resource == null) {
            throw new JSONException("Resource is null");
        }
        return true;
    }

}
