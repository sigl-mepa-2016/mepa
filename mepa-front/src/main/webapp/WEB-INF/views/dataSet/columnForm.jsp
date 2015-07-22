<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <h2 class="white">Add fields to the selected dataset</h2>

    <c:set var="id" value="${dataset._id}"/>
    <c:url var="addCustomColumnFormActionUrl" value="/dataSet/addColumn?datasetId=${id}"/>
    <form:form id="surveyForm" role="form" action="${addCustomColumnFormActionUrl}" modelAttribute="addCustomColumnFormBean" method="post">
        <div class="form-group">
            <label class="col-sm-1 control-label">Name</label>
            <div class="col-sm-4">
                <form:input path="name" type="text" class="form-control" name="option[]" placeholder="Name"/>
            </div>
            <label class="col-sm-1 control-label">Type</label>
            <div class="col-sm-4">
                <form:input path="type" type="text" class="form-control" name="option[]" placeholder="Type"/>
            </div>
            <div class="col-sm-2">
                <button type="button" class="btn btn-default addButton">+</button>
            </div>
        </div>

        <!-- The option field template containing an option field and a Remove button -->
        <div class="form-group hide" id="optionTemplate">
            <div class="col-sm-offset-1 col-sm-4">
                <form:input path="name" class="form-control" type="text" name="option[]" placeholder="Name"/>
            </div>
            <div class="col-sm-offset-1 col-sm-4">
                <form:input path="type" class="form-control" type="text" name="option[]" placeholder="Type"/>
            </div>
            <div class="col-sm-2">
                <button type="button" class="btn btn-default removeButton">-</button>
            </div>
        </div>

        <button type="submit" class="btn btn-default">Submit</button>
    </form:form>

    <script>
        $(document).ready(function() {
            $('#surveyForm')
                // Add button click handler
                    .on('click', '.addButton', function() {
                        var $template = $('#optionTemplate'),
                                $clone    = $template
                                        .clone()
                                        .removeClass('hide')
                                        .removeAttr('id')
                                        .insertBefore($template),
                                $option   = $clone.find('[name="option[]"]');
                    })

                // Remove button click handler
                    .on('click', '.removeButton', function() {
                        var $row    = $(this).parents('.form-group'),
                                $option = $row.find('[name="option[]"]');

                        // Remove element containing the option
                        $row.remove();
                    });

        });
    </script>

    <br/>
    <c:set var="id" value="${dataset._id}"/>
    <c:url var="detailsUrl" value="/dataSet/details?datasetId=${id}"/>
    <a href="${detailsUrl}">
        <span class="glyphicon glyphicon-arrow-left"></span> Back to dataset details
    </a>

</div>


