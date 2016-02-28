package com.tothenew.enums

enum Seriousness {
    SERIOUS,VERY_SERIOUS,CASUAL

    static Seriousness convertToEnum(String seriousness){
        try{
            return valueOf(seriousness.toUpperCase())
        }catch (Exception e){
            return null
        }
    }
}