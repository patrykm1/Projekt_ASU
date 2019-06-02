package com.example.project_git_stats;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.json.JSONException;

import java.net.URL;

@Route("main")
public class GUI extends VerticalLayout {
    private TextArea output = new TextArea("output will show up here");
    private TextField repoURL = new TextField("Link to the repository");
    private Button sendButton = new Button("Send");

    public GUI() {
        add(output);
        add(repoURL);
        add(sendButton);

//        sendButton.addClickListener(buttonClickEvent -> {
//            String urlAfterGithub = repoURL.getValue().split("github.com/")[1];
//            String repoName = urlAfterGithub.split("/")[1];
//            String orgName = urlAfterGithub.split("/")[0];
//            GitHubAPI githubAPI = new GitHubAPI(repoName, orgName);
//            //output.setValue(githubAPI.getRepoStatus());
//            try {
//                githubAPI.getPercent();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        });
        sendButton.addClickListener(buttonClickEvent -> {
            String url = repoURL.getValue();
            GitOther gitOther = new GitOther(url);
        });
    }
}