<%--
  Created by IntelliJ IDEA.
  User: Qin_Yikai
  Date: 2018-09-20
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../common/tag.jsp" %>
    <%@ include file="../../common/head.jsp" %>
</head>
<body>
<div class="edit-div">
    <form class="layui-form" id="editPtForm" action="" lay-filter="pt">
        <input type="hidden" name="id" value="${productType.id}">
        <input type="hidden" name="parentTypeId" value="${productType.parentTypeId}">
        <div class="layui-form-item">
            <label class="layui-form-label"><span class="x-red">*</span>类型名称</label>
            <div class="layui-input-block">
                <input type="text" name="typeName" lay-verify="notempty" lay-vertype="tips" autocomplete="off"
                       placeholder="请输入类型名称"
                       class="layui-input" value="${productType.typeName}">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"><span class="x-red">*</span>类型代码</label>
            <div class="layui-input-block">
                <input type="text" name="typeCode" lay-verify="notempty" lay-vertype="tips" autocomplete="off"
                       placeholder="请输入类型代码"
                       class="layui-input" value="${productType.typeCode}">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="checkbox" name="status"
                       value="${productType.status}" ${productType.status == 1 ? "checked" : ""} lay-skin="switch"
                       lay-text="ON|OFF" lay-filter="status">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="submit">立即提交</button>
                <button type="reset" id="cancel" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer

        //自定义验证规则
        form.verify({
            notempty: function (value, obj) {
                var vname = $(obj).parent().prev().text();
                if (value.match(/^\s*$/)) {
                    return vname + '不能为空';
                }
            }
            /*    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
             ,content: function(value){
             layedit.sync(editIndex);
             }*/
        });

        //监听提交
        form.on('submit(submit)', function (data) {
            var loadIndex = layer.load(0);
            $.ajax({
                type: "POST",
                url: "/productType/save",
                data: JSON.stringify(data.field),
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    layer.close(loadIndex);
                    if (data.success) {
                        //parent 是 JS 自带的全局对象，可用于操作父页面
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

                        layer.msg(data.message, {
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            parent.layer.close(index);
                            parent.layui.table.reload('pt')
                        });

                    } else {
                        layer.msg(data.message);
                    }
                }
            });
            return false;
        });

        //监听指定开关
        form.on('switch(status)', function (data) {
            if (this.checked) {
                $('input[name=status]').val(1);
            } else {
                $('input[name=status]').val(0);
            }
        });
    })

</script>
</body>
</html>
