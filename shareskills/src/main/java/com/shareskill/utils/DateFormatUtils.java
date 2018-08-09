package com.shareskill.utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtils {
    /**
     * 日期对象转字符串对象
     * @param date
     * @return String
     */
    public static String changeDateToString(Date date ){
        if (date!=null)
            return DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINESE).format(date);
        else
            return null;

    }

    /**
     * 字符串时间对象转日期date
     * @param strDate
     * @return date or null
     */
    public static  Date changeStringToDate(String strDate){
        Date date=null;
        try{
            if (strDate!=null)
                 date=DateFormat.getDateInstance(DateFormat.SHORT,new Locale("zh")).parse(strDate);
            else
                return null;
        }catch (Exception e ){
            e.printStackTrace();
            return null;
        }
       return date;
    }
}
