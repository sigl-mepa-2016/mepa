<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
<body>
<div class="container">
    <div class="page-header">
        <h1 class="white">API documentation</h1>
    </div>
    <div id="map-canvas" class="canvas">

        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

            <!-- get List of DataSet -->
            <div class="panel panel-default">
              <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                <div class="panel-heading" role="tab" id="headingOne">
                  <h4 class="panel-title">
                      Get list of DataSet
                  </h4>
                </div>
                </a>

                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                  <div class="panel-body">
                    <%@ include file="DataSetList.jsp"%>
                  </div>
                </div>
              </div>

            <!-- Add DataSet -->
            <div class="panel panel-default">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            Add DataSet
                        </h4>
                    </div>
                </a>

                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        <%@ include file="AddDataSet.jsp"%>
                    </div>
                </div>
            </div>

            <!-- Update DataSet -->
            <div class="panel panel-default">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            Update DataSet
                        </h4>
                    </div>
                </a>

                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        <%@ include file="UpdateDataSet.jsp"%>
                    </div>
                </div>
            </div>

            <!-- Delete DataSet -->
            <div class="panel panel-default">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            Delete DataSet
                        </h4>
                    </div>
                </a>

                <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                    <div class="panel-body">
                        <%@ include file="DeleteDataSet.jsp"%>
                    </div>
                </div>
            </div>

            <!-- Get schema of specific DataSet -->
            <div class="panel panel-default">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                    <div class="panel-heading" role="tab" id="headingFive">
                        <h4 class="panel-title">
                            Get schema of specific DataSet
                        </h4>
                    </div>
                </a>

                <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                    <div class="panel-body">
                        <%@ include file="DataSetSchema.jsp"%>
                    </div>
                </div>
            </div>

            <!-- Get schema of specific DataSet with Data -->
            <div class="panel panel-default">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                    <div class="panel-heading" role="tab" id="headingSix">
                        <h4 class="panel-title">
                            Get schema of specific DataSet with Data
                        </h4>
                    </div>
                </a>

                <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                    <div class="panel-body">
                        <%@ include file="DataSetSchemaWithData.jsp"%>
                    </div>
                </div>
            </div>

            <!-- Get data from specific DataSet -->
            <div class="panel panel-default">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
                    <div class="panel-heading" role="tab" id="headingSeven">
                        <h4 class="panel-title">
                            Get data from specific DataSet
                        </h4>
                    </div>
                </a>

                <div id="collapseSeven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSeven">
                    <div class="panel-body">
                        <%@ include file="DataFromSpecificDataSet.jsp"%>
                    </div>
                </div>
            </div>

            <!-- Add Data to specific DataSet -->
            <div class="panel panel-default">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseEight" aria-expanded="false" aria-controls="collapseEight">
                    <div class="panel-heading" role="tab" id="headingEight">
                        <h4 class="panel-title">
                            Add Data to specific DataSet
                        </h4>
                    </div>
                </a>

                <div id="collapseEight" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingEight">
                    <div class="panel-body">
                        <%@ include file="AddDataToSpecificDataSet.jsp"%>
                    </div>
                </div>
            </div>

            <!-- Get Token -->
            <div class="panel panel-default">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseNine" aria-expanded="false" aria-controls="collapseNine">
                    <div class="panel-heading" role="tab" id="headingNine">
                        <h4 class="panel-title">
                            Get token
                        </h4>
                    </div>
                </a>

                <div id="collapseNine" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingNine">
                    <div class="panel-body">
                        <%@ include file="getToken.jsp"%>
                    </div>
                </div>
            </div>

            <!-- Check Token -->
            <div class="panel panel-default">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseEleven" aria-expanded="false" aria-controls="collapseEleven">
                    <div class="panel-heading" role="tab" id="headingEleven">
                        <h4 class="panel-title">
                            Check token
                        </h4>
                    </div>
                </a>

                <div id="collapseEleven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingEleven">
                    <div class="panel-body">
                        <%@ include file="CheckToken.jsp"%>
                    </div>
                </div>
            </div>

            <!-- add User -->
            <div class="panel panel-default">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTen" aria-expanded="false" aria-controls="collapseTen">
                    <div class="panel-heading" role="tab" id="headingTen">
                        <h4 class="panel-title">
                            add User
                        </h4>
                    </div>
                </a>

                <div id="collapseTen" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTen">
                    <div class="panel-body">
                        <%@ include file="adduser.jsp"%>
                    </div>
                </div>
            </div>

            <!-- Remove User -->
            <div class="panel panel-default">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwelve" aria-expanded="false" aria-controls="collapseTwelve">
                    <div class="panel-heading" role="tab" id="headingTwelve">
                        <h4 class="panel-title">
                            Remove User
                        </h4>
                    </div>
                </a>

                <div id="collapseTwelve" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwelve">
                    <div class="panel-body">
                        <%@ include file="RemoveUser.jsp"%>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</div>