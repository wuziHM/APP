package allenhu.app.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.ContactBean;

/**
 * Created by AllenHu on 2016/3/1.
 */
public class ContactUtil {
    /**
     * 得到手机通讯录联系人信息
     **/
    private List<ContactBean> getPhoneContacts(Context mContext) {

        ArrayList<ContactBean> contactBeans = new ArrayList<>();

        /**联系人显示名称**/
        final int PHONES_DISPLAY_NAME_INDEX = 0;

        /**电话号码**/
        final int PHONES_NUMBER_INDEX = 1;

        /**头像ID**/
        final int PHONES_PHOTO_ID_INDEX = 2;

        /**联系人的ID**/
        final int PHONES_CONTACT_ID_INDEX = 3;
        ContentResolver resolver = mContext.getContentResolver();

        /**获取库Phone表字段**/
        final String[] PHONES_PROJECTION = new String[]{
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID};


        // 获取手机联系人
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);


        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                ContactBean contactBean = new ContactBean();
                //得到手机号码
                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                //当手机号码为空的或者为空字段 跳过当前循环
                if (TextUtils.isEmpty(phoneNumber))
                    continue;

                //得到联系人名称
                String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);

                //得到联系人ID
                Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);

                //得到联系人头像ID
                Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);

                //得到联系人头像Bitamp
                Bitmap contactPhoto = null;

                //photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的
                if (photoid > 0) {
                    Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactid);
                    InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);
                    contactPhoto = BitmapFactory.decodeStream(input);
                } else {
                    contactPhoto = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.iv_lol_icon3);
                }
                contactBean.setName(contactName);
                contactBean.setHeaderImg(contactPhoto);
                contactBean.setPhone(phoneNumber);
                contactBeans.add(contactBean);
            }

            phoneCursor.close();
        }
        return contactBeans;
    }
}
