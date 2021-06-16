package jp.co.axiz.web.util;

import java.util.ArrayList;
import java.util.List;

import jp.co.axiz.web.entity.User;

public class ParamUtil {
	public static boolean isNullOrEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 引数に指定した文字列が数値に変換できるかを判定
     */
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return false;
        }

        return true;
    }

    /**
     * 引数に指定した文字列が数値に変換して返却する。
     * 変換できない場合はnullを返却する。
     */
    public static Integer checkAndParseInt(String str) {
        if (isNumber(str)) {
            return Integer.parseInt(str);
        } else {
            return null;
        }
    }

//
    public static boolean allItemsAreNull(String ...string) {
    	List<String> strList = new ArrayList<String>();
    	for(int i = 0; i < string.length; i++) {
    		strList.add(string[i]);
    	}
    	for(String str : strList) {
			if(!ParamUtil.isNullOrEmpty(str)) {
				return false;
			}
		}
    	return true;
    }

    public static boolean compareTwoUsers(User user1, User user2) {
//    	System.out.println("1" + user2);
    	if(user1.toString().equals(user2.toString())) {
    		return true;
    	}
    	return false;
    }
}















