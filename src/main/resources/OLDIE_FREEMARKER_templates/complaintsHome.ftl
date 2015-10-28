<#include "general/header.ftl">
<h1>COMPLAINTS STREET</h1>
<div>
    Tu bedzie podsumowanie w liczbach, zalatwione nie zalatwion i ile na kim wisi, plus ile sie zbliza deadline
</div>
<div>
    <h2>Lista wszystkich reklamacji</h2>
    <table border=1>
    <#if complaints?has_content>
        <#list complaints as complaint>
            <tr>
                <td>${city.name}
                <td/>
            <tr/>
        </#list>
    <#else>
        Brak reklamacji w bazie!
    </#if>
    </table>
</div>
<#include "general/footer.ftl">
