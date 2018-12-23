$.ajaxSetup({cache: false});

var lstGraphLoader = (function(){
    var setFun={
        getLstGraph: function () {
            var dataSource=new kendo.data.DataSource({
                transport: {
                    read:{
                        url: "getGraphData",
                        dataType:"json",
                        complete:function (e) {
                            var data=e.responseJSON;
                            data[0].inst_disp_name="合計";
                            $("#lst-graph-container").empty();
                            $.each(data, function (idx, item) {
                                $("#lst-graph-container").append("<div id='lst_graph_"+item.inst_disp_id+"' class='lst-graph'>");
                            });
                            setFun.setLstGraph(data);
                        }
                    }
                }
            }).fetch();

        },

        setLstGraph: function (data) {
            console.log(data);
            $.each($("#lst-graph-container").find("div.lst-graph"), function (idx, item) {
                console.log(item.id);
                console.log(data[idx]);
                $("#"+item.id).kendoChart({
                    title: {
                        text: data[idx].inst_disp_name
                    },
                    legend: {
                        position: "top",
                        visible: true
                    },
                    series:data[idx].inst_disp_series,
                    tooltip: {
                        visible: true,
                        template: "#= series.name #: #= value #"
                    }
                });
            });
        }
    };
    return {
        setFun:setFun
    }
})();

$(function () {
    lstGraphLoader.setFun.getLstGraph();
});