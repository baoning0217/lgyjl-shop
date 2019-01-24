package com.vincce.shop.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created By BaoNing On 2019/1/24
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {

    private boolean success = false;

    private String message = null;

}
