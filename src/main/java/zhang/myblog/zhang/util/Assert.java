package zhang.myblog.zhang.util;


import org.apache.commons.lang.StringUtils;

/**
 * Created with IDEA
 *数据校验
 * @author xp
 * @date 2019/2/21 10:47
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        /**
         * 字符串为空
         */
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }
    public static void isNull(Object object, String message) {
        /**
         *对象为null
         */
        if (object == null) {
            throw new RRException(message);
        }
    }
}
