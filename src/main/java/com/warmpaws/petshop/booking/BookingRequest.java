package com.warmpaws.petshop.booking;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record BookingRequest(
        @NotBlank(message = "请填写称呼")
        @Size(max = 30, message = "称呼不能超过30个字符")
        String name,

        @NotBlank(message = "请填写联系电话")
        @Pattern(regexp = "^[0-9+\\-\\s]{6,20}$", message = "联系电话格式不正确")
        String phone,

        @NotBlank(message = "请选择宠物类型")
        String petType,

        @NotNull(message = "请选择预约时间")
        LocalDateTime appointmentTime,

        @NotBlank(message = "请选择需要服务")
        String service,

        @Size(max = 200, message = "备注不能超过200个字符")
        String note
) {
}
