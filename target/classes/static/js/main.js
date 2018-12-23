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
        }
    };
    return {
        setFun:setFun
    }
})();

$(function () {
    lstGraphLoader.setFun.getLstGraph();
});