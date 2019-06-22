package com.example.mora.myproject.com.database;

import org.litepal.crud.DataSupport;

public class comment_db extends DataSupport{
    String username;
    String comment;
    String Title;
    public String getUsername() {
        return username;
    }

    public comment_db setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public comment_db setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getTitle() {
        return Title;
    }

    public comment_db setTitle(String title) {
        Title = title;
        return this;
    }
}
