package com.test.common.utils;

import com.test.common.utils.dao.ICommonDAO;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * 时间工具
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AI(NanJing)</p>
 * @author Yang Hua
 * @version 1.0
 */
public class TimeUtil {
    private static DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HHmmss");

    /**
     * 由传入的连接获得系统数据库时间
     * @param conn Connection
     * @throws Exception
     * @return Timestamp
     */
    public static Timestamp getSysDate(Connection conn)  throws Exception{
        java.sql.Timestamp result = null;
        String sql = "";
        java.sql.Statement stmt = null;
        java.sql.ResultSet rowSet = null;
        try {
            sql = "select sysdate from dual";
            stmt = conn.createStatement();
            rowSet = stmt.executeQuery(sql);
            if (rowSet.next()) {
                String s = rowSet.getString("sysdate");
                java.text.SimpleDateFormat a = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                result = new java.sql.Timestamp(a.parse(s).getTime());
            }
        }
        catch (Exception e) {
            throw new Exception("读取系统时间错误：" + e.getMessage());
        }finally{
            if(stmt!=null){
                stmt.close();
            }
            if(rowSet!=null){
                rowSet.close();
            }
        }
        return result;

    }
    /**
     * 根据起始时间和结束时间获得之间的天数
     * @param start Timestamp
     * @param end Timestamp
     * @throws Exception
     * @return long
     */
    public static long getBetweenDaysByStartDateAndEndDate(Connection conn,java.sql.Timestamp start,java.sql.Timestamp end) throws Exception{
        long rtn = 0;
        java.sql.Statement stmt = null;
        java.sql.ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String start_str = "to_date('"+dateformat.format(start)+"','yyyy-mm-dd hh24miss')";
            String end_str = "to_date('"+dateformat.format(end)+"','yyyy-mm-dd hh24miss')";
            rs = stmt.executeQuery("select ceil("+end_str+" - "+start_str+") as da from dual");
            if (rs.next()) {
                rtn = rs.getLong("da");
            }
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return rtn;
    }

    /**
     * 格式化日期
     * */

