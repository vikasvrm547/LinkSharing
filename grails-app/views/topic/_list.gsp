<g:each in="${topics}">
    <g:render template="/topic/show" model="[topic: it]"/>
</g:each>