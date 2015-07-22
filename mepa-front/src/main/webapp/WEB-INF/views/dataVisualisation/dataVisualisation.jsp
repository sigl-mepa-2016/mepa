<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<script type="text/javascript" src="<spring:url value="/js/colpick.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/data_visualisation.js"/>"></script>

<div class="container">
    <h2>Data Visualisation</h2>

    <br/>
    <table>
        <tbody>
        <tr>
            <td>
                <strong>Horizontal Axe :</strong>
                <select id="horizontal-axe">
                    <option></option>
                </select>
            </td>
            <td>
                <strong>Graph Type :</strong>
                <select id="graph-type">
                    <option value="LineChart">LineChart</option>
                    <option value="ColumnChart">ColumnChart</option>
                </select>
            </td>
        </tr>
        </tbody>
    </table>
    <table>
        <tbody id="table">
        <tr id="line1">
            <td>
                <strong>Vertical Axe 1 :</strong>
                <select id="vertical-axe1">
                    <option></option>
                </select>
            </td>
            <td>
                <div class="color-box1" style="width:30px; height:30px; margin:5px; border: 1px solid white;"></div>
            </td>
            <td>
                <button type="button" id="add-chart">Add Chart</button>
            </td>
        </tr>
        <tr id="line2">
            <td>
                <strong>Vertical Axe 2 :</strong>
                <select id="vertical-axe2">
                    <option></option>
                </select>
            </td>
            <td>
                <div class="color-box2" style="width:30px; height:30px; margin:5px; border: 1px solid white;"></div>
            </td>
            <td>
                <button type="button" id="delete2">Delete</button>
            </td>
        </tr>
        </tbody>
    </table>
    <br/><br/>
    <div  id="visualization_div" width="400" height="400"></div>
    <br/><br/>
</div>