package com.example.task_manager.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task {
    @Id
    private int id;
    private String title;
    private String timing;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTiming() {
        return timing;
    }
    public void setTiming(String timing) {
        this.timing = timing;
    }
    @Override
    public String toString() {
        return "Task [id=" + id + ", timing=" + timing + ", title=" + title + "]";
    }


}
