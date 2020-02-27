package plus.knowing.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ConvertUtil {

    /**
     * DBList 转 VOList
     *  VO需要（DB, VOTypeEnum）的构造函数
     * @param list
     * @param toClass
     * @param <T>
     * @param <D>
     * @return
     */
    public static <T, D> List<D> convert(List<T> list, Class<D> toClass) {
        return list.parallelStream().map(t -> {
            try {
                Constructor<D> constructor = toClass.getConstructor(t.getClass());
                return constructor.newInstance(t);
            } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                log.error(String.format("DBList 转 VOList, VO%s需要（DB）的构造函数", toClass), e);
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public static String simpleMarkdown(String content) {
        String str = content
                .replaceAll("(\\*\\*|__)(.*?)(\\*\\*|__)", "")      // 全局匹配内粗体
                .replaceAll("!\\[[\\s\\S]*?]\\([\\s\\S]*?\\)", "")  // 全局匹配图片
                .replaceAll("\\[[\\s\\S]*?]\\([\\s\\S]*?\\)", "")   // 全局匹配连接
                .replaceAll("</?.+?/?>", "")                        // 全局匹配内html标签
                .replaceAll("(\\*)(.*?)(\\*)", "")                  // 全局匹配内联代码块
                .replaceAll("`{1,2}[^`](.*?)`{1,2}", "")            // 全局匹配内联代码块
                .replaceAll("```([\\s\\S]*?)```[\\s]*", "")         // 全局匹配代码块
                .replaceAll("~~(.*?)~~", "")                        // 全局匹配删除线
                .replaceAll("\\+\\+(.*?)\\+\\+", "")                // 全局匹配下划线
                .replaceAll("[\\s]*[-*]+[\\s]*", " ")               // 全局匹配无序列表
                .replaceAll("[\\s]*[0-9]+.[\\s]*", " ")             // 全局匹配有序列表
                .replaceAll("(#+)[\\s]*", " ")                      // 全局匹配标题
                .replaceAll("(>+)[\\s]*", " ")                      // 全局匹配摘要
                .replaceAll("\r\n", " ")                            // 全局匹配换行
                .replaceAll("\n", " ")                              // 全局匹配换行
                .replaceAll("\\s", "");                             // 全局匹配空字符
        return str.length() > 100 ? String.format("%s...", str.substring(0, 97)) : str;
    }
}
