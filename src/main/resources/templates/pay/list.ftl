<html>
    <head>
        <meta charset="utf-8">
        <title>买家商品列表</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" href="/sell/css/style.css">
    </head>
    <body>
    <div id="wrapper" class="toggled">
        <#--边栏0-->

        <#include "../common/nav.ftl">
        <#--主要内容-->
        <div class="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>
                                    编号
                                </th>
                                <th>
                                    产品
                                </th>
                                <th>
                                    交付时间
                                </th>
                                <th>
                                    状态
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list  orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum.message}</td>
                                <td>${orderDTO.getPayStatusEnum.message}</td>
                                <td>详情</td>
                                <td><#if orderDTO.getOrderStatusEnum=="新订单">
                                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                </#if></td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                <#--分页-->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled">
                                <a href="#">上一页</a>
                            </li>
                        <#else >
                            <li>
                                <a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a>
                            </li>
                        </#if>

                        <#list 0..<orderDTOPage.getTotalPages() as index>
                            <#if currentPage==index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else >
                                <li>
                                    <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>

                        </#list>
                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="">下一页</a> </li>
                        <#else >
                            <li>
                                <a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a>
                            </li>
                        </#if>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </body>
</html>


<#--<h1>${orderDTOPage.getTotalPages()}</h1>-->


