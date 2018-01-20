package com.boxfox.oceanapplication.data;

import io.realm.RealmObject;

/**
 * Created by boxfox on 2018-01-20.
 */

public class FishInfo extends RealmObject{
    private String name, date, location, imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
