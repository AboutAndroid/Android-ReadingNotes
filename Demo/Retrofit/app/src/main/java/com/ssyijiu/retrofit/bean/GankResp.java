package com.ssyijiu.retrofit.bean;

import java.util.List;

/**
 * Created by lxm on 2016/10/13.
 */
public class GankResp {

    /**
     * error : false
     * results : [{"_id":"57fca10c421aa95dd78e8de5","createdAt":"2016-10-11T16:21:32.80Z","desc":"转换QQ微信语音格式转为mp3","publishedAt":"2016-10-12T11:40:02.146Z","source":"chrome","type":"Android","url":"https://github.com/ketn4391/android_silk_v3_decoder","used":true,"who":"蒋朋"},{"_id":"57fd262f421aa95dd351b104","createdAt":"2016-10-12T01:49:35.190Z","desc":"简化上传图片之前必需的压缩方法","publishedAt":"2016-10-12T11:40:02.146Z","source":"web","type":"Android","url":"https://github.com/shaohui10086/AdvancedLuban","used":true,"who":"邵辉Vista"},{"_id":"57fd9964421aa95dd351b106","createdAt":"2016-10-12T10:01:08.961Z","desc":"一个漂亮的 Share Button UI 效果。","images":["http://img.gank.io/ee1fcfbd-20a5-4819-943c-80d55301dc4d"],"publishedAt":"2016-10-12T11:40:02.146Z","source":"chrome","type":"Android","url":"https://github.com/kayan1990/ShareButton","used":true,"who":"代码家"},{"_id":"57fd9b91421aa95dd78e8df2","createdAt":"2016-10-12T10:10:25.710Z","desc":"让 Intellij 支持 Emoji 输入提醒","images":["http://img.gank.io/eb556ea9-e0f3-4fe2-a5fb-fc0148f88e6d"],"publishedAt":"2016-10-12T11:40:02.146Z","source":"chrome","type":"Android","url":"https://github.com/shiraji/emoji","used":true,"who":"代码家"},{"_id":"57fd9c72421aa95dd78e8df3","createdAt":"2016-10-12T10:14:10.459Z","desc":"支持自动计数的 EditText，做社交限制字数的时候非常有用。","images":["http://img.gank.io/87ba3b71-384c-4e77-8b8b-b98b5e5d5c12"],"publishedAt":"2016-10-12T11:40:02.146Z","source":"chrome","type":"Android","url":"https://github.com/FTandJYQ/AnFQNumEditText","used":true,"who":"代码家"},{"_id":"57fdab07421aa95dd78e8dfc","createdAt":"2016-10-12T11:16:23.433Z","desc":"仿Cuto的加载动画","images":["http://img.gank.io/20e7756e-9243-451a-a8b7-0a5115c0220a"],"publishedAt":"2016-10-12T11:40:02.146Z","source":"web","type":"Android","url":"https://github.com/andyxialm/CutoLoadingView","used":true,"who":null},{"_id":"57f0e616421aa95ddb9cb577","createdAt":"2016-10-02T18:48:54.166Z","desc":"又一个LowPoly图片, 另外这个还可以让图片变成沙画","images":["http://img.gank.io/4b93fe53-bb4f-4dfb-b058-4fc7aebdedce","http://img.gank.io/917ce961-f026-4bdf-9f36-7877709805cd"],"publishedAt":"2016-10-11T11:42:22.814Z","source":"web","type":"Android","url":"https://github.com/xyzxqs/XLowPoly","used":true,"who":"xyzxqs"},{"_id":"57fb0f38421aa95dd351b0ef","createdAt":"2016-10-10T11:47:04.29Z","desc":"豆瓣的混合开发框架 -- Rexxar","publishedAt":"2016-10-11T11:42:22.814Z","source":"chrome","type":"Android","url":"https://github.com/douban/rexxar-android","used":true,"who":"wuzheng"},{"_id":"57fb4987421aa95dd351b0f2","createdAt":"2016-10-10T15:55:51.411Z","desc":"当Rxjava遇上databinding","images":["http://img.gank.io/1a9db224-2809-4fca-bfb9-22d467bfe07e"],"publishedAt":"2016-10-11T11:42:22.814Z","source":"chrome","type":"Android","url":"https://github.com/TangoAgency/android-data-binding-rxjava","used":true,"who":"有时放纵"},{"_id":"57fc4248421aa95de3b8ab7c","createdAt":"2016-10-11T09:37:12.796Z","desc":"支持自动高亮关键字的 TextView，很实用。","images":["http://img.gank.io/1296065e-d8a3-4edc-8d70-ce9e5bdfe79b"],"publishedAt":"2016-10-11T11:42:22.814Z","source":"chrome","type":"Android","url":"https://github.com/wangshaolei/UnderLineLinkTextView","used":true,"who":"机器人"}]
     */

    private boolean error;
    /**
     * _id : 57fca10c421aa95dd78e8de5
     * createdAt : 2016-10-11T16:21:32.80Z
     * desc : 转换QQ微信语音格式转为mp3
     * publishedAt : 2016-10-12T11:40:02.146Z
     * source : chrome
     * type : Android
     * url : https://github.com/ketn4391/android_silk_v3_decoder
     * used : true
     * who : 蒋朋
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
