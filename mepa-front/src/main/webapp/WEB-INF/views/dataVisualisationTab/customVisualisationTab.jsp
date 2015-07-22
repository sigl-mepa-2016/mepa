<%--
  Created by IntelliJ IDEA.
  User: flo
  Date: 22/07/15
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <h2 class="white">Custom table visualisation </h2>
    <br/><br/>
    <div class="panel panel-default">
        <div class="panel-heading">
            Custom your table
        </div>
        <div class="panel-body">

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
                                    <input type="checkbox" name="my-checkbox" checked>
                                </td>
                            </c:forEach>
                        </tr>
                        </thead>
                    </table>
                </div>

            <b></b>
            <a role="button" class="btn btn-primary">Submit changes</a>
        </div>
    </div>

</div>