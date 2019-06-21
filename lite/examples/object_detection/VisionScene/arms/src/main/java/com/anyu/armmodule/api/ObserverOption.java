package com.anyu.armmodule.api;

import android.content.Context;

/**
 * Created by gaoqiong on 2019/5/16
 */
public class ObserverOption {
    public Context context;
    public String textShow;
    public boolean notShow;

    public ObserverOption(Builder builder) {
        this.context = builder.getContext();
        this.textShow = builder.getTextShow();
        this.notShow = builder.isNotShow();
    }

    public static class Builder {
        private Context context;
        private String textShow;
        private boolean notShow;

        public Builder() {
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setTextShow(String textShow) {
            this.textShow = textShow;
            return this;
        }

        public Builder setNotShow(boolean notShow) {
            this.notShow = notShow;
            return this;
        }

        public Context getContext() {
            return context;
        }

        public String getTextShow() {
            return textShow;
        }

        public boolean isNotShow() {
            return notShow;
        }

        public ObserverOption build() {
            return new ObserverOption(this);
        }
    }

}
