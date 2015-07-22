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

var pointsQuantity = 0;

var horizontalAxe;
var hozizontalBool = false;

var verticalAxe1;
var verticalAxe2;


//Initializing elements when google visualisation is loaded
function initialize() {
    chart = new google.visualization.ChartWrapper({
        containerId: 'visualization_div'
    });
    horizontalAxe = document.getElementById('horizontal-axe');

    verticalAxe1 = document.getElementById('vertical-axe1');
    verticalAxe2 = document.getElementById('vertical-axe2');

    graphType = document.getElementById('graph-type');
    //pointsQuantity = document.getElementById('points-quantity');

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

    /*pointsQuantity.onchange = function () {
        //restreindre le nombre de points puis draw le graph
        drawGraph();
    }*/

    verticalAxe1.onchange = function () {
        BuildDataTable();
    }

    verticalAxe2.onchange = function () {
        BuildDataTable();
    }

    horizontalAxe.onchange = function () {
        //mettre à jour les axes Y disponible
        verticalAxe1.value = "";
        verticalAxe2.value = "";

        if(horizontalAxe.value != "" && hozizontalBool == false) {

            $.each(listAxe, function (key, val) {
                if(key != horizontalAxe.value)
                    $('#vertical-axe1').append('<option value=' + key + '>' + key + '</option>');
            });
            $.each(listAxe, function (key, val) {
                if(key != horizontalAxe.value)
                    $('#vertical-axe2').append('<option value=' + key + '>' + key + '</option>');
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

    document.getElementById('delete2').addEventListener('click', function() {
        graphCounter--;
        $("#line2").hide();
        $("#add-chart").show();
        verticalAxe2.value = "";
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

function BuildDataTable() {
    $.ajax({
        url : '/api/dataSet/' + idDataSet + '/data.json',
        type : 'GET',
        dataType : 'json',
        success: function(dataSet) {

            dataTable = new google.visualization.DataTable();

            var h1 = horizontalAxe.value;
            var v1 = verticalAxe1.value;
            var v2 = verticalAxe2.value;
            var rows = [];

            dataTable.addColumn('string', h1);
            dataTable.addColumn('number', v1);

            if(graphCounter == 2 && v2 != "" && v1 != "" && v1 != v2) {
                dataTable.addColumn('number', v2);

                var col1 = dataSet.data[h1];
                var col2 = dataSet.data[v1];
                var col3 = dataSet.data[v2];

                while (col1.length != 0)
                {
                    rows.push([col1.pop(), parseInt(col2.pop()), parseInt(col3.pop())]);
                }
            }
            else {
                var col1 = dataSet.data[h1];
                var col2 = dataSet.data[v1];

                while (col1.length != 0)
                {
                    rows.push([col1.pop(), parseInt(col2.pop())]);
                }
            }

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
        url : '/api/dataSet/' + idDataSet + '.json',
        type : 'GET',
        dataType : 'json',
        success: function(data) {
            listAxe = data.fieldMap;
            /*
            listAxe =  {"student name":"String",
            "student age":"integer",
            "Student Height":"integer",
            "Student Grade":"integer"};
            */
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

//Binding the button and the addGraph function when window is loaded
window.addEventListener('load',function(){
    $("#line2").hide();
    initializeHorizontalAxe();

    document.getElementById('add-chart').addEventListener('click', function() {
        addGraph();
    }, false);
});