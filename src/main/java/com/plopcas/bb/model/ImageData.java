package com.plopcas.bb.model;

public class ImageData {
    private String id;
    private String urlViewer;
    private String url;
    private String displayUrl;
    private String time;
    private ImageDataResized thumb;
    private ImageDataResized medium;
    private String deleteUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlViewer() {
        return urlViewer;
    }

    public void setUrlViewer(String urlViewer) {
        this.urlViewer = urlViewer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ImageDataResized getThumb() {
        return thumb;
    }

    public void setThumb(ImageDataResized thumb) {
        this.thumb = thumb;
    }

    public ImageDataResized getMedium() {
        return medium;
    }

    public void setMedium(ImageDataResized medium) {
        this.medium = medium;
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }
}
