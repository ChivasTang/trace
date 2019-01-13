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
                var category_list = setFun.getCategoryList(dataSource);
                var category_menu_html=setFun.createCategoryMenu(category_list);
                $("#category_menu").empty().html(category_menu_html);
                console.log($("#category_menu"));
            }).then(function () {
                setFun.setCategoryMenu();
            });


        },

        createCategoryMenu: function (category_list) {
            var category_menu = '<li>' +
                '<label for="selector1"><input id="selector1" type="hidden" disabled="disabled" value="0"><span id="selector1-text">すべてのカテゴリ</span></label>' +
                '<ul>' +
                '<li id="ca0-li" class="root-category" style="display: none;"><label for="ca0"><input id="ca0" name="ca0" type="hidden" value="0"><span id="ca0-text">すべてのカテゴリ</span></label></li>';
            $.each(category_list, function (idx, item) {
                if (item.items.length <= 0) {
                    category_menu += '<li class="root-category"><label for="ca' + item.category_id + '"><input id="ca' + item.category_id + '" name="ca' + item.category_id + '" type="hidden" value="' + item.category_id + '"><span id="ca' + item.category_id + '-text">' + item.resc_key + '</span></label></li>';
                } else {
                    category_menu += '<li class="root-category"><label for="ca' + item.category_id + '"><input id="ca' + item.category_id + '" name="ca' + item.category_id + '" type="hidden" value="' + item.category_id + '"><span id="ca' + item.category_id + '-text">' + item.resc_key + '</span></label><ul>';
                    $.each(item.items, function (subIdx, subItem) {
                        category_menu += '<li><label for="ca' + subItem.category_id + '"><input id="ca' + subItem.category_id + '" name="ca' + subItem.category_id + '" type="hidden" value="' + subItem.category_id + '"><span id="ca' + subItem.category_id + '-text">' + subItem.resc_key + '</span></label></li>';
                    });
                    category_menu += '</ul></li>';
                }
            });
            category_menu += '</ul></li>';
            console.log(category_menu);
            return category_menu;
        },

        getCategoryList: function (category_dataSource) {
            var category_list = category_dataSource.data();
            var category_list_arr = [];
            $.each(category_list, function (idx, item) {
                if (0 === item.category_level && null === item.root_category_id) {
                    var category_list_item = {
                        category_id: item.category_id,
                        resc_key: item.resc_key,
                        category_level: item.category_level,
                        root_category_id: item.root_category_id
                    };
                    category_list_item.items = setFun.getCategoryListItems(category_list, category_list_item);
                    category_list_arr.push(category_list_item);
                }
            });
            return category_list_arr;
        },

        getCategoryListItems: function (category_list, category_list_item) {
            var items = [];
            $.each(category_list, function (idx, item) {
                if (category_list_item.category_id === item.root_category_id) {
                    items.push(item);
                }
            });
            return items;
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
                    $(".root-category").on("click", function () {
                        $("#category_menu").data("kendoMenu").close();
                    });
                },
                select: function (e) {
                    e.preventDefault();
                    console.log("Select Event");
                    var show_all = $(e.item.innerHTML).children("label").eq(0).children("input").val();
                    console.log("select show_all: " + show_all);
                    if (undefined != show_all && 0 == show_all) {
                        $("#ca0-li").fadeOut();
                    } else {
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
            $("#category_menu").show();

        }
    };
    return {
        setFun: setFun
    }
})();

$(function () {
    lstGraphLoader.setFun.pageReady();
});