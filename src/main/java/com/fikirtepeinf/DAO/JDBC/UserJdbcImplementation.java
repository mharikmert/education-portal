package com.fikirtepeinf.DAO.JDBC;

import com.fikirtepeinf.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcImplementation {
        @Autowired
        private JdbcTemplate jdbcTemplate;

        public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
                this.jdbcTemplate = jdbcTemplate;
        }

        RowMapper<User> rowMapper = (rs, rowNum) -> {
                User user = new User();
                user.setTc(rs.getString("tc"));
                user.setPassword(rs.getString("password"));
                return user;
        };

        public boolean checkInfo(User user){
                System.out.println("i am in check info");
                String sql = "select tc, password from user where tc = " + user.getTc() + " and password = " + user.getPassword();
                User checkedUser =
                        DataAccessUtils.singleResult(jdbcTemplate.query(sql,  rowMapper
                        ));
                assert checkedUser != null;
                return user.getTc().equals(checkedUser.getTc()) &&
                        user.getPassword().equals(checkedUser.getPassword());
        }
        public List<User> allUsers(){
                String sql = "select * from user";
                return jdbcTemplate.query(sql, rowMapper);
        }

}
