package com.tothenew.enums

enum Seriousness {
    SERIOUS("Serious"),
    VERY_SERIOUS("Very serious"),
    CASUAL("Casual")



    final String value

    Seriousness(String value) {
        this.value = value
    }

    String toString() { value }

    String getKey() { name() }

    static Seriousness convertToEnum(String seriousness){
        try{
            return valueOf(seriousness.toUpperCase())
        }catch (Exception e){
            return null
        }
    }

}