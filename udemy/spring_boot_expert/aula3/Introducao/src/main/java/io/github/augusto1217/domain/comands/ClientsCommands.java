package io.github.augusto1217.domain.comands;

import java.io.Serializable;

public class ClientsCommands {

    public static String INSERT = "insert into client (name) values(?)";
    public static String SELECT_ALL = "select * from client";
    public static String UPDATE = "update client set name = ? where id = ?";
    public static String DELETE = "delete from client where id = ?";
    public static String SELECT_FOR_NAME = "select * from client";

}
