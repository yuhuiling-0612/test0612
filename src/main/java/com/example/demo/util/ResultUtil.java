package com.example.demo.util;

import com.example.demo.selfEntity.ResultInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class ResultUtil {
    /**
     * 请求成功时返回结果
     * @param data
     * @param code
     * @param msg
     * @param flag
     * @return
     */

    public static ResultInfo getResultInfoOk(Object data, Integer code, String msg, Boolean flag) {
        ResultInfo resultInfo = new ResultInfo(code, msg, data, flag);
        return resultInfo;
    }

    public static ResultInfo getResultInfoOk(Object data, Integer code, String msg) {
        ResultInfo resultInfo = new ResultInfo(code, msg, data, true);
        return resultInfo;
    }
    public static ResultInfo getResultInfoOk(Object data, Boolean flag) {
        ResultInfo resultInfo = new ResultInfo(200, "", data, flag);
        return resultInfo;
    }

    public static ResultInfo getResultInfoOk(Object data, Integer type) {
        ResultInfo resultInfo = null;
        switch (type) {
            case 1: PageInfo pageInfo = new PageInfo((List)data);
                    resultInfo = new ResultInfo(200, "", pageInfo, true);
                    break;
            case 2: resultInfo = new ResultInfo(200, "", data, true);
                    break;
            default:
        }
        return resultInfo;
    }

    public static ResultInfo getResultInfoOk(Object data) {
        ResultInfo resultInfo = null;
        if(data instanceof List) {
            PageInfo pageInfo = new PageInfo((List)data);
            resultInfo = new ResultInfo(200, "", pageInfo, true);
        } else {
            resultInfo = new ResultInfo(200, "", data, true);
        }
        return resultInfo;
    }

    /**
     * 请求失败返回的结果
     * @param data
     * @param code
     * @param msg
     * @return
     */
     public static ResultInfo getResultInfoFailed(Object data, Integer code, String msg, Boolean flag) {
         ResultInfo resultInfo = new ResultInfo(code, msg, data, false);
         return resultInfo;
     }
     public static ResultInfo getResultInfoFailed(Object data, String msg) {
         ResultInfo resultInfo = new ResultInfo(200, msg, data,false);
         return resultInfo;
     }
}
