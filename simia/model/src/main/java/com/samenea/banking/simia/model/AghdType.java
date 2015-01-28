package com.samenea.banking.simia.model;

public enum AghdType {
    FOROSH_AGHSATI(0),
    MOZAREBE(1),
    MOSHAREKAT(2),
    GHARZOL_HASANE(3),
    NORMAL(4),
    GHARZOL_HASANE_SANAVATI(5),
    EJARE_BE_SHARTE_TAMLIK(6),
    JOALEH(7),
    KHARIDE_DEIN(8),
    ZEMANAT_NAMEH(9),
    SALAF(10);

    private int typeCode;

    private AghdType(int code) {
        this.typeCode = code;
    }

    public int getLoanTypeCode() {
        return this.typeCode;
    }


    public static AghdType enumOf(int n) {
        for (AghdType e : values()) if (e.typeCode == n) return e;
        return null;
    }

}
