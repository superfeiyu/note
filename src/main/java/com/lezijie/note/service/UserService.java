package com.lezijie.note.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.lezijie.note.dao.UserDao;
import com.lezijie.note.po.User;
import com.lezijie.note.vo.ResultInfo;

public class UserService {

    private UserDao userDao = new UserDao();

    public ResultInfo<User> userLogin(String userName, String userPwd) {
        ResultInfo<User> resultInfo = new ResultInfo<>();
        User u = new User();
        u.setUname(userName);
        u.setUpwd(userPwd);
        resultInfo.setResult(u);

        if (StrUtil.isBlank(userName) || StrUtil.isBlank(userPwd)) {
            resultInfo.setCode(0);
            resultInfo.setMsg("用户姓名或密码不能为空!");
            return resultInfo;
        }
        User user = userDao.queryUserByName(userName);
        if (user == null) {
            resultInfo.setCode(0);
            resultInfo.setMsg("该用户不存在!");
            return resultInfo;
        }
        userPwd = DigestUtil.md5Hex(userPwd);
        if (!userPwd.equals(user.getUpwd())) {
            resultInfo.setCode(0);
            resultInfo.setMsg("用户密码不正确!");
            return resultInfo;
        }
        resultInfo.setCode(1);
        resultInfo.setResult(user);
        return resultInfo;
    }

}
