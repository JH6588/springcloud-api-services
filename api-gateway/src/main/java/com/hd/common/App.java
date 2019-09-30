package com.hd.common;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Data
@ToString
public class App implements Serializable {

    @GeneratedValue
    @Id
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String appKey;

    @Size(min = 12, max = 36)
    @Pattern(regexp = ".*[A-Z].*")
    @Pattern(regexp = ".*[a-z].*")
    @Pattern(regexp = ".*[0-9].*")
    private String secret;
    private String appName;
    //方便计费等
    private  Long createTimeStamp;
    //方便 对 secret 更改间隔做限制
    private  Long lastChangeTimeStamp;


}
