<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/home">Azymut 0.1</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">

            <#include "ceneokiller.ftl">
            <#include "complaints.ftl">
            <#include "pltopo.ftl">
            <#include "leaves.ftl">
            <#include "settings.ftl">

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Wyloguj [Adam Test]</a></li>
            </ul>

            https://github.com/jbake-org/jbake-example-project-freemarker/blob/master/OLDIE_FREEMARKER_templates/menu.ftl


        </div>
        <!--/.nav-collapse -->
    </div>
    <!--/.container-fluid -->
</nav>