import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

// class that helps to read the data and assign them to variables
public class Reader {
    //variables
    private URL url;
    private BufferedReader in;
    private String inputLine;

    public Reader() {
    }

    //method to read all text from url and return text in String, parameter url - address URL
    public String readUrl(String url) throws IOException {
        this.url = new URL(url);
        in = new BufferedReader(new InputStreamReader(this.url.openStream()));
        String jsonText="";
        while ((inputLine = in.readLine()) != null)
            jsonText+=inputLine;
        in.close();
        return jsonText;
    }

    //method deserialization json text to array of objects Post (using Gson from package com.google)
    public Post[] readPosts(String url) throws IOException {
        String jsonPosts = readUrl(url);
        Post[] posts = new Gson().fromJson(jsonPosts, Post[].class);
        return posts;
    }

    //method deserialization json text to array of objects User (using Gson from package com.google)
    public User[] readUsers(String url) throws IOException {
        String jsonUsers = readUrl(url);
        User[] users = new Gson().fromJson(jsonUsers, User[].class);
        return users;
    }
}