package ua.deliciousrestaurant.utils;


import javax.servlet.http.HttpServletRequest;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public final class PaginationUtil {

    public static void paginate(HttpServletRequest request, int totalRecords) {
        int offset = getInteger(request.getParameter(OFFSET), 0, 0);
        int records = getInteger(request.getParameter(RECORDS), 1, 5);
        setAttributes(request, totalRecords, records, offset);
    }

    private static void setAttributes(HttpServletRequest request, int totalRecords, int records, int offset) {
        int pages = totalRecords / records + (totalRecords % records != 0 ? 1 : 0);
        int currentPage = offset / records + 1;
        int startPage = currentPage == pages ? Math.max(currentPage - 2, 1)
                : Math.max(currentPage - 1, 1);
        int endPage = Math.min(startPage + 2, pages);
        request.setAttribute(OFFSET, offset);
        request.setAttribute(RECORDS, records);
        request.setAttribute(TOTAL_PAGES, pages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(START_PAGE, startPage);
        request.setAttribute(END_PAGE, endPage);
    }

    private static int getInteger(String value, int min, int defaultValue) {
        try {
            int records = Integer.parseInt(value);
            if (records >= min) {
                return records;
            }
        } catch (NumberFormatException e) {
            return defaultValue;
        }
        return defaultValue;
    }

    private PaginationUtil() {}
}
