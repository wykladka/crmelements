<#include "general/header.ftl">

<fieldset>
<form name="simpleBinding" action="createSimpleBinding" method="post">



    CeneoId:<br>
    <input type="text" name="ceneoId" value="ceneo">
    <br>
    ID Z XMLA<br>
    <input type="text" name="ownProductId" value="my">
    <br><br>
    <input type="submit" value="Submit">
</form>
</fieldset>



<table >
    <tr>
        <th>ceneo</th>  <th>own produc</th>
    </tr>
<#list model["simpleBindingList"] as simpleBinding>
    <tr>
        <td>${simpleBinding.ceneoId}</td> <td>${simpleBinding.ownProductId}</td>
    </tr>
</#list>
</table>







<#include "general/footer.ftl">
