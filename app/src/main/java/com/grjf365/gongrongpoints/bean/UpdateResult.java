package com.grjf365.gongrongpoints.bean;

/**
 * Created by vip on 2018/4/24.
 */

public class UpdateResult {

    private UpdateBean currentVersion;
    private UpdateBean newestVersion;

    public UpdateBean getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(UpdateBean currentVersion) {
        this.currentVersion = currentVersion;
    }

    public UpdateBean getNewestVersion() {
        return newestVersion;
    }

    public void setNewestVersion(UpdateBean newestVersion) {
        this.newestVersion = newestVersion;
    }
}
