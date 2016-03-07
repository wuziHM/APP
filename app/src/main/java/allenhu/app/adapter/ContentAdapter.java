package allenhu.app.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;

import java.util.List;

import allenhu.app.util.StringMatcher;

/**
 * Created by AllenHu on 2016/2/26.
 */
public class ContentAdapter extends ArrayAdapter<String> implements SectionIndexer {
    private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public ContentAdapter(Context context, int textViewResourceId,
                          List<String> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public int getPositionForSection(int section) {
        // If there is no item for current section, previous section will be
        // selected
        // 如果当前部分没有item，则之前的部分将被选择
        for (int i = section; i >= 0; i--) {
            for (int j = 0; j < getCount(); j++) {
                System.out.println(getCount());
                if (i == 0) { // #
                    // For numeric section 数字
                    for (int k = 0; k <= 9; k++) {// 1...9
                        // 字符串第一个字符与1~9之间的数字进行匹配
                        if (StringMatcher.match(
                                String.valueOf(getItem(j).charAt(0)),
                                String.valueOf(k)))
                            return j;
                    }
                } else { // A~Z
                    if (StringMatcher.match(
                            String.valueOf(getItem(j).charAt(0)),
                            String.valueOf(mSections.charAt(i))))
                        return j;
                }
            }
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    @Override
    public Object[] getSections() {
        String[] sections = new String[mSections.length()];
        for (int i = 0; i < mSections.length(); i++)
            sections[i] = String.valueOf(mSections.charAt(i));
        return sections;
    }
}
