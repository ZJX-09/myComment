<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ tag language="java" import="com.travis.util.CommonUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute type="java.lang.String" name="url" required="true" %>
<%@ attribute type="java.lang.String" name="method" %>

<% if(CommonUtil.containsUrl(session, url, method)) { %>
<jsp:doBody/>
<% } %>
