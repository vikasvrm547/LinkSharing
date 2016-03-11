package com.tothenew.enums


enum Visibility {
    PUBLIC("Public"),
    PRIVATE("Private")

    final String value

    Visibility(String value) {
        this.value = value
    }

    String toString() { value }

    String getKey() { name() }

    static Visibility convertToEnum(String visibility){
        try{
            return valueOf(visibility.toUpperCase())
        }catch (Exception e){
            return null
        }
    }
}