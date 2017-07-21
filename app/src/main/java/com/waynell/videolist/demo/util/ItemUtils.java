package com.waynell.videolist.demo.util;

import com.waynell.videolist.demo.model.BaseItem;
import com.waynell.videolist.demo.model.PicItem;
import com.waynell.videolist.demo.model.TextItem;
import com.waynell.videolist.demo.model.VideoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wayne
 */
public class ItemUtils {

    private static final String VIDEO_URL1 = "http://7xl1ve.com5.z0.glb.qiniucdn.com/2017/05/22/20/17/99e3f8ac7a5740a8a3e789106ea5ecc8/90.mp4";
    private static final String VIDEO_URL2 = "http://7xl1ve.com5.z0.glb.qiniucdn.com/2017/04/12/12/35/a8b32348f0ce44b7a2b16df5e7bbe2ed.mp4";
    private static final String VIDEO_URL3 = "http://7xl1ve.com5.z0.glb.qiniucdn.com/2017/04/13/07/19/b712aff78e6b4d6699268c8168756569.mp4";
    private static final String VIDEO_URL4 = "http://7xl1ve.com5.z0.glb.qiniucdn.com/2017/04/10/21/35/d18bf03251af4d5a99f1d171116de02e.mp4";

    private static final String PIC_URL1 = "http://img10.3lian.com/sc6/show02/67/27/03.jpg";
    private static final String PIC_URL2 = "http://img10.3lian.com/sc6/show02/67/27/04.jpg";
    private static final String PIC_URL3 = "http://img10.3lian.com/sc6/show02/67/27/01.jpg";
    private static final String PIC_URL4 = "http://img10.3lian.com/sc6/show02/67/27/02.jpg";

    public static List<BaseItem> generateMockData() {
        List<BaseItem> list = new ArrayList<>();

        list.add(new TextItem("TextItem"));
        list.add(new PicItem(PIC_URL1));
        list.add(new VideoItem(VIDEO_URL4, PIC_URL4));

        list.add(new TextItem("TextItem"));
        list.add(new PicItem(PIC_URL2));
        list.add(new VideoItem(VIDEO_URL3, PIC_URL3));

        list.add(new TextItem("TextItem"));
        list.add(new PicItem(PIC_URL3));
        list.add(new VideoItem(VIDEO_URL2, PIC_URL2));

        list.add(new TextItem("TextItem"));
        list.add(new PicItem(PIC_URL4));
        list.add(new VideoItem(VIDEO_URL1, PIC_URL1));

        list.add(new TextItem("TextItem"));
        list.add(new PicItem(PIC_URL1));
        list.add(new VideoItem(VIDEO_URL4, PIC_URL4));

        list.add(new TextItem("TextItem"));
        list.add(new PicItem(PIC_URL2));
        list.add(new VideoItem(VIDEO_URL3, PIC_URL3));

        list.add(new TextItem("TextItem"));
        list.add(new PicItem(PIC_URL3));
        list.add(new VideoItem(VIDEO_URL2, PIC_URL2));

        list.add(new TextItem("TextItem"));
        list.add(new PicItem(PIC_URL4));
        list.add(new VideoItem(VIDEO_URL1, PIC_URL1));

        return list;
    }

}
