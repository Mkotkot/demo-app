package com.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserRequestParam {
    @NotNull
    @ApiModelProperty(required = true)
    private String firstName;
    @NotNull
    @ApiModelProperty(required = true)
    private String lastName;
    @NotNull
    @ApiModelProperty(required = true)
    private LocalDate dateOfBirth;
    @NotNull
    @ApiModelProperty(required = true)
    private String email;
}
