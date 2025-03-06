package com.webCrawler;

import java.io.IOException;

public abstract class AbstractWebCrawler {
    protected String url;
    public final void crawlWebsite() throws IOException {
        connectToWebsite();
        fetchPage();
        parsePage();
        disconnectWebsite();
    }
    public AbstractWebCrawler() {
    }
    public AbstractWebCrawler(String url) {
        this.url = url;
    }
    public abstract void connectToWebsite();
    public abstract void fetchPage() throws IOException;
    public abstract void parsePage() throws IOException;
    public abstract void process() throws IOException;
    public void disconnectWebsite(){

    }
}
