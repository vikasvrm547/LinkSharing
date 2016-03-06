<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">Trending topic</h3>
    </div>

    <div class="panel-body tending-topic-panel-body">
        <g:each in="${tendingTopics}">
            <g:render template="/topic/show" model="[topic: it]"/>
        </g:each>
    </div>
</div>