package committee.nova.petphrasex.util;

import committee.nova.petphrasex.petphrasex;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class StringUtil {
    static final List<Character> punctuations = List.of('!', '?', '.', '(', ')', '！', '？', '。', '（', '）', '~', '”', '“', '‘', '’', '"', '\'');

    public static int getLastPunc(String string) {
        final int length = string.length();
        if (length == 1) return (punctuations.contains(string.charAt(0))) ? -1 : 0;
        return getPunc(string, length - 1);
    }

    public static int getPunc(String string, int index) {
        return (!punctuations.contains(string.charAt(index))) ? index : (index == 0) ? -1 : getPunc(string, index - 1);
    }

    public static String fillpetphrasexIn(String original, String petphrasex) {
        if (original == "") return original;
        for (final String f : petphrasex.filteredPrefix.get()) if (original.startsWith(f)) return original;
        final int index = getLastPunc(original) + 1;
        if (index == 0) return original;
        return StringUtils.substring(original, 0, index) + petphrasex + StringUtils.substring(original, index);
    }
}
