package com.lezijie.note;

import com.lezijie.note.util.DBUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDB {

    private Logger logger= LoggerFactory.getLogger(TestDB.class);

    @Test
    public void testConnection(){
        System.out.println(DBUtil.getConnection());
        logger.info("获取数据库链接："+DBUtil.getConnection());
        logger.info("获取数据库{}链接: ",DBUtil.getConnection());
    }


}
