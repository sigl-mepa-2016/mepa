google.load('visualization', '1.0');
google.setOnLoadCallback(initialize);

function getUrlParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
}

var chart;

var graphCounter = 1;

var idDataSet = getUrlParameter('datasetId');
var listAxe;

var dataTable;

var graphColor1 = "ff8800";
var graphColor2 = "ff8800";

var graphType;

var pointsQuantity;

var horizontalAxe;
var hozizontalBool = false;

var verticalAxe1;
var agregateFunc1;
var verticalAxe2;


//Initializing elements when google visualisation is loaded
function initialize() {
    chart = new google.visualization.ChartWrapper({
        containerId: 'visualization_div'
    });
    horizontalAxe = document.getElementById('horizontal-axe');

    verticalAxe1 = document.getElementById('vertical-axe1');
    verticalAxe2 = document.getElementById('vertical-axe2');

    agregateFunc1 = document.getElementById('agregation-axe1');

    graphType = document.getElementById('graph-type');
    pointsQuantity = document.getElementById('points-quantity');

    $('.color-box1').colpick({
        colorScheme:'dark',
        layout:'rgbhex',
        color:'ff8800',
        onSubmit:function(hsb,hex,rgb,el) {
            $(el).css('background-color', '#'+hex);
            $(el).colpickHide();
            graphColor1 = hex;
            drawGraph();
        }
    }).css('background-color', '#ff8800');

    $('.color-box2').colpick({
        colorScheme:'dark',
        layout:'rgbhex',
        color:'ff8800',
        onSubmit:function(hsb,hex,rgb,el) {
            $(el).css('background-color', '#'+hex);
            $(el).colpickHide();
            graphColor2 = hex;
            drawGraph();
        }
    }).css('background-color', '#ff8800');

    graphType.onchange = function () {
        drawGraph();
    }

    pointsQuantity.onchange = function () {
        BuildDataTable();
    }

    verticalAxe1.onchange = function () {
        BuildDataTable();
    }

    verticalAxe2.onchange = function () {
        BuildDataTable();
    }

    agregateFunc1.onchange = function () {
        if(agregateFunc1.value != "")
        {
            $("#add-chart").hide();
        }
        else {
            $("#add-chart").show();
        }
        BuildDataTable();
    }

    horizontalAxe.onchange = function () {
        //mettre à jour les axes Y disponible
        verticalAxe1.value = "";
        verticalAxe2.value = "";

        if(horizontalAxe.value != "" && hozizontalBool == false) {

            $.each(listAxe, function (key, val) {
                if(key != horizontalAxe.value) {
                    if (listAxe[horizontalAxe.value] == "TEXT" && val != "TEXT") {
                        $('#vertical-axe1').append('<option value=' + key + '>' + key + '</option>');
                    }
                    else if (listAxe[horizontalAxe.value] != "TEXT" && val != "TEXT") {
                        $('#vertical-axe1').append('<option value=' + key + '>' + key + '</option>');
                    }
                }
            });
            $.each(listAxe, function (key, val) {
                if(key != horizontalAxe.value) {
                    if (listAxe[horizontalAxe.value] == "TEXT" && val != "TEXT") {
                        $('#vertical-axe2').append('<option value=' + key + '>' + key + '</option>');
                    }
                    else if (listAxe[horizontalAxe.value] != "TEXT") {
                        $('#vertical-axe2').append('<option value=' + key + '>' + key + '</option>');
                    }
                }
            });
            hozizontalBool = true;

        }
        if(horizontalAxe.value == "" && hozizontalBool == true) {
            for (var i = 1; i <= 2; i++) {
                $('#vertical-axe' + i + ' option').remove();
                $('#vertical-axe' + i).append("<option></option>");
            }
            hozizontalBool = false;
        }
    }
}

//Saving a chart when button addGraph is clicked
function addGraph() {
    graphCounter++;
    $("#line2").show();
    $("#add-chart").hide();
    $("#agregation-axe1").hide();

    document.getElementById('delete2').addEventListener('click', function() {
        graphCounter--;
        $("#line2").hide();
        $("#agregation-axe1").show();
        $("#add-chart").show();
        verticalAxe2.value = "";
        if(verticalAxe1.value != "")
            BuildDataTable();
    }, false);
}

//Drawing the chart based on dataTable with good options
function drawGraph() {
    chart.setChartType(graphType.value);
    chart.setOption('colors', [graphColor1, graphColor2]);
    chart.setDataTable(dataTable);
    chart.draw();
}

//Count function used for the agregation
function count(array_elements) {
    array_elements.sort();

    var arrayOutput = [];
    var current = null;
    var cnt = 0;
    for (var i = 0; i < array_elements.length; i++) {
        if (array_elements[i] != current) {
            if (cnt > 0) {
                arrayOutput.push([current, cnt]);
            }
            current = array_elements[i];
            cnt = 1;
        } else {
            cnt++;
        }
    }
    if (cnt > 0) {
        arrayOutput.push([current, cnt]);
    }
    return arrayOutput;
}

//min function used for the agregation
function min(array_elements) {
    array_elements.sort();

    var arrayOutput = [];
    var current = array_elements[0][0];
    var min = array_elements[0][1];

    for (var i = 0; i < array_elements.length; i++) {
        if (array_elements[i][0] != current) {
            arrayOutput.push([current, min]);
            current = array_elements[i][0];
            min = array_elements[i][1];
        } else {
            if (min > array_elements[i][1])
                min = array_elements[i][1];
        }
    }
    arrayOutput.push([current, min]);

    return arrayOutput;
}

