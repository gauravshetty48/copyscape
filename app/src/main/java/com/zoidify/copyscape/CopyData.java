package com.zoidify.copyscape;

/**
 * Created by gaurav on 04-02-2017.
 */

public class CopyData {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public long getVideoPosition() {
        return videoPosition;
    }

    public void setVideoPosition(long videoPosition) {
        this.videoPosition = videoPosition;
    }


    private String title, thumbnailUrl, videoId;
    private long videoPosition;
}
