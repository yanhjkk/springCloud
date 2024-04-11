package com.example.ykcloud.emums;

import lombok.Getter;

@Getter
public enum NotifyIconEnum {
    SUCCESS("success"),
    INFO("info"),
    WARNING("warning"),
    ERROR("error");

    private String code;

    private NotifyIconEnum(String code) {
        this.code = code;
    }

    public static boolean containCode(String code) {
        boolean flag = false;
        NotifyIconEnum[] values = values();
        NotifyIconEnum[] var3 = values;
        int var4 = values.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            NotifyIconEnum value = var3[var5];
            if (value.code.equals(code)) {
                flag = true;
            }
        }

        return flag;
    }
}
