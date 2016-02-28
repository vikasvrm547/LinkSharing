package com.tothenew.enums


enum Visibility {
    PUBLIC,PRIVATE

    static Visibility convertToEnum(String visibility){
        try{
            return valueOf(visibility.toUpperCase())
        }catch (Exception e){
            return null
        }
    }
}