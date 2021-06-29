package com.vjti.common;


import com.vjti.constant.ApplicationConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DateUtil {


    public static final String DATE_FORMAT_MYSQL = "yyyy-MM-dd";
    public static final String DATE_FORMAT_EMAIL_DISPLAY = "EEE, MMMM dd";
    public static final String DATE_FORMAT_CALENDAR_EMAIL_DISPLAY = "EEE, MMMM dd, yyyy";
    public static final String DATE_FORMAT_CALENDAR_INVITE_CANDIDATE = "EEEEE, dd MMMM, yyyy";
    public static final String DATE_FORMAT_RESCHEDULE = "EEE, MMMM dd, yyyy, hh:mm a";
    public static final String DATE_FORMAT_DISPLAY = "yyyy-MMM-dd";
    public static final String DATE_FORMAT_SLASH_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy";
    public static final String DATE_FORMAT_MON_YYYY = "(MMM-yyyy)";
    public static final String DATE_FORMAT_DD_MMM_YYYY = "dd-MMM-yyyy";
    public static final String DATE_FORMAT_DD_MMM_YY = "dd-MMM-yy";
    public static final String DATE_FORMAT_DD_MON_YYYY = "(dd-MMM-yyyy)";
    public static final String DATE_FORMAT_DD_MON_YYYY_WITHOUT_BRACKETS = "dd-MMM-yyyy";
    public static final String DATE_FORMAT_DD_SPACE_MON_SPACE_YYYY = "dd MMM yyyy";
    public static final String DATE_FORMAT_DD_MON_YYYY_HH_MM_A = "dd-MMM-yyyy, hh:mm a";
    public static final String DATE_FORMAT_EEEEE_DD_MMM_YYYY = "EEEEE, dd MMM yyyy";
    public static final String TIME_FORMAT_HH_MM_SS = "HH:mm a";
    public static final String TIME_FORMAT_HH_MM_A = "hh:mm a";
    public static final String TIME_FORMAT_H_MM_A = "h:mm a";
    public static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";
    public static final String DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_A = "MM/dd/yyyy hh:mm:ss a";
    public static final String DATE_FORMAT_UI = "yyyy-MMM-dd HH:mm:ss";
    public static final String DATE_FORMAT_DEFAULT = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_APLHA = "dd-MMM-yyyy hh:mm";
    public static final String DATE_FORMAT_MON_SPACE_YEAR = "MMM YY";
    public static final String DATE_FORMAT_YYYY = "YYYY";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FOMAT_DD_MMM = "dd MMM";
    public static final String DATE_FORMAT_MM_YYYY = "MM-yyyy";
    public static final String DAY = "E";
    public static final String DATE_FORMAT_DD_MM_YYYY_HH_MM_A = "dd-MM-yyyy hh:mm a";
    public static final String DATE_FORMAT_DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy hh:mm:ss";
    public static final String DATE_FORMAT_DD_MMM_YYYY_HH_MM_SS_A_Z = "dd-MMM-yyyy hh:mm:ss a Z";
    public static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SSZ = "yyyy-MM-dd'T'HH:mm:ssz";
    public static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS_Z_API = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_FORMAT_yyyy_MM_dd_T_HH_mm_ssXXX = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String DATE_FORMAT_yyyy_MM_dd_T_HH_mm_ss_SSS_X = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    public static final String DATE_FORMAT_YYYY_MM_dd_T_HH_mm_ss_SSS_XXX="yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DEFAULT_TIMEZONE_UTC = "UTC";
    public static final String DEFAULT_TIMEZONE_OFFSET = "+00:00";
    public static final String DATE_FORMAT_EXCEL="EEE MMM d HH:mm:ss Z yyyy";
    public static final String DATE_FORMAT_FILTER = "EEE MMM dd HH:mm:ss zzz yyyy";
    public static final String DATE_FORMAT_DD_MMM_YYYY_HHMMSS = "dd-MMM-yyyy-HH:mm:ss";
    public static final String DATE_FORMAT_ALL_UNDERSCORE =  "dd_MMM_yyyy_hh_mm_ss_a";
    public static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_FORMAT_MMMM_DD_YYYY = "MMMM dd, yyyy";
    public static final String DATE_FORMAT_MMMM_DD_YYYY_V2 = "MMMM dd, yyyy";
    public static final String DATE_FORMAT_SLASH_YYYY_MM_DD = "yyyy/MM/dd";
    public static final String DATE_FORMAT_WEBEX = "MM/dd/yyyy HH:mm:ss";

    private static Logger logger = LogManager.getLogger("DateUtil");

    public static Date getCurrentDate() {

        Date date = new Date();
        /*int rawOffset = TimeZone.getTimeZone("IST").getRawOffset();
        Date dateIST = new Date(date.getTime() + rawOffset);

		return dateIST;*/
        return date;
    }

    public static Date trim(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        return calendar.getTime();
    }


    public static String convertUtilDateToString(Date utilDate, String format) {

        logger.debug("DateUtil: convertUtilDateToString utilDate=" + utilDate + " format=" + format);

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (utilDate == null) {
            return "";
        } else {
            return sdf.format(utilDate);
        }
    }
    public static String convertUtilDateToString(Date utilDate, String format, Locale locale) {

        logger.debug("DateUtil: convertUtilDateToString utilDate=" + utilDate + " format=" + format);

        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        if (utilDate == null) {
            return "";
        } else {
            return sdf.format(utilDate);
        }
    }

    public static long convertTimeStampToSeconds(long timeInMills) {
        long timestampSeconds = TimeUnit.MILLISECONDS.toSeconds(timeInMills);
        return timestampSeconds;
    }

    public static Date convertStringToDate(String utilDate, String format) {

        logger.debug("DateUtil: convertUtilDateToString utilDate=" + utilDate + " format=" + format);

        Date newDate = null;
        if (null != utilDate && null != format) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            try {
                newDate = sdf.parse(utilDate);
            } catch (ParseException pe) {
                logger.error("DateUtil: convertStringToDate" + CommonUtil.getStackTrace(pe));
                /*pe.printStackTrace();*/
                return null;
            }

        }
        return newDate;
    }


    public static Integer compareDates(Date pDate1, Date pDate2) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date1 = sdf.parse(sdf.format(pDate1));
            Date date2 = sdf.parse(sdf.format(pDate2));

            logger.debug("DateUtil: compareDates date1=" + date1 + " date2=" + date2);
            return date1.compareTo(date2);

        } catch (ParseException pe) {
            logger.error("DateUtil: compareDates" + CommonUtil.getStackTrace(pe));
            /*pe.printStackTrace();*/
            return null;
        }
    }

    public static Integer compareDatesWithTimestamp(Date pDate1, Date pDate2) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_UI);
        try {
            Date date1 = sdf.parse(sdf.format(pDate1));
            Date date2 = sdf.parse(sdf.format(pDate2));

            logger.debug("DateUtil: compareDatesWithTimestamp date1=" + date1 + " date2=" + date2);
            return date1.compareTo(date2);

        } catch (ParseException pe) {
            logger.error("DateUtil: compareDatesWithTimestamp" + CommonUtil.getStackTrace(pe));
            /*pe.printStackTrace();*/
            return null;
        }
    }

    public static String getDateForUI(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATE_FORMAT_UI);
        if(date != null){
            return formatter.format(date);
        }else {
            return null;
        }

    }

    public static String beautifyDateRange(String startDateStr, String endDateStr) {

        String dateRange = startDateStr + " - " + endDateStr;
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = formatter.parse(startDateStr);
            Calendar start = Calendar.getInstance();
            start.getInstance().setTime(startDate);

            Date endDate = formatter.parse(endDateStr);
            Calendar end = Calendar.getInstance();
            start.getInstance().setTime(endDate);


            if (end.get(Calendar.YEAR) > start.get(Calendar.YEAR)) {
                dateRange = new SimpleDateFormat("dd MM yyyy").format(startDate) + " - " + new SimpleDateFormat("dd MM yyyy").format(endDate);
            } else if (end.get(Calendar.MONTH) > start.get(Calendar.MONTH)) {
                dateRange = new SimpleDateFormat("dd MMM ").format(startDate) + " - " + new SimpleDateFormat("dd MMM yyyy").format(endDate);
            } else {
                dateRange = new SimpleDateFormat("dd").format(startDate) + " - " + new SimpleDateFormat("dd MMM yyyy").format(endDate);
            }

        } catch (Exception e) {
            ;
            logger.debug(e);

        }
        return dateRange;
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException e) {
            logger.debug(e);
        }
        return xmlCalendar;
    }

    public static Date validateDate(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            Date d = simpleDateFormat.parse(date);
            return d;
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getBeforeOrAfterDate(Date date, int interval) {/*Add positive or negative to interval to get after or before date*/
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, interval);

        return calendar.getTime();
    }

    public static Date getBeforeOrAfterDateByMonths(Date date, int interval) {/*Add positive or negative to interval in months to get after or before date*/
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, interval);

        return calendar.getTime();
    }

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }


    public static Long getNoOfDaysDiff(Date pDate1, Date pDate2) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DISPLAY);

        try {
            Date date1 = sdf.parse(sdf.format(pDate1));
            Date date2 = sdf.parse(sdf.format(pDate2));
            long diff = date1.getTime() - date2.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        } catch (ParseException pe) {
            logger.error("DateUtil: getNoOfDaysDiff" + CommonUtil.getStackTrace(pe));
            /*pe.printStackTrace();*/
            return null;
        }
    }

    public static String formatDate(String date, String currFormat, String expectedFormat) {
        String formattedDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat(currFormat);
        sdf.setLenient(false);
        try {
            Date currentdate = sdf.parse(date);
            SimpleDateFormat expected = new SimpleDateFormat(expectedFormat);
            formattedDate = expected.format(currentdate);
            return formattedDate;
        } catch (ParseException e) {
            logger.error("DateUtil: formatDate " + date+" "+currFormat);
            /*e.printStackTrace();*/

        }
        return formattedDate;
    }

    public static String dateForDisplay(String date, String currFormat, String expectedFormat) {
        String beautifyDate = new String();
        String time = null;
        String hours = null;
        String mins = null;
        if (!StringUtil.isNullOrBlank(date)) {
            String formattedDate = formatDate(date, currFormat, expectedFormat);
            if (!StringUtil.isNullOrBlank(formattedDate)) {
                beautifyDate = formattedDate.split(" ")[0];
                if (formattedDate.split(" ").length > 1) {
                    time = formattedDate.split(" ")[1];
                    if (!StringUtil.isNullOrBlank(time)) {
                        beautifyDate = beautifyDate + ApplicationConstants.STR_COMMA_AND_SPACE;
                        hours = time.split(":")[0];
                        mins = time.split(":")[1];
                        beautifyDate = beautifyDate + hours + ApplicationConstants.STR_COLON + mins;
                    }
                }
            }
        }
        return beautifyDate;
    }

    public static long getDateFromDayMonthYear(String day, String month, String year) {
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(year),Integer.parseInt(month)-1, Integer.parseInt(day), 0, 0);
        Date d = c.getTime();
        Timestamp t = new Timestamp(d.getTime());
        return TimeUnit.MILLISECONDS.toSeconds(t.getTime());
    }

    public static String getDateTimeForApi(Date date, String format){

        SimpleDateFormat df = new SimpleDateFormat(format);
        TimeZone tz = TimeZone.getTimeZone(DEFAULT_TIMEZONE_UTC);
        df.setTimeZone(tz);
        String output = df.format( date);
        output = output.replaceAll(DEFAULT_TIMEZONE_UTC, DEFAULT_TIMEZONE_OFFSET);

        return output;
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }



    public static boolean isThisDateWithinIntervalRange(String dateToValidate,
                                                String dateFromat, Integer intervalRange) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);
        try {
            // if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);

            Calendar currentDateBeforeInterval = Calendar.getInstance();
            currentDateBeforeInterval.add(Calendar.DAY_OF_YEAR, -intervalRange);
            return date.after(currentDateBeforeInterval.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean isGreaterThanCurrentDate(Date dateToCompare) {
        int value = compareDates(DateUtil.getCurrentDate(), dateToCompare);
        return (value < 0 ? true: (value == 0 ? true : false));
    }

    public static XMLGregorianCalendar getGregorianDate(String year, String month, String day) throws ApplicationException {
        try{
            XMLGregorianCalendar dt = DatatypeFactory.newInstance().newXMLGregorianCalendar();
            if(year!=null && month!=null && day!=null){
                dt.setYear(Integer.parseInt(year));
                dt.setMonth(Integer.parseInt(month));
                dt.setDay(Integer.parseInt(day));
                dt.setHour(0);
                dt.setMinute(0);
                dt.setSecond(0);
                return dt;
            }else{
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTime(new Date());
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new ApplicationException("Incorrect Date");
        }
    }

    public static int getYearDifference(Date inputDate){
        Calendar current = Calendar.getInstance();
        current.setTime(getCurrentDate());
        Calendar input = Calendar.getInstance();
        input.setTime(inputDate);
        return current.get(Calendar.YEAR) - input.get(Calendar.YEAR);
    }

    public static String getDateByUtcOffsetForUI(Date date, Integer utcOffet, String format){
        int rawOffset = utcOffet * ApplicationConstants.CONVERT_TO_TIME;
        return convertUtilDateToString(new Date(date.getTime() - rawOffset), format);
    }

    public static String convertDateForBulkUpload(String dateValue) {
        Date s = null;
        String convertedDateValue = "";
        try{

            if(dateValue.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})") || dateValue.matches("([0-9]{2})-([0-9]{1})-([0-9]{4})")){
                s = DateUtil.convertStringToDate(dateValue,DateUtil.DATE_FORMAT_DD_MM_YYYY);
                convertedDateValue = DateUtil.formatDate(DateUtil.convertUtilDateToString(s,DateUtil.DATE_FORMAT_DD_MM_YYYY),DateUtil.DATE_FORMAT_DD_MM_YYYY,DateUtil.DATE_FORMAT_DD_MON_YYYY_WITHOUT_BRACKETS);
            }else{

                s = DateUtil.convertStringToDate(dateValue,DateUtil.DATE_FORMAT_MYSQL);
                convertedDateValue = DateUtil.formatDate(DateUtil.convertUtilDateToString(s,DateUtil.DATE_FORMAT_MYSQL),DateUtil.DATE_FORMAT_MYSQL,DateUtil.DATE_FORMAT_DD_MON_YYYY_WITHOUT_BRACKETS);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return StringUtil.isNullOrBlank(convertedDateValue) ? dateValue : convertedDateValue;
    }

    public static Date addTimeUnitToDate(Date date, int timeUnitValue,String timeUnitDesc) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch(timeUnitDesc){
            case ApplicationConstants.QUARTZ_UNIT_HOUR:
                calendar.add(Calendar.HOUR_OF_DAY, timeUnitValue);
                break;
            case ApplicationConstants.QUARTZ_UNIT_MIN:
                calendar.add(Calendar.MINUTE, timeUnitValue);
                break;
            case ApplicationConstants.QUARTZ_UNIT_SEC:
                calendar.add(Calendar.SECOND, timeUnitValue);
                break;

        }
        return calendar.getTime();
    }

    public static Date convertDateForBulkUploadSave(String dateValue){
        Date s = null;
        String convertedDateValue = "";
        try{

            if(dateValue.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})") || dateValue.matches("([0-9]{2})-([0-9]{1})-([0-9]{4})")){
                s = DateUtil.convertStringToDate(dateValue,DateUtil.DATE_FORMAT_DD_MM_YYYY);
                convertedDateValue = DateUtil.formatDate(DateUtil.convertUtilDateToString(s,DateUtil.DATE_FORMAT_DD_MM_YYYY),DateUtil.DATE_FORMAT_DD_MM_YYYY,DateUtil.DATE_FORMAT_DD_MON_YYYY_WITHOUT_BRACKETS);
            }else{
                s = DateUtil.convertStringToDate(dateValue,DateUtil.DATE_FORMAT_MYSQL);
                convertedDateValue = DateUtil.formatDate(DateUtil.convertUtilDateToString(s,DateUtil.DATE_FORMAT_MYSQL),DateUtil.DATE_FORMAT_MYSQL,DateUtil.DATE_FORMAT_DD_MON_YYYY_WITHOUT_BRACKETS);
            }
            s = DateUtil.convertStringToDate(convertedDateValue,DateUtil.DATE_FORMAT_DD_MON_YYYY_WITHOUT_BRACKETS);
        }catch(Exception e){
            e.printStackTrace();
        }
        return s;

    }




    public static void main(String [] args){

        try{
            Calendar c = DateUtil.getCalendar(new Date());
            String currentdate=new SimpleDateFormat("dd-MMM").format(c.getTime());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static String changingStringDateObjectFormat(String utilDate, String format) {

        logger.debug("DateUtil: convertUtilDateToString utilDate=" + utilDate + " format=" + format);

        String newDate = null;
        Date tempDate = null;
        if (null != utilDate && null != format) {
        	SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_DD_MON_YYYY_HH_MM_A);
            sdf.setLenient(false);
            try {
            	tempDate = sdf.parse(utilDate);
            	sdf = new SimpleDateFormat(format);
            	newDate = sdf.format(tempDate);
            } catch (ParseException pe) {
                logger.error("DateUtil: convertStringToDate" + CommonUtil.getStackTrace(pe));
                return null;
            }
        }
        return newDate;
    }

}
