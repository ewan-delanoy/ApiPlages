package com.ewan.apiplages.util;

import com.ewan.apiplages.entity.Affectation;
import com.ewan.apiplages.entity.Equipement;
import com.ewan.apiplages.entity.File;

public class KeepCompilerQuiet {

    public static boolean doNotModifyBoolean(boolean b) {
        return doNotModifyBoolean1(b, 1L) && doNotModifyBoolean1(b, 2L);
    }

    private static boolean doNotModifyBoolean1(boolean b, Long l) {
        Long l2 = doNotModifyLong(l);
        return l.equals(l2) && b;
    }
    public static Long doNotModifyLong(Long l) {
        return (l>3L)?doNotModifyLong1(l):l;
    }

    private static Long doNotModifyLong1(Long l) {
        return l;
    }

    public static void doNothingWithLong(Long l) {
        if( l>4L) {
            doNothing1(5L);
            doNothing1(6L);
        }
    }

    private static void doNothing1(Long l) {
        if( l>7L) {
            doNothingWithLong(7L);
        }
    }

    public static void doNothing() {
        Equipement equipement = new Equipement((byte)1,(byte)1);
        equipement.keepCompilerQuiet();
        File file = new File();
        file.keepCompilerQuiet();
        Affectation affectation = new Affectation();
        affectation.keepCompilerQuiet();
        doNothing1();
    }

    private static void doNothing1() {
        boolean b = false;
        b = doNotModifyBoolean(b);
        if(b) {
            doNothing();
        }
    }
}
