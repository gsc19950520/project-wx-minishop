package com.shop.util.common;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
@Slf4j
public class SessionUtil {
	/**获取session中大小
	 * @param request
	 * @param str
	 * @return
	 */
	public static Object getSession(HttpServletRequest request, String str) {
		return request.getSession().getAttribute(str);
	}
	
	/**设置session值
	 * @param request
	 * @param str
	 * @param obj
	 */
	public static void setSession(HttpServletRequest request, String str, Object obj) {
		request.getSession().setAttribute(str, obj);
	}
	
	/**获取session中大小
	 * @param request
	 * @param str
	 * @return
	 */
	public static String getStringSession(HttpServletRequest request, String str) {
		return getSession(request, str)==null?null:getSession(request, str).toString();
	}
	
	public static Long getLongSession(HttpServletRequest request, String str) {
		return getSession(request, str)==null?null: Long.parseLong(getSession(request, str).toString());
	}
	
	public static void updateSession(HttpServletRequest request) {
		try {
			request.getSession().invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession(true);
	}
	
}
