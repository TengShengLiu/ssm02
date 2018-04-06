$(function () {
    var treeData = [{
        "id":1,
        "text":"系统管理",
        "children":[{
            "id":11,
            "text":"用户管理",
            "url":"user/toUser"
        },{
            "id":12,
            "text":"角色管理",
            "url":"role/toRole"
        },{
            "id":13,
            "text":"权限管理",
            "url":"auth/toMain"
        }]
    }];








    $("#my-tree").tree({
        data:treeData,
        onClick:function (node) {
            console.log(node);
            if(!node.children){
                //打开页面,如果有此标签
                if($("#main-tab").tabs("exists",node.text)){
//                            已有，打开已有的
                    $("#main-tab").tabs("select",node.text)
                }else {
                    $("#main-tab").tabs("add",{
                        title:node.text,
                        content:"<iframe width='100%' height='100%' frameborder='0'"+"src="+node.url+"/>",
                        closable:true
                    });
                }
            }
        }
    })

    $("#auth-tree").tree({

    })

})