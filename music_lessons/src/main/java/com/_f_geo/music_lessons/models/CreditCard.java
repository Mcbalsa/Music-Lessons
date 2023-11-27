package com._f_geo.music_lessons.models;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class CreditCard {
    String cardNum;
    String name;
    LocalDate expDate;
    String cvv;
    String type;
}
