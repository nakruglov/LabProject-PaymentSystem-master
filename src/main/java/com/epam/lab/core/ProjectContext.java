package com.epam.lab.core;
/*
 *
 *  This class needed for storage all object in one place (ProjectContext)
 *  If you want to store some object which will use in other classes
 *  you need:
 *  1. Get instance of ProjectContext ( ProjectContext context = ProjectContext.getContext(); )
 *  2. context.setContextEntity("myObject",object);
 *
 *  then you can get this object anywhere in the project, just write this:
 *  context.getContextEntity("myObject");
 *
 */

import com.epam.lab.dao.impl.AccountDao;
import com.epam.lab.dao.impl.CardDao;
import com.epam.lab.dao.impl.TransactionDao;
import com.epam.lab.dao.impl.UserDao;
import com.epam.lab.service.TransactionService;


import javax.sql.DataSource;
import java.util.HashMap;

public class ProjectContext {

    private static final ProjectContext context;
    private HashMap<String, Object> entityMap = new HashMap<>();

    static {
        context = new ProjectContext();
    }

    private ProjectContext() {
        DataSource dataSource = Configuration.getDataSource();
        entityMap.put("dataSource", dataSource);

        UserDao userDao = new UserDao();
        TransactionDao transactionDao = new TransactionDao();
        AccountDao accountDao = new AccountDao();
        CardDao cardDao = new CardDao();

        entityMap.put("userDao",userDao);
        entityMap.put("transactionDao",transactionDao);
        entityMap.put("accountDao",accountDao);
        entityMap.put("cardDao",cardDao);

//        UserService userService = new UserService(dataSource,userDao);
        TransactionService transactionService = new TransactionService(dataSource,transactionDao, cardDao, accountDao);
//        AccountService accountService = new AccountService(dataSource,accountDao);
//        CardService cardService = new CardService(dataSource,cardDao);

//        entityMap.put("userService",userService);
        entityMap.put("transactionService",transactionService);
//        entityMap.put("accountService",accountService);
//        entityMap.put("cardService",cardService);
    }

    public static ProjectContext getContext() {
        return context;
    }

    public void setContextEntity(String key, Object obj) {
        entityMap.put(key, obj);
    }

    public Object getContextEntity(String key) {
        return entityMap.get(key);
    }


}
