<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title col-xs-6">Top stories</h3>
            <button type="button" >
                </button>
    </div>

    <div class="panel-body top-post-panel-body">
        <g:each in="${topPosts}" var="post">
            <g:render template="/resource/show" model="[post:post]"/>
        </g:each>

    </div>

</div>