package com.hlib.http.request;

import android.content.Context;

import com.hlib.util.MStringUtil;
import com.hlib.util.MToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 存放校验条件和响应信息
 * Created by moguangjian on 16/8/22 10:15.
 */
public class MParams {
    private Context context;
    private List<String> conditions;
    private List<String> messages;

    public MParams(Context context) {
        this.context = context;
        conditions = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public MParams put(String params, String message) {
        String key = "";
        if (!MStringUtil.isEmpty(params)) {
            key = params;
        }

        conditions.add(key);
        if (MStringUtil.isEmpty(message)) {
            message = "请输入";
        }
        messages.add(message);
        return this;
    }

    public boolean isShowNullHint() {

        if (conditions.isEmpty()) {
            return false;
        }

        int size = conditions.size();
        for (int i = 0; i < size; i++) {
            String key = conditions.get(i);
            if (MStringUtil.isEmpty(key)) {
                MToastUtil.show(context, messages.get(i));
                return true;
            }
        }

        return false;
    }
}
