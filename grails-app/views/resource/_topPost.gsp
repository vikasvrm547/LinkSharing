<div class="panel panel-primary">
    <div class="panel-heading">
        <div class="row">
        <h3 class="panel-title col-sm-6 ">Top stories</h3>
        </div>
    </div>

    <div class="panel-body top-post-panel-body">
        <g:each in="${topPosts}" var="post">
            <g:render template="/resource/show" model="[post:post]"/>
        </g:each>

    </div>

</div>