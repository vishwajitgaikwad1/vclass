package com.vjti.common;

/**
 * Created by vishwajit_gaikwad on 15/5/21.
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vjti.constant.ApplicationConstants;
import org.apache.commons.lang.StringUtils;

import java.text.Normalizer;
import java.util.*;

public class StringUtil {

    public static Integer convertStringToInteger(String strObj) {

        Integer iObj = null;
        try {
            iObj = Integer.parseInt(strObj);
        } catch (NumberFormatException e) {
        } catch (NullPointerException e) {
        }
        return iObj;
    }

    public static boolean convertCharToBoolean(char charObj) {

        if(charObj == ApplicationConstants.CHAR_Y)
            return true;
        else
            return false;
    }

    public static String getIndexValueFromArray(String[] array, int index) {
        if (array!=null && index<array.length) {
            return array[index];
        } else {
            return null;
        }
    }

    public static boolean isNullOrBlank(String strObj) {
        if (strObj==null || ( null !=strObj &&  "".equals(strObj.trim()))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotNullOrBlank(String strObj){
        return !isNullOrBlank(strObj);
    }


    public static boolean isVaildMandatoryString(String strObj, int length) {

        if (isNullOrBlank(strObj)) {
            return false;
        }

        if (strObj.length() > length) {
            return false;
        }
        return true;
    }

    public static boolean isVaildOptionalString(String strObj, int length) {

        if (!isNullOrBlank(strObj)) {
            if (strObj.length() > length) {
                return false;
            }
        }
        return true;
    }

    public static boolean isVaildMandatoryStringList(List<String> strListObj, int length) {

        if (strListObj==null || strListObj.size()<1) {
            return false;
        }
        for(String strObj:strListObj){
            if (isNullOrBlank(strObj) || strObj.length() > length) {
                return false;
            }
        }

        return true;
    }

    public static boolean isVaildOptionalStringList(List<String> strListObj, int length) {
        if (strListObj != null && strListObj.size() > 0) {
            for(String strObj:strListObj){
                if (isNullOrBlank(strObj) || strObj.length() > length) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String normalizeDateString(String obj) {

        if(!isNullOrBlank(obj) && obj.length() > 1) {
            return obj.substring(1, obj.length()-1);
        }
        return null;
    }

    public static boolean isNumeric(String str) {
        if(str == null) {
            return false;
        } else {
            int sz = str.length();

            for(int i = 0; i < sz; ++i) {
                if(!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String convertStringListToStringWithSeparator(List<String> stringList, String separator) {
        String finalString = "";
        for (int i = 0; i < stringList.size(); i++) {
            if (i == 0) {
                finalString = stringList.get(i);
            } else {
                finalString = finalString + separator + stringList.get(i);
            }
        }
        return finalString;
    }

    public static String convertCommaSeperatedStringToSqlSearch(String commaSeperated,String delimiter){
        ArrayList<String> stringList = null;
        StringBuffer stringBuffer = new StringBuffer();
        switch(delimiter){
            case ",":
                stringList = new ArrayList<String>(Arrays.asList(commaSeperated.split(",")));
                break;
            case "|":
                stringList = new ArrayList<String>(Arrays.asList(commaSeperated.split("|")));
                break;
        }

        for(String s : stringList){
            stringBuffer.append("'" + s + "'").append(",");
        }
        String value  = stringBuffer.toString().replaceAll(", $", "");
        value = value.substring(0, value.length()-1);
        return value;

    }

    public static ArrayList<Integer> convertDelimitedStringToIntegerList(String commaSeperatedString, String delimiter){
        ArrayList<Integer> listOfValues = new ArrayList<>();
        ArrayList<String> stringList = null;
        switch(delimiter){
            case ",":
                stringList = new ArrayList<String>(Arrays.asList(commaSeperatedString.split(",")));
                break;
            case "|":
                stringList = new ArrayList<String>(Arrays.asList(commaSeperatedString.split("|")));
                break;
        }
        for(String s : stringList){
            listOfValues.add(Integer.parseInt(s));
        }
        return listOfValues;



    }

    public static String trim(String s)
    {
        if(!isNullOrBlank(s)){
            return s.trim();
        }else {
            return s;
        }

    }

    public static boolean checkIfStringEqualsIgnoresList(String strToCompare, List<String> listToCompare){
        boolean containsVal = false;
        for(String s : listToCompare){
            if(s.equalsIgnoreCase(strToCompare)){
                containsVal = true;
                break;
            }
        }
        return containsVal;
    }

    public static String removeBlanks(String s)
    {
        if(!isNullOrBlank(s)){
            s = s.replaceAll(" ","");
            return s.trim();
        }else {
            return s;
        }

    }

    public static String findAndReplaceOfferElementsWithValues(String offerTemplate, Map<String,String> mapOfParameterToFindAndValue){
        String finalString = "";
        Iterator<Map.Entry<String, String>> iterator = mapOfParameterToFindAndValue.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            finalString = StringUtils.replace(offerTemplate,entry.getKey(),entry.getValue());
            offerTemplate = finalString;
        }
        return finalString;
    }

    public static String findAndReplace(String textToFormat,String textToReplaceWith,String textToReplace){
        return textToFormat.replace(textToReplace,textToReplaceWith);

    }


    public static String returnBlankIfNull(String s){
        if(isNullOrBlank(s)){
            return ApplicationConstants.STR_EMPTY;
        }else{
            return s;
        }
    }

    public static String unaccent(String src) {
        return Normalizer
                .normalize(src, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

/*	public static boolean isNumeric (String strObj) {
		if (null != strObj && strObj.)
	}
*/

}


