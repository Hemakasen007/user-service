package com.travel.lodge.userservice.util;

public class Consts {
    private Consts(){}

    public static class ConstString{
        private ConstString(){}
        public static final int ACTIVE_USER_REG= 1;
    }

    public static class ExceptionMessage{
        private ExceptionMessage(){}
        public static final String USER_EXISTS = "The User already exists in the system";
        public static final String USER_DOESNT_EXIST = "The User doesnt exist in the system";
        public static final String USER_CREATION_FAILED = "Cannot create user";
    }

    public static class Log{
        private Log(){}
        public static final String BRACKETS_3 = "{} -> {} -> {}";
        public static final String BRACKETS_2 = "{} -> {}";

        public static final String ENTERED = "Entered";

        public static final String ADD_USER = "addUser";
        public static final String USER_EXISTS = "User Exists";
    }

    public static class AppMessages{
        private AppMessages(){}
        public static final String USER_CREATED = "User Successfully created ";
        public static final String GET_USERS = "Retrieving All Users ";
        public static final String UPDATED_USER = "User Updated ";
    }

    public enum ResponseMessages {
        FAILED_ADD_USER,
        SUCCESS,
        FAILED ;
    }

    public enum UserStatus{
        DEACTIVATE, ACTIVATE;
    }
}
