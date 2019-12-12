package com.lab.factory;

import com.lab.dao.*;
import com.lab.enums.DaoType;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
    private static Map<DaoType, EntityDao> daoMap = new HashMap<>();

    static {
        daoMap.put(DaoType.LINE, new LineDao());
        daoMap.put(DaoType.RECEIPT, new ReceiptDao());
        daoMap.put(DaoType.USER, new UserDao());
        daoMap.put(DaoType.PRODUCT, new ProductDao());
        daoMap.put(DaoType.REPORT_LINE, new ReportLineDao());
        daoMap.put(DaoType.REPORT, new ReportDao());
    }

    private DaoFactory() {
    }

    public static EntityDao getEntityDao(DaoType daoType){
        EntityDao entityDao = daoMap.get(daoType);
        if(entityDao != null ){
            return entityDao;
        }
        throw new RuntimeException("Dao with current dao type do not exist: " + daoType.name());
    }
}
