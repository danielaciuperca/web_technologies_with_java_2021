package com.unibuc.ex1curs13.repository;

import com.unibuc.ex1curs13.model.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;

@Repository
public class DestinationRepository {
    private JdbcTemplate jdbcTemplate;

    public DestinationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Destination create(Destination destination) {
        String sql = "insert into destinations values (?, ?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, destination.getName());
            preparedStatement.setString(3, destination.getCountry());
            preparedStatement.setString(4, destination.getDestinationType().toString());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

        destination.setId(generatedKeyHolder.getKey().longValue());
        return destination;
    }

    public Optional<Destination> getByName(String name) {
        String sql = "select * from destinations d where d.name = ?";
        RowMapper<Destination> mapper = getDestinationRowMapper();

        return getDestinationFromResultSet(jdbcTemplate.query(sql, mapper, name));
    }

    public Optional<Destination> getById(long id) {
        String sql = "select * from destinations d where d.id = ?";
        RowMapper<Destination> mapper = getDestinationRowMapper();

        return getDestinationFromResultSet(jdbcTemplate.query(sql, mapper, id));
    }

    public void deleteAll() {
        String sql = "delete from destinations";
        jdbcTemplate.update(sql);
    }

    private Optional<Destination> getDestinationFromResultSet(List<Destination> destinations) {
        if (destinations != null && !destinations.isEmpty()) {
            return Optional.of(destinations.get(0));
        } else {
            return Optional.empty();
        }
    }

    private RowMapper<Destination> getDestinationRowMapper() {
        return (resultSet, rowNum) ->
                new Destination(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("country"),
                        DestinationType.valueOf(resultSet.getString("destinationType")));
    }
}
