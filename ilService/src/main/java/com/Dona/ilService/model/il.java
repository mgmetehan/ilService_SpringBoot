package com.Dona.ilService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class il {
//Database id ile belirtmek için ekledik bunu
    @Id
    private String id;
    //sürekli tarih oluşturmayla ilgilenmemek için
    private Date createdDate=new Date();

    private String name;
}
