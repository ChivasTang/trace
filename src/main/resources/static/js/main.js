$.ajaxSetup({cache: false});

var lstGraphLoader = (function () {
    var setFun = {

        pageReady: function () {
            setFun.getCategoryMenu();
        },

        getCategoryMenu: function () {
            var dataSource = new kendo.data.DataSource({
                transport: {
                    read: {
                        url: "getCategoryList",
                        dataType: "json",
                        contentType: "application/json",
                        data: {locale: "ja"}
                    }
                }
            });

            dataSource.fetch(function () {
                var category_list = dataSource.data();
                console.log(category_list);
            });

            setFun.setCategoryMenu();
        },

        setCategoryMenu: function () {
            var category_id, category_name;
            $("#category_menu").kendoMenu({
                //dataSource:dataSource,
                openOnClick: {
                    rootMenuItems: true,
                    subMenuItems: false
                },
                open: function (e) {
                    console.log("Open Event");
                    $(".root-category").on("click",function () {
                        $("#category_menu").data("kendoMenu").close();
                    });
                },
                select: function (e) {
                    e.preventDefault();
                    console.log("Select Event");
                    var show_all=$(e.item.innerHTML).children("label").eq(0).children("input").val();
                    console.log("select show_all: " + show_all);
                    if(undefined!=show_all && 0==show_all){
                        $("#ca0-li").fadeOut();
                    }else{
                        $("#ca0-li").fadeIn();
                    }
                    category_id = $(e.item.innerHTML).children("label").eq(0).children("input").val();
                    category_name = $(e.item.innerHTML).children("label").eq(0).children("span").text();
                    return false;
                },
                close: function (e) {
                    console.log("Close Event");
                    $("#selector1").val(category_id);
                    $("#selector1-text").html(category_name);
                    return false;
                }
            });


        }
    };
    return {
        setFun: setFun
    }
})();

$(function () {
    lstGraphLoader.setFun.pageReady();
});