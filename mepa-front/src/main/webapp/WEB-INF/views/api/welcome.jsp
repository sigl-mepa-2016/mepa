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

            <!-- Add Data to specific DataSet -->

            <!-- Get Token -->

            <!-- Check Token -->

            <!-- add User -->

            <!-- Remove User -->

        </div>
    </div>
</div>
</body>
</div>