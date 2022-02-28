package com.example.dto.body;

import com.example.dto.ConsumeProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeProductsBody {

    private String username;
    private List<ConsumeProductDTO> products;
}
