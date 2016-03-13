package com.tothenew

import com.tothenew.co.ResourceSearchCO
import com.tothenew.vo.PostVO
import grails.transaction.Transactional

@Transactional
class ResourceService {

    def search(ResourceSearchCO resourceSearchCO){
        List<PostVO> resources = []
         Resource.search(resourceSearchCO).list([max:resourceSearchCO.max,offset:resourceSearchCO.offset]).each { resource ->
            resources.add(Resource.getPost(resource.id))
         }
        return resources
    }
}
