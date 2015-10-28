<#include "general/header.ftl">
<h1>show al own STREET</h1>
<h2>Lista wszystkich reklamacji</h2>
<table border=1>
<#if ownproductlist?has_content>
    <#list ownproductlist as ownproduct>
        <tr>
            <td>${ownproduct.name}
            <td/>
            <td>${ownproduct.id}
            <td/>
            <td>${ownproduct.price}
            <td/>
        <tr/>
    </#list>
<#else>
    Brak produktow w xml lub blad xmla!
</#if>
</table>
<#include "general/footer.ftl">
