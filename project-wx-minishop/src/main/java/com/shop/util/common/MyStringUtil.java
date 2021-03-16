package com.shop.util.common;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用工具类
 *
 * @author Administrator
 */
public class MyStringUtil {
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    private static String PDF_FIC_NAME = "name=";
    private final static String[] agent = {"Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser"}; //定义移动端请求的所有可能类型

    public static void main(String[] args) throws UnsupportedEncodingException {
        // String image="http://www.xxxxx/.jsp?name=xxxbbbb_1.gif";
        // String s =MyStringUtil.getEndfix(image);
        // System.out.println(s);
//		String str = "200";
//		System.out.println(MyStringUtil.isPositiveInteger(str));
//		System.out.println(getEndfix("啊发放.doc"));
        System.out.println(EncodeString("/"));
//        String aa = "输入有误，您还有1次机会";
//        System.out.println(HasDigit(aa));
//        System.out.println(getNumbers(aa));
    }

    /**
     * 判断User-Agent 是不是来自于手机
     *
     * @param ua
     * @return
     */
    public static boolean checkAgentIsMobile(String ua) {
        boolean flag = false;
        if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
            // 排除 苹果桌面系统
            if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
                for (String item : agent) {
                    if (ua.contains(item)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 获取pdf接口返回的图片名称
     *
     * @return
     */
    public static String gitpicname(String url) {
        int start = url.lastIndexOf(PDF_FIC_NAME);
        String out = url.substring(start + PDF_FIC_NAME.length(), url.length());
        return out;
    }

    /**
     * 获取文件后缀
     *
     * @param filepath
     * @return
     */
    public static String getEndfix(String filepath) {
        String end = "";
        int ps = filepath.lastIndexOf(".");
        end = filepath.substring(ps, filepath.length());
        return end;
    }

    /**
     * 获取文件前缀
     *
     * @param filepath
     * @return
     */
    public static String getPrefix(String filepath) {
        String end = "";
        int ps = filepath.lastIndexOf(".");
        end = filepath.substring(0, ps);
        return end;
    }

    /**
     * @param value
     * @param length
     * @return
     */
    /**
     * <b>概要：</b> 检查邮件是否合法 <b>作者：</b>suxh </br> <b>日期：</b>2015-1-12 </br>
     *
     * @param value  邮件地址
     * @param length 长度
     * @return true 邮箱格式合法 false 邮箱格式不合法
     */
    public static boolean checkEmail(String value, int length) {
        return value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*") && value.length() <= length;
    }

    /**
     * 检查电话输入 是否正确 正确格 式 012-87654321、0123-87654321、0123－7654321
     *
     * @param value
     * @return
     */
    public static boolean checkTel(String value) {
        return value.matches("\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d(3)-\\d(8)");
    }

    /**
     * 检查手机输入 是否正确
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean checkMobile(String mobile) {
        return mobile.matches("^[1][3,5,7,0,8,4,9,6]+\\d{9}");
    }


    /**
     * 检查字符串是 否含有HTML标签
     *
     * @param value
     * @return
     */

    public static boolean checkHtmlTag(String value) {
        return value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");
    }

    /**
     * 检查URL是 否合法
     *
     * @param value
     * @return
     */
    public static boolean checkURL(String value) {
        return value.matches("[a-zA-z]+://[^\\s]*");
    }

    /**
     * 检查IP是否 合法
     *
     * @param value
     * @return
     */
    public static boolean checkIP(String value) {
        return value.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");
    }


    /**
     * 检查QQ是否 合法，必须是数字，且首位不能为0，最长15位
     *
     * @param value
     * @return
     */

    public static boolean checkQQ(String value) {
        return value.matches("[1-9][0-9]{4,13}");
    }

    /**
     * 检查邮编是否 合法
     *
     * @param value
     * @return
     */
    public static boolean checkPostCode(String value) {
        return value.matches("[1-9]\\d{5}(?!\\d)");
    }

    /**
     * 检查身份证是 否合法,15位或18位
     *
     * @param value
     * @return
     */
    public static boolean checkIDCard(String value) {
        return value.matches("\\d{15}(\\d{2}[0-9X])?$");
    }

    /**
     * 检查输入是否 超出规定长度 Java教程:http://www.javaweb.cc
     *
     * @param length
     * @param value
     * @return
     */
    public static boolean checkLength(String value, int length) {
        return ((value == null || "".equals(value.trim())) ? 0 : value.length()) <= length;
    }

    /**
     * 检查是否为空 字符串,空：true,不空:false
     *
     * @param value
     * @return
     */
    public static boolean checkNull(String value) {
        return value == null || "".equals(value.trim());
    }

    /**
     * 数组转换成字符串
     *
     * @param array
     * @param split
     * @return
     */
    public static String arraytostring(Object[] array, String split) {
        StringBuffer sb = new StringBuffer();
        if (null == array)
            return "";
        for (Object obj : array) {
            sb.append(obj);
            sb.append(split);
        }
        return sb.toString();
    }

    /**
     * 返回区域
     *
     * @param province
     * @param city
     * @param areaId
     * @return
     */
    public static String getAreaId(String province, String city, String areaId) {
        if (StringUtils.isNotBlank(areaId))
            return areaId.trim();
        else if (StringUtils.isNotBlank(city))
            return city.trim();
        else if (StringUtils.isNotBlank(province))
            return province.trim();
        return "";
    }

    /**
     * 字符串截长
     *
     * @param s
     * @param length
     * @return
     * @throws Exception
     */
    public static String bSubstring(String s, int length) {
        if (null == s || "".equals(s))
            return "";
        String ss = "";
        byte[] bytes;
        int i;
        try {
            bytes = s.getBytes("Unicode");
            int n = 0; // 表示当前的字节数
            i = 2;
            for (; i < bytes.length && n < length; i++) {
                // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
                if (i % 2 == 1) {
                    n++; // 在UCS2第二个字节时n加1
                } else {
                    // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                    if (bytes[i] != 0) {
                        n++;
                    }
                }
            }
            // 如果i为奇数时，处理成偶数
            if (i % 2 == 1) {
                // 该UCS2字符是汉字时，去掉这个截一半的汉字
                if (bytes[i - 1] != 0)
                    i = i - 1;
                    // 该UCS2字符是字母或数字，则保留该字符
                else
                    i = i + 1;
            }
            ss = new String(bytes, 0, i, "Unicode");
            if (bytes.length > length)
                ss += "...";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ss;
    }

    /**
     * <b>概要：</b> 分割字符串并保留分割字符 <b>作者：</b>SUXH </br> <b>日期：</b>2015-3-27 </br>
     *
     * @param regEx 正则表达式：分隔符 如：：|。|！|；
     * @return
     * @str 需要分割的字符 如：第一句。第二句！第三句：第四句；第五句。
     */
    public static String[] splitStrIncludeSplitChar(String str, String regEx) {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String[] words = p.split(str);

        /* 将句子结束符连接到相应的句子后 */
        if (words.length > 0) {
            int count = 0;
            while (count < words.length) {
                if (m.find()) {
                    words[count] += m.group();
                }
                count++;
            }
        }
        return words;
    }

    /**
     * <b>概要：</b> 判断字符串是否为正整数 <b>作者：</b>SUXH </br> <b>日期：</b>2015-3-31 </br>
     *
     * @param str 字符串
     * @return true:为数字 false:不为数字
     */
    public static boolean isPositiveInteger(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * <b>概要：</b> 判断字符串是否为整数 <b>作者：</b>SUXH </br> <b>日期：</b>2015-3-31 </br>
     *
     * @param str 字符串
     * @return true:为数字 false:不为数字
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^-?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * <b>概要：</b> 判断字符串是否为数字 <b>作者：</b>SUXH </br> <b>日期：</b>2015-3-31 </br>
     *
     * @param str 字符串
     * @return true:为数字 false:不为数字
     */
    public static boolean isDigital(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * <b>概要：</b> 把字符串的内容分成N部分 如： str:suxiao1 n：3 则分割出为：sux,iao,1 <b>作者：</b>SUXH
     * </br> <b>日期：</b>2015-4-25 </br>
     *
     * @param str  指定字符串
     * @param part part部分
     * @return 分割出的字符串数组
     */
    public static String[] splitPartsOfStr(String str, int part) {
        int strLength = str.length();
        Integer partLength = strLength / part + 1;
        String[] result = new String[part];
        for (int i = 0; i < part; i++) {
            result[i] = str.substring(i * partLength, (i + 1) * partLength > strLength ? strLength : (i + 1) * partLength);
        }
        return result;
    }

    /**
     * <b>概要：</b> 获得简写的字符串： 如： str:深圳法大大网络科技有限公司 如果①length=14，则结果：深圳法大大网络科技有限公司
     * ②length=12，则结果：深圳法大大网络科技有限公.. <b>作者：</b>SUXH </br> <b>日期：</b>2015-4-27
     * </br>
     *
     * @param str    需要简写的字符串
     * @param length 长度
     * @return
     */
    public static String getAbbreviationStr(String str, int length) {
        return str.length() > length ? (str.subSequence(0, length - 1) + "..") : str;
    }

    /**
     * <b>概要：</b> anotherStrs数组中的是否存在一个元素在strs数组中不包含，
     * 如存在，则返回anotherStrs中第一个不包含的字符 不存在，返回null <b>作者：</b>SUXH </br>
     * <b>日期：</b>2015-4-27 </br>
     *
     * @param strs        字符串数组
     * @param anotherStrs 另一个字符串数组
     * @return 如： strs:{1,3,4} anotherStrs:{3,4},则返回null strs:{1,3,4}
     * anotherStrs:{2,4},则返回第一个不包含的字符串2
     */
    public static String findAnotherStrsElementNotContainInStrsElement(String[] strs, String[] anotherStrs) {
        List<String> strList = new ArrayList<String>();
        Collections.addAll(strList, strs);
        for (String str : anotherStrs) {
            if (!strList.contains(str)) {
                return str;
            }
        }
        return null;
    }

    /**
     * <b>概要：</b> anotherStrs数组中的是否存在一个元素在strs数组中包含， 如存在，则返回anotherStrs中第一个包含的字符
     * 不存在，返回null <b>作者：</b>SUXH </br> <b>日期：</b>2015-4-28 </br>
     *
     * @param strs        字符串数组
     * @param anotherStrs 另一个字符串数组
     * @return 如： strs:{2,3,4} anotherStrs:{1,2},则返回2 strs:{1,4}
     * anotherStrs:{2,3},则返回null
     */
    public static String findAnotherStrsElementContainInStrsElement(String[] strs, String[] anotherStrs) {
        List<String> strList = new ArrayList<String>();
        Collections.addAll(strList, strs);
        for (String str : anotherStrs) {
            if (strList.contains(str)) {
                return str;
            }
        }
        return null;
    }

    /**
     * <b>概要：</b> 从字符串数组中移除指定字符 <b>作者：</b>SUXH </br> <b>日期：</b>2015-4-27 </br>
     *
     * @param strs 字符串数组
     * @param str  字符
     * @return 去掉str的字符串数组
     */
    public static String[] removeStrFromStrArr(String[] strs, String str) {
        List<String> strList = new ArrayList<String>();
        Collections.addAll(strList, strs);
        strList.remove(str);
        String[] strArr = new String[strList.size()];
        return (String[]) strList.toArray(strArr);
    }

    /**
     * <b>概要：</b>
     * 遍历data的值，替换template字符串
     * <b>作者：</b>jiancf </br>
     * <b>日期：</b>2015-6-26 </br>
     *
     * @param template 模版
     * @param data     模版中占位符对应的key和value
     * @return 根据模版替换占位符后的内容
     * @throws Exception
     */
    public static String composeMessage(String template, Map<String, Object> data) {
        String regex = "\\$\\{(.+?)\\}";//字符串字面值中使用两个反斜线 .+?匹配零个字符，一个字符，或是多个字符
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(template);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String name = matcher.group(1);// 键名
            String value = (String) data.get(name);// 键值
            if (value == null) {
                value = "";
            } else {
                value = value.replaceAll("\\$", "\\\\\\$");
            }
            matcher.appendReplacement(sb, value);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * <b>概要：</b>
     * 隐藏字符串
     * <b>作者：</b>zhangzh </br>
     * <b>日期：</b>2017-1-24 </br>
     *
     * @param srcStr   要隐藏的字符串
     * @param hideChar 隐藏替代的字符串
     * @param headLen  保留的字符串头的长度
     * @param endLen   保留的字符串尾的长度
     * @return
     */
    public static String hideStr(String srcStr, char hideChar, int headLen, int endLen) {
        if (srcStr != null) {
            int len = srcStr.length();
            if (len > headLen && len > endLen) {
                String replaceStr = srcStr.substring(headLen, len - endLen);
                StringBuilder sb = new StringBuilder(len - endLen - headLen);
                for (int i = headLen; i < len - endLen; i++) {
                    //srcStr = srcStr.replace(srcStr.charAt(i), hideChar);
                    sb.append(hideChar);
                }
                srcStr = srcStr.replaceFirst(replaceStr, sb.toString());
            }
        }
        return srcStr;
    }

    // 判断一个字符串是否都为数字
    public static boolean isDigit(String strNum) {
        return strNum.matches("[0-9]{1,}");
    }

    // 判断一个字符串是否都为数字
    public static boolean isAllDigit(String strNum) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher((CharSequence) strNum);
        return matcher.matches();
    }

    //截取数字
    public static Integer getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return Integer.parseInt(matcher.group(0));
        }
        return null;
    }

    // 截取非数字
    public static String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 判断一个字符串是否含有数字
    public static boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    public static String EncodeString(String url) throws UnsupportedEncodingException {
        // 对URL进行编码
        return URLEncoder.encode(url, "UTF-8")
                .replaceAll("\\+", "%20")
                .replaceAll("\\!", "%21")
                .replaceAll("\\'", "%27")
                .replaceAll("\\(", "%28")
                .replaceAll("\\)", "%29")
                .replaceAll("\\~", "%7E");
    }

}
