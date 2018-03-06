package com.seecen.ssh.utils;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import org.apache.struts2.util.StrutsTypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Author: CalcYu
 * Date: 2017/12/18
 */
public class DateConverter extends StrutsTypeConverter {
    //支持转换的多种日期格式，可增加时间格式
    private final DateFormat[] dfs={
            new SimpleDateFormat("yyyy年MM月dd日"),
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("MM/dd/yy"),
            new SimpleDateFormat("yyyy.MM.dd"),
            new SimpleDateFormat("yy.MM.dd"),
            new SimpleDateFormat("yyyy/MM/dd")
    };


    /**
     * 将指定格式字符串转换为日期类型
     */
    @Override
    public Object convertFromString(Map context, String[] values, Class toType) {
        String dateStr=values[0];       //获取日期的字符串
        for (int i = 0; i < dfs.length; i++) {   //遍历日期支持格式，进行转换
            try {
                return dfs[i].parse(dateStr);
            } catch (Exception e) {
                continue;
            }
        }
        //如果遍历完毕后仍没有转换成功，表示出现转换异常
        throw new TypeConversionException();
    }


    /**
     * 将日期转换为指定的字符串格式
     */
    @Override
    public String convertToString(Map context, Object object) {
        Date date=(Date) object;
        //输出格式是yyyy-MM-dd
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}