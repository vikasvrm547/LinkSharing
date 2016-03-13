<g:each in="${topics}">

    <g:render template="/topic/show" model='[topic: it,
                    uniqueIdForTopicEdit: "${session.uniqueIdForTopicEdit ? session.uniqueIdForTopicEdit++ : null}"]'/>
</g:each>