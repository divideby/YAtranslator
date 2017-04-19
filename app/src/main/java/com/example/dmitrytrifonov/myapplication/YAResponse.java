package com.example.dmitrytrifonov.myapplication;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dmitrytrifonov on 19.04.17.
 */

public class YAResponse {

        @SerializedName("text")
        @Expose
        private List<String> text = null;

        public List<String> getTranslate() {
            return text;
        }

        public void setTranslate(List<String> text) {
                this.text = text;
        }
}
