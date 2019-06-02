package com.example.project_git_stats;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


public class GitHubAPI {
    final private String baseURL = "https://api.github.com/";
    private JSONArray jsonNode;

    private String repoName;
    private String orgName;

    public String getRepoName() {
        return repoName;
    }

    public String getOrgName() {
        return orgName;
    }


    public GitHubAPI(String repoName, String orgName) {
        this.repoName = repoName;
        this.orgName = orgName;
        getRepoStatus();
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    private void getRepoStatus() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github.v3+json");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL + "repos/" + orgName + "/" + repoName + "/commits");
         /*       .queryParam("msisdn", msisdn)
                .queryParam("email", email)
                .queryParam("clientVersion", clientVersion)
                .queryParam("clientType", clientType)
                .queryParam("issuerName", issuerName)
                .queryParam("applicationName", applicationName);*/

        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        try {
            jsonNode = new JSONArray(response.getBody());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public double getPercent() throws JSONException {
        for (int i = 0; i < jsonNode.length(); i++) {
            JSONObject jsonObj = jsonNode.getJSONObject(i);

            System.out.println(jsonObj.getJSONObject("commit").get("author").toString());
        }

        return 2.5;
    }
}