package com.example.user1.volleyballmanager20.cmn;

import com.firebase.client.Firebase;
import com.firebase.client.core.Path;
import com.firebase.client.core.Repo;

/**
 * Created by user1 on 26.6.2016 г..
 */
public class MyFirebase extends Firebase {
    public MyFirebase(String url) {
        super(url);
    }

    public MyFirebase(Repo repo, Path path) {
        super(repo, path);
    }
}
