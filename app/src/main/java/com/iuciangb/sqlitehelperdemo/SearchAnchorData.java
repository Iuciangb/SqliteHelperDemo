package com.iuciangb.sqlitehelperdemo;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * @author YY
 * @create 2019/4/3
 * @Describe
 **/
public class SearchAnchorData implements Parcelable {
    private final static int ONLINE_TRUE = 0;
    private final static int HAS_TRACE = 1;
    private int no;
    private String account;
    private String nickname;
    private String introduction;
    private String hotMark;
    private int online;
    private int role;
    private String avatar;
    private int hasTrace;

    protected SearchAnchorData(Parcel in) {
        no = in.readInt();
        account = in.readString();
        nickname = in.readString();
        introduction = in.readString();
        hotMark = in.readString();
        online = in.readInt();
        role = in.readInt();
        avatar = in.readString();
        hasTrace = in.readInt();
    }

    public SearchAnchorData(int no, String account, String nickname, String introduction, String hotMark) {
        this.no = no;
        this.account = account;
        this.nickname = nickname;
        this.introduction = introduction;
        this.hotMark = hotMark;
    }

    public static final Creator<SearchAnchorData> CREATOR = new Creator<SearchAnchorData>() {
        @Override
        public SearchAnchorData createFromParcel(Parcel in) {
            return new SearchAnchorData(in);
        }

        @Override
        public SearchAnchorData[] newArray(int size) {
            return new SearchAnchorData[size];
        }
    };

    public int getNo() {
        return no;
    }

    public String getAccount() {
        return account;
    }

    public String getNickname() {
        if (TextUtils.isEmpty(nickname)) {
            return "";
        }
        return nickname;
    }

    public String getIntroduction() {
        if (TextUtils.isEmpty(introduction)) {
            return "";
        }
        return introduction;
    }

    public String getHotMark() {
        if (TextUtils.isEmpty(hotMark)) {
            return "";
        }
        return hotMark;
    }

    public boolean isOnline() {
        return online == ONLINE_TRUE;
    }

    public int getRole() {
        return role;
    }

    public String getAvatar() {
        return avatar;
    }

    /**
     * 0=未追蹤
     * 1=已追蹤
     *
     * @return
     */
    public boolean isTrace() {
        return hasTrace == HAS_TRACE;
    }

    @Override
    public String toString() {
        return "SearchAnchorData{" +
                "no=" + no +
                ", account='" + account + '\'' +
                ", nickname='" + nickname + '\'' +
                ", introduction='" + introduction + '\'' +
                ", hotMark='" + hotMark + '\'' +
                ", online=" + online +
                ", role=" + role +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(no);
        parcel.writeString(account);
        parcel.writeString(nickname);
        parcel.writeString(introduction);
        parcel.writeString(hotMark);
        parcel.writeInt(online);
        parcel.writeInt(role);
        parcel.writeString(avatar);
        parcel.writeInt(hasTrace);
    }
}
