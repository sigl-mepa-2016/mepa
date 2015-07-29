<%--
  Created by IntelliJ IDEA.
  User: flo
  Date: 22/07/15
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="tab-pane" id="table-view">
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <c:forEach items="${fieldKeys}" var="column" varStatus="loop">
                    <th>${column}</th>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach items="${fieldKeys}" var="column" varStatus="loop2">
                    <td>
                        <input type="checkbox" id="${column}" onchange="updateTable('${column}');" checked>
                        <input type="button" id="${column}" onclick="updateTable2('${column}');">
                    </td>
                </c:forEach>
            </tr>
            </thead>
        </table>
    </div>
</div>