package com.unibuc.ex1curs13.repository;

import com.unibuc.ex1curs13.model.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.*;

import java.sql.*;

@Repository
public class HolidayRepository {
    private JdbcTemplate jdbcTemplate;

    public HolidayRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Holiday create(Holiday holiday) {
        String sql = "insert into holidays values (?, ?, ?, ?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, holiday.getAccommodation());
            preparedStatement.setInt(3, holiday.getDuration());
            preparedStatement.setString(4, holiday.getTransportation());
            preparedStatement.setDouble(5, holiday.getPrice());
            preparedStatement.setLong(6, holiday.getDestinationId());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

        holiday.setId(generatedKeyHolder.getKey().longValue());
        return holiday;
    }
}
