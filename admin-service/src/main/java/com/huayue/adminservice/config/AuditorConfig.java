package com.huayue.adminservice.config;

import com.huayue.common.util.SecurityUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/22.
 * @description
 */
@Configuration
public class AuditorConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String username = SecurityUtil.getCurrentUsername();
        return Optional.ofNullable(username);
    }
}
