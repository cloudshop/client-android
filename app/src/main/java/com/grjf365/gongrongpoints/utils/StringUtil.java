package com.grjf365.gongrongpoints.utils;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vip on 2018/4/15.
 */

public class StringUtil {
    public static Map<String,String> getUrlParams(String url){

        String[] urlArr = url.split("\\?");
        Map<String,String> urlParams = new HashMap<>();
        if(urlArr.length==2){
            String[] paramsArr = urlArr[1].split("&");
            for(String str : paramsArr){
                if(str.contains("=")){
                    String[] strArr = str.split("=");
                    if(strArr.length == 1){
                        urlParams.put(strArr[0],"");
                    }else if(strArr.length == 2){
                        urlParams.put(strArr[0],strArr[1]);
                    }
                }
            }
        }
        return urlParams;
    }
}
