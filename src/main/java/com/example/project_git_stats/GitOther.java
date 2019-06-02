package com.example.project_git_stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.apache.naming.SelectorContext.prefix;

public class GitOther {
    private String URL;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public GitOther(String URL) {
        this.URL = URL;
        cloneRepo();
    }

    private void cloneRepo() {
        Path tempDirWithPrefix;
        try {
            tempDirWithPrefix = Files.createTempDirectory("gowno");
            Runtime rt = Runtime.getRuntime();
            Process p1 = rt.exec("git clone --no-checkout " + URL, null, tempDirWithPrefix.toFile());
            Process pr = rt.exec("git shortlog -s -n", null, tempDirWithPrefix.toFile());
            System.out.println(pr.getOutputStream().toString());

            InputStream is = p1.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
