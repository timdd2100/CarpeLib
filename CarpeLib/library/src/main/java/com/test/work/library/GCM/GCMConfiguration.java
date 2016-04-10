package com.test.work.library.GCM;

import java.util.ArrayList;


public class GCMConfiguration {

    //262666519874

    // edoc 1063942889959
    public static String PROJECT_NUMBER = "";


    protected static ArrayList<String> MsgTagList = new ArrayList<String>();
    protected static final String GCM_SERVER_URL = "http://carpediemgcm.gear.host/";
    protected static final String MSG_KEY = "message";


    /**
     * 增加tag
     * @param tag
     */
    public static void addMsgTag(String tag) {
        MsgTagList.add(tag);
    }

    public static ArrayList<String> getMsgTagList() {
        return MsgTagList;
    }

}
