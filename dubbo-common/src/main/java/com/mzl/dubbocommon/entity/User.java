package com.mzl.dubbocommon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author mzl
 * @since 2022-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "user_id", type = IdType.AUTO)
      private Integer userId;

    private String username;

    private String password;

    private Integer age;

    private String phone;


}
