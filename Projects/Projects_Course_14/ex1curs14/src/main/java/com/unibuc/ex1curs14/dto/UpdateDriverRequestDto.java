package com.unibuc.ex1curs14.dto;

import javax.validation.constraints.*;

public class UpdateDriverRequestDto extends CreateDriverRequestDto {

    @NotNull
    private long id;

    public UpdateDriverRequestDto(String name, String email, String city, long id) {
        super(name, email, city);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
