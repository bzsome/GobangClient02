package com.chao.oauth;


/**
 * 用户接口
 *
 * @author Chao
 */
public interface UserAuthService {

    /**
     * 用户登录
     *
     * @author Chao
     */
    public void login(String account, String password, LoginBack loginBack);

    /**
     * 增加积分
     *
     * @param grade
     * @return
     */
    public int addGrade(int grade);

    /**
     * 通过token口令，获得用户信息
     *
     * @return
     */
    public void getUserByToken(String token, LoginBack loginBack);


    /**
     * 用户登录回调接口
     */
    public static interface LoginBack {
        public void success();

        public void error();
    }
}
