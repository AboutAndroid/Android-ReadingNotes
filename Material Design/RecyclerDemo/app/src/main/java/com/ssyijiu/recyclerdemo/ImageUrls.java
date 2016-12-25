package com.ssyijiu.recyclerdemo;

import java.util.ArrayList;

/**
 * Created by ssyijiu on 2016/12/24.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public enum  ImageUrls {
    INSTANCE;
    public ArrayList<String> mUrls = new ArrayList<>();

    public String ONLY = "http://imgsrc.baidu.com/forum/w%3D580/sign=2e3af6da88b1cb133e693c1bed5556da/c50d5809c93d70cf21553738fddcd100baa12b0f.jpg";

    ImageUrls() {
        mUrls.add("http://ww2.sinaimg.cn/large/610dc034jw1faza3ghd2lj20f00k1gof.jpg");
        mUrls.add("http://ww3.sinaimg.cn/large/610dc034gw1fay98gt0ocj20u011hn24.jpg");
        mUrls.add("http://ww2.sinaimg.cn/large/610dc034jw1fawx09uje2j20u00mh43f.jpg");
        mUrls.add("http://ww4.sinaimg.cn/large/610dc034jw1favb116hm2j20u00u0gqi.jpg");
        mUrls.add("http://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg");
        mUrls.add("http://ww2.sinaimg.cn/large/610dc034gw1farbzjliclj20u00u076w.jpg");

        mUrls.add("http://ww2.sinaimg.cn/large/610dc034gw1faq15nnc0xj20u00u0wlq.jpg");
        mUrls.add("http://ww4.sinaimg.cn/large/610dc034jw1fanrdyaxi6j20u00k1ta9.jpg");
        mUrls.add("http://ww1.sinaimg.cn/large/610dc034jw1faj6sozkluj20u00nt75p.jpg");
        mUrls.add("http://ww1.sinaimg.cn/large/610dc034jw1fahy9m7xw0j20u00u042l.jpg");
        mUrls.add("http://ww4.sinaimg.cn/large/610dc034jw1fagrnmiqm1j20u011hanr.jpg");
    }

    public String get(int index) {
        return mUrls.get(index);
    }

    public int size() {
        return mUrls.size();
    }
}
