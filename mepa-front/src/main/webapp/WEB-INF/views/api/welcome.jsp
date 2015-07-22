<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
<body>
<div class="container">
    <div class="page-header">
        <h1 class="white">API documentation</h1>
    </div>
    <div id="map-canvas" class="canvas">

    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

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

      <div class="panel panel-default">
      <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
        <div class="panel-heading" role="tab" id="headingTwo">
          <h4 class="panel-title">
              Get schema ofspecific DataSet
          </h4>
        </div>
        </a>

        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
          <div class="panel-body">
            <%@ include file="DataSetSchema.jsp"%>
          </div>
        </div>
      </div>
      <div class="panel panel-default">
      <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
        <div class="panel-heading" role="tab" id="headingThree">
          <h4 class="panel-title">
              Add DataSet
          </h4>
        </div>
      </a>

        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
          <div class="panel-body">

          </div>
        </div>
      </div>
    </div>
    </div>
</div>
</body>
</div>