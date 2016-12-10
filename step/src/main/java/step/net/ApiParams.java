package step.net;

import java.util.HashMap;


public class ApiParams extends HashMap<String, String> {
    private static final long serialVersionUID = 8112047472727256876L;


    public ApiParams with(String key, String value) {
        put(key, value);
        return this;
    }

    public ApiParams with(String key, int value) {
        put(key, String.valueOf(value));
        return this;
    }

    public ApiParams with(String key, float value) {
        put(key, String.valueOf(value));
        return this;
    }

    /**
     * 创建通用rest api请求参数
     *
     * @param version 接口版本号
     * @return
     */
    public ApiParams with(String version) {
        put(NetworkConstants.version_key, version);
        put(NetworkConstants.source_key, NetworkConstants.source_value);
        put(NetworkConstants.os_key, NetworkConstants.os_value);
        put(NetworkConstants.pro_key, NetworkConstants.pro_value);
        return this;
    }

    /**
     * 创建通用sign参数
     *
     * @return
     */
    public ApiParams withSign() {
        with(NetworkConstants.SIGN,
                DataRequestTool.getSig(this,
                        NetworkConstants.SIGNKEY));
        return this;
    }
}
