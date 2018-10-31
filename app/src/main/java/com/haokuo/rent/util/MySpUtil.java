package com.haokuo.rent.util;

import com.haokuo.rent.bean.UserInfoBean;
import com.haokuo.rent.consts.SpConsts;
import com.haokuo.rent.util.utilscode.SPUtils;

/**
 * Created by zjf on 2018/9/7.
 */
public class MySpUtil {
    private static MySpUtil mInstance;
    private final SPUtils mPersonInfoSp;

    public static MySpUtil getInstance() {
        if (mInstance == null) {
            synchronized (MySpUtil.class) {
                if (mInstance == null) {
                    mInstance = new MySpUtil();
                }
            }
        }
        return mInstance;
    }

    private MySpUtil() {
        mPersonInfoSp = SPUtils.getInstance(SpConsts.FILE_PERSONAL_INFORMATION);
    }

    public long getUserId() {
        return mPersonInfoSp.getLong(SpConsts.KEY_USER_ID, -1);
    }

    public String getToken() {
        return mPersonInfoSp.getString(SpConsts.KEY_TOKEN);
    }

    public void logout() {
        mPersonInfoSp.clear();
    }

    public void saveUserInfo(UserInfoBean userInfoBean) {
        mPersonInfoSp.put(SpConsts.KEY_USER_NAME, userInfoBean.getUserName());
        mPersonInfoSp.put(SpConsts.KEY_BIRTHDAY, userInfoBean.getBirthday());
        mPersonInfoSp.put(SpConsts.KEY_HEAD_PHOTO, userInfoBean.getHeadPhoto());
        mPersonInfoSp.put(SpConsts.KEY_ID_CARD, userInfoBean.getIdCard());
        mPersonInfoSp.put(SpConsts.KEY_REAL_NAME, userInfoBean.getRealname());
        mPersonInfoSp.put(SpConsts.KEY_SEX, userInfoBean.getSex());
        mPersonInfoSp.put(SpConsts.KEY_VOLUNTEER_STATUS, userInfoBean.getVolunteerStatus());
    }

    public UserInfoBean getUserInfo() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUserName(mPersonInfoSp.getString(SpConsts.KEY_USER_NAME));
        userInfoBean.setBirthday(mPersonInfoSp.getString(SpConsts.KEY_BIRTHDAY));
        userInfoBean.setHeadPhoto(mPersonInfoSp.getString(SpConsts.KEY_HEAD_PHOTO));
        userInfoBean.setIdCard(mPersonInfoSp.getString(SpConsts.KEY_ID_CARD));
        userInfoBean.setRealname(mPersonInfoSp.getString(SpConsts.KEY_REAL_NAME));
        userInfoBean.setSex(mPersonInfoSp.getString(SpConsts.KEY_SEX));
        userInfoBean.setVolunteerStatus(mPersonInfoSp.getInt(SpConsts.KEY_VOLUNTEER_STATUS));
        return userInfoBean;
    }


}
