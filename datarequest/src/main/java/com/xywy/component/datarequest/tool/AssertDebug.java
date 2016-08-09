package com.xywy.component.datarequest.tool;

import com.xywy.component.datarequest.BuildConfig;

/**
 * 只在编译环境下使用的Assert
 */
public class AssertDebug {

    public static void Assert(Object obj) {

        if(BuildConfig.DEBUG) {
            assert obj == null;
        }
    }
}