//max function used for the agregation
function max(array_elements) {
    array_elements.sort();

    var arrayOutput = [];
    var current = array_elements[0][0];
    var max = array_elements[0][1];

    for (var i = 0; i < array_elements.length; i++) {
        if (array_elements[i][0] != current) {
            arrayOutput.push([current, max]);
            current = array_elements[i][0];
            max = array_elements[i][1];
        } else {
            if (max < array_elements[i][1])
                max = array_elements[i][1];
        }
    }
    arrayOutput.push([current, max]);

    return arrayOutput;
}

function BuildDataTable() {
    $.ajax({
        url : '/mepa-front/api/dataSet/' + idDataSet + '/data.json',
        type : 'GET',
        dataType : 'json',
        success: function(dataSet) {

            dataTable = new google.visualization.DataTable();

            var h1 = horizontalAxe.value;
            var v1 = verticalAxe1.value;
            var agreg1 = agregateFunc1.value;
            var v2 = verticalAxe2.value;
            var rows = [];

            if(listAxe[h1] == "TEXT")
                dataTable.addColumn('string', h1);
            if(listAxe[h1] == "NUMBER")
                dataTable.addColumn('number', h1);
            if(listAxe[h1] == "DATE")
                dataTable.addColumn('number', h1);

            if(listAxe[v1] == "TEXT")
                dataTable.addColumn('string', v1);
            if(listAxe[v1] == "NUMBER")
                dataTable.addColumn('number', v1);
            if(listAxe[v1] == "DATE")
                dataTable.addColumn('date', v1);

            var col1 = dataSet.data[h1].value;
            var col2 = dataSet.data[v1].value;

            for (var i = 0; i < col1.length;i++)
            {
                if(listAxe[h1] == "NUMBER")
                {
                    col1[i] = parseFloat(col1[i]);
                }
                if(listAxe[v1] == "NUMBER")
                {
                    col2[i] = parseFloat(col2[i]);
                }
            }

            if(graphCounter == 2 && v2 != "" && v2 != v1) {
                if (listAxe[v2] == "TEXT")
                    dataTable.addColumn('string', v2);
                if (listAxe[v2] == "NUMBER")
                    dataTable.addColumn('number', v2);
                if (listAxe[v2] == "DATE")
                    dataTable.addColumn('date', v2);

                var col3 = dataSet.data[v2].value;

                for (var i = 0; i < col3.length; i++)
                {
                    if(listAxe[v2] == "NUMBER")
                    {
                        col3[i] = parseFloat(col3[i]);
                    }
                }
                while (col1.length != 0) {
                    rows.push([col1.pop(), col2.pop(), col3.pop()]);
                }
            }
            else {
                if (agreg1 == "Count") {
                    rows = count(col1);
                }
                else {
                    while (col1.length != 0) {
                        rows.push([col1.pop(), col2.pop()]);
                    }
                    if (agreg1 == "Min") {
                        rows = min(rows);
                    }
                    else if (agreg1 == "Max") {
                        rows = max(rows);
                    }
                }
            }
            rows.sort();

            if(pointsQuantity.value != "")
                rows = rows.slice(0, pointsQuantity.value);

            dataTable.addRows(rows);
            drawGraph();
        },
        error: function() {
            console.log("error while getting the dataset from API");
        }
    });
}

//Initialize the horizontal axe with value
function initializeHorizontalAxe() {
//Charger tous les axes dans l'horizontal

    $.ajax({
        url : '/mepa-front/api/dataSet/' + idDataSet + '.json',
        type : 'GET',
        dataType : 'json',
        success: function(data) {
            listAxe = data.fieldMap;
            $.each(listAxe, function(key, val)
            {
                $('#horizontal-axe').append('<option value=' + key + '>' + key + '</option>');
            });
        },
        error: function() {
            console.log("error while getting the dataset from API");
        }
    });
}

//the admin will save the graph into the database.
function saveGraphintoDB() {
    var printedGraphType = graphType.value;
    var printedGraphColor1 = graphColor1;
    var printedGraphColor2 = graphColor2;
    var printedDataTable = dataTable;

    $.ajax({
        url : '/mepa-front/api/SetGraph/' + idDataSet + '.json',
        type : 'GET',
        dataType : 'json',
        success: function(graph) {

        },
        error: function() {
            console.log("error while storing the graph with the API");
        }
    });
}

//get the graph from the database.
function getGraphFromDB() {
    $.ajax({
        url : '/mepa-front/api/GetGraph/' + idDataSet + '.json',
        type : 'GET',
        dataType : 'json',
        success: function(graph) {
            var printedGraphType = graph.grapheType;
            var printedGraphColor1 = graph.grapheColor1;
            var printedGraphColor2 = graph.grapheColor2;
            var printedDataTable = graph.grapheJson;

            chart.setChartType(printedGraphType);
            chart.setOption('colors', [printedGraphColor1, printedGraphColor2]);
            chart.setDataTable(printedDataTable);
            chart.draw();
        },
        error: function() {
            console.log("error while getting the graph from API");
        }
    });
}

//Binding the button and the addGraph function when window is loaded
window.addEventListener('load',function(){
    $.ajax({
        url : '/mepa-front/api/user/isConnected.Json',
        type : 'GET',
        dataType : 'json',
        success: function(connection) {
            console.log(connection);
            if(true) {
                $("#line2").hide();
                initializeHorizontalAxe();

                document.getElementById('add-chart').addEventListener('click', function () {
                    addGraph();
                }, false);

                document.getElementById('save-graph').addEventListener('click', function () {
                    saveGraphintoDB();
                }, false);
            }
            else
            {
                //remove HTML elements
                $("#chart-view table").remove();
                getGraphFromDB();
            }
        },
        error: function() {
            console.log("error while getting the graph from API");
        }
    });
});