    public static String formatDate(Date date,String formatStr){
        if(StringUtils.isEmpty(formatStr)){
            formatStr = "yyyy-MM-dd HHmmss";
        }
        DateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }
    /**
     * 在一个时间上加上对应的年
     * @param ti long
     * @param i int
     * @throws Exception
     * @return Date
     */
    public static Date addYear(long ti, int i) throws Exception{
        if(i<0){
            throw new Exception("只能加上年");
        }
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.YEAR, i);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 在一个时间上加上对应的月份数
     * @param ti long
     * @param i int
     * @return Date
     */
    public static Date addMonth(long ti, int i) throws Exception{
        if(i<0){
            throw new Exception("只能加上月份");
        }
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.MONTH, i);
        rtn = cal.getTime();
        return rtn;
    }

    /***
     * 在一个时间上加上或减去月份
     * */
    public static Date addOrMinusMonth(long ti,int i) throws Exception{
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.MONTH,i);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 在一个时间上加上或减去周
     * @param ti long
     * @param i int
     * @return Date
     */
    public static Date addOrMinusWeek(long ti, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.WEEK_OF_YEAR, i);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 在一个时间上加上或减去天数
     * @param ti long
     * @param i int
     * @return Date
     */
    public static Date addOrMinusDays(long ti, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.DAY_OF_MONTH, i);
        rtn = cal.getTime();
        return rtn;
    }
    /**
     * 在一个时间上加上或减去小时
     * @param ti long
     * @param i int
     * @return Date
     */
    public static Date addOrMinusHours(long ti, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.HOUR, i);
        rtn = cal.getTime();
        return rtn;
    }
    /**
     * 在一个时间上加上或减去分钟
     * @param ti long
     * @param i int
     * @return Date
     */
    public static Date addOrMinusMinutes(long ti, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.MINUTE, i);
        rtn = cal.getTime();
        return rtn;
    }
    /**
     * 在一个时间上加上或减去秒数
     * @param ti long
     * @param i int
     * @return Date
     */
    public static Date addOrMinusSecond(long ti, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.SECOND, i);
        rtn = cal.getTime();
        return rtn;
    }


    public static String getYYYYMMDDHHMMSS(Date ts) throws Exception{
        if(ts==null){
            return null;
        }
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = dateformat.format(ts);
        return str;
    }

    public static String getYYYYMMDD(Date ts) throws Exception{
        if(ts==null){
            return null;
        }
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String str = dateformat.format(ts);
        return str;
    }


    /**
     * 获得时间的字符串
     * @param ts Timestamp
     * @throws Exception
     * @return String
     */
    public static String getYYYYMMDDHHMMSS(Timestamp ts) throws Exception{
        if(ts==null){
            return null;
        }
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = dateformat.format(ts);
        return str;
    }
    /**
     * 获得时间的字符串
     * @param ts Timestamp
     * @param pattern String
     * @throws Exception
     * @return String
     */
    public static String getYYYYMMDDHHMMSS(Timestamp ts,String pattern) throws Exception{
        if(ts==null){
            return null;
        }
        DateFormat dateformat = new SimpleDateFormat(pattern);
        String str = dateformat.format(ts);
        return str;
    }
    /**
     * 根据时间串获得时间对象
     * @param time String
     * @param pattern String yyyy-MM-dd HH:mm:ss
     * @throws Exception
     * @return Timestamp
     */
    public static Timestamp getTimestampByYYYYMMDDHHMMSS(String time,String pattern) throws Exception{
        Timestamp rtn = null;
        DateFormat dateformat2 = new SimpleDateFormat(pattern);
        rtn = new Timestamp(dateformat2.parse(time.trim()).getTime());
        return rtn;
    }
    /**
     * date转化成Timestamp
     * @param date Date
     * @return Timestamp
     */
    public static Timestamp dateToTimestamp(Date date){
        return new Timestamp(date.getTime());
    }

    /**
     * 取下个月一号
     * @return Timestamp
     * @throws Exception
     */
    public static Timestamp getNextMonthFirstDay() throws Exception{
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);// 下个月
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 1号
        Timestamp timestamp1 = new Timestamp(calendar.getTimeInMillis());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp timestamp2 = new Timestamp(df.parse(timestamp1.toString()).getTime());
        return timestamp2;
    }

    /**
     * 获取当月一号
     * */
    public static Timestamp getCurMonthFirstDay() throws Exception{
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);//1号
        Timestamp timestamp1 = new Timestamp(calendar.getTimeInMillis());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp timestamp2 = new Timestamp(df.parse(timestamp1.toString()).getTime());
        return timestamp2;
    }

    public static Timestamp getDaysByStartDateAndEndDate(Connection conn,java.sql.Timestamp start,java.sql.Timestamp end) throws Exception{
        java.sql.Timestamp endTime = null;
        java.sql.Statement stmt = null;
        java.sql.ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String start_str = "to_date('"+dateformat.format(start)+"','yyyy-mm-dd hh24miss')";
            String end_str = "to_date('"+dateformat.format(end)+"','yyyy-mm-dd hh24miss')";
            rs = stmt.executeQuery("select "+end_str+" + "+start_str+" as da from dual");
            if (rs.next()) {
                endTime = rs.getTimestamp("da");
            }
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return endTime;
    }

    /**
     * 取得指定日期后afterDays的日期
     *
     * @param date
     * @param afterDays
     * @return
     * @throws ParseException
     */
    public static Date getNextDate(Date date, int afterDays) throws ParseException{
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,afterDays);
        return c.getTime();
    }

    /**
     * 获得系统当前时间
     * */
    public static Date getCurrentDate() throws Exception{
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    /**
     * 返回当前时间的指定格式字符串
     * */

    public static String getCurrentDateFormat(String format) throws Exception{
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = getCurrentDate();
        return dateFormat.format(date);
    }

    /**
     * 获取制定格式的数据库时间串
     * @param format
     * @return
     * @throws Exception
     */
    public static String getCurrentDateFromDB(String format) throws Exception{
        ICommonDAO dao = SpringContextHolder.getBean(ICommonDAO.class);
        Date returnDate = dao.getSysDate("a" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 25));
        String sysDate = "";
        if(returnDate!=null){
            DateFormat dateFormat = new SimpleDateFormat(format);
            sysDate = dateFormat.format(returnDate);
        }
        return sysDate;
    }

    public static Timestamp getCurrentDateFromDB() throws Exception{
        Date returnDate = DataQueryUtil.getSysDate();
        if(returnDate!=null){
            return dateToTimestamp(returnDate);
        }
        return dateToTimestamp(getCurrentDate());
    }

    public static void main(String[] args) throws Exception{
        System.out.println(TimeUtil.getTimestampByYYYYMMDDHHMMSS("20041212","yyyyMMdd"));
    }

}

