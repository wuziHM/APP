package allenhu.app.adapter;

import android.content.Context;
import android.transition.Slide;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;

import java.util.ArrayList;

import allenhu.app.util.StringMatcher;

/**
 * Created by AllenHu on 2016/2/26.
 */
public class ContentAdapter extends ArrayAdapter<String> implements SectionIndexer {
    private String mSe = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public ContentAdapter(Context context, int resource, ArrayList mItems) {
        super(context, resource);
    }

    @Override
    public Object[] getSections() {
        String[] section = new String[mSe.length()];
        //将每个Section作为单个数组元素放到sections中
        for (int i = 0; i < section.length; i++) {
            section[i] = String.valueOf(mSe.charAt(i));
        }
        return section;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        //如果点击的没有的话  从当前的section往前查，一直到有对应的item为止，否则不进行定位
        for (int i = sectionIndex; i >= 0; i++) {
            for (int j = 0; j < getCount(); j++) {
                //查询数字
                if (i == 0) {
                    for (int k = 0; k < 9; k++) {
                        if (StringMatcher.mathch(String.valueOf(getItem(j).charAt(0)), String.valueOf(k))) {
                            return j;
                        }
                    }
                }
                //查询字母
                else {
                    if (StringMatcher.mathch(String.valueOf(getItem(j).charAt(0)), String.valueOf(mSe.charAt(i)))) {
                        return j;
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }
}
